package com.github.loadup.testify.data.engine.variable;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;
import com.github.loadup.testify.data.engine.helper.FunctionHelper;
import com.github.loadup.testify.data.engine.helper.TimeHelper;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Variable resolution engine supporting: - Datafaker expressions: ${faker.name.firstName} - Time
 * functions with offsets: ${time.now('+1d')}, ${time.now('-2h')} - Custom functions: ${fn.uuid()},
 * ${fn.random(1, 100)} - Variable cross-references with dependency resolution
 */
@Slf4j
public class VariableEngine {

  private final Faker faker = new Faker();
  private final ExpressionParser spelParser = new SpelExpressionParser();
  private final ParserContext templateContext = new ParserContext() {
    @Override
    public boolean isTemplate() { return true; }
    @Override
    public String getExpressionPrefix() { return "${"; }
    @Override
    public String getExpressionSuffix() { return "}"; }
  };
  // --- 1. Entry Points (入口方法) ---
  /** 场景 A: 初始化 YAML variables 块。处理依赖关系并生成上下文。 */
  public Map<String, Object> resolveVariables(Map<String, String> rawVariables) {
    Map<String, Object> context = new LinkedHashMap<>();
    if (rawVariables == null) return context;

    // 利用 SpEL 自身的能力，多次迭代解析直到结果稳定（处理变量间引用）
    int maxIterations = rawVariables.size() * 2;
    for (int i = 0; i < maxIterations && context.size() < rawVariables.size(); i++) {
      rawVariables.forEach(
          (key, value) -> {
            if (!context.containsKey(key)) {
              try {
                Object evaluated = evaluate(value, context);
                if (evaluated != null && !String.valueOf(evaluated).contains("${")) {
                  context.put(key, evaluated);
                }
              } catch (Exception ignored) {
              }
            }
          });
    }
    return context;
  }

  /** 场景 B: 解析整个 JsonNode 树 (用于 input, setup, expect 等) */
  public JsonNode resolveJsonNode(JsonNode node, Map<String, Object> context) {
    if (node == null || !node.isContainerNode() && !node.isTextual()) return node;

    if (node.isTextual()) {
      return convertToNode(evaluate(node.asText(), context));
    }

    if (node.isObject()) {
      ObjectNode newNode = JsonNodeFactory.instance.objectNode();
      node.fields()
          .forEachRemaining(
              entry -> newNode.set(entry.getKey(), resolveJsonNode(entry.getValue(), context)));
      return newNode;
    }

    if (node.isArray()) {
      ArrayNode newNode = JsonNodeFactory.instance.arrayNode();
      node.forEach(item -> newNode.add(resolveJsonNode(item, context)));
      return newNode;
    }
    return node;
  }

  // --- 2. Core Evaluator (评估核心) ---

  /** 统一的解析方法：处理纯占位符、混合字符串、工具类调用 */
  public Object evaluate(String text, Map<String, Object> context) {
    if (text == null || !text.contains("${")) return text;
    log.info("开始解析: {}, 当前 Context 中的 Key: {}", text, context.keySet());
    // 如果是纯变量引用，如 "${userId}"，且 context 中已有，直接返回对象保持类型
    String expression = text.trim();
    if (expression.startsWith("${")
        && expression.endsWith("}")
        && countOccurrences(expression, "${") == 1) {
      String varName = expression.substring(2, expression.length() - 1);
      if (context != null && context.containsKey(varName)) return context.get(varName);
    }

    try {
      StandardEvaluationContext spelContext = buildSpelContext(context);
      // 自动为变量增加 # 前缀，以符合 SpEL 规范
      String processedText = ensureHashPrefix(text);
      Object result = spelParser.parseExpression(processedText, templateContext).getValue(spelContext);
      return result != null ? result : text;
    } catch (Exception e) {
      log.warn("SpEL 解析失败: {}，错误: {}", text, e.getMessage());
      return text;
    }
  }

  // --- 3. Helpers (内部辅助) ---

  private StandardEvaluationContext buildSpelContext(Map<String, Object> context) {
    StandardEvaluationContext ctx = new StandardEvaluationContext();
    ctx.addPropertyAccessor(new MapAccessor());

    // 注册核心工具
    ctx.setVariable("fn", new FunctionHelper());
    ctx.setVariable("time", new TimeHelper());
    ctx.setVariable("faker", faker);

    // 注册业务变量
    if (context != null) {
      context.forEach(ctx::setVariable);
    }
    return ctx;
  }

  private String ensureHashPrefix(String text) {
    // 匹配 ${ 后面不是 # 的所有情况，自动补上 #。使得 ${time.now()} 变为 ${#time.now()}
    return text.replaceAll("\\$\\{(?=[^#])", "\\${#");
  }

  private JsonNode convertToNode(Object obj) {
    if (obj == null) return NullNode.instance;
    return switch (obj) {
      case JsonNode j -> j;
      case Integer i -> IntNode.valueOf(i);
      case Long l -> LongNode.valueOf(l);
      case Double d -> DoubleNode.valueOf(d);
      case Boolean b -> BooleanNode.valueOf(b);
      case TemporalAccessor t ->
          TextNode.valueOf(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(t));
      default -> TextNode.valueOf(obj.toString());
    };
  }

  private int countOccurrences(String str, String sub) {
    return (str.length() - str.replace(sub, "").length()) / sub.length();
  }
}
