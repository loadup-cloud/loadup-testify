package com.github.loadup.testify.data.engine.variable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

import com.github.loadup.testify.data.engine.helper.FunctionHelper;
import com.github.loadup.testify.data.engine.helper.TimeHelper;
import net.datafaker.Faker;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Variable resolution engine supporting: - Datafaker expressions: ${faker.name.firstName} - Time
 * functions with offsets: ${time.now('+1d')}, ${time.now('-2h')} - Custom functions: ${fn.uuid()},
 * ${fn.random(1, 100)} - Variable cross-references with dependency resolution
 */
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;

public class VariableEngine {

  private final Faker faker = new Faker();
  private final ExpressionParser spelParser = new SpelExpressionParser();

  /** 核心方法：解析变量块并处理依赖顺序 */
  public Map<String, Object> resolveVariables(Map<String, String> rawVariables) {
    Map<String, Object> context = new LinkedHashMap<>();
    if (rawVariables == null) return context;

    StandardEvaluationContext spelContext = createSpelContext();
    Map<String, String> stringContext = new HashMap<>();
    Set<String> resolved = new HashSet<>();

    int maxIterations = rawVariables.size() * 2; // 防止死循环
    int iteration = 0;

    while (resolved.size() < rawVariables.size() && iteration++ < maxIterations) {
      for (Map.Entry<String, String> entry : rawVariables.entrySet()) {
        String key = entry.getKey();
        if (resolved.contains(key)) continue;

        try {
          String value = entry.getValue();
          // 替换已经解析过的变量
          String substituted = StringSubstitutor.replace(value, stringContext);

          // 执行 SpEL 或 Faker
          Object evaluated = evaluate(substituted, spelContext);

          context.put(key, evaluated);
          stringContext.put(key, String.valueOf(evaluated));
          resolved.add(key);
        } catch (Exception ignored) {
          // 依赖尚未就绪，等待下一轮解析
        }
      }
    }
    return context;
  }

  /** 递归遍历 JsonNode 树，替换所有文本节点中的变量 */
  public JsonNode resolveJsonNode(JsonNode node, Map<String, Object> context) {
    if (node == null || node.isNull()) {
      return node;
    }

    if (node.isTextual()) {
      // 解析文本中的变量：可能是 "${id}" -> 123 (LongNode)
      // 也可能是 "Hello ${name}" -> "Hello Tom" (TextNode)
      Object resolvedValue = resolveString(node.asText(), context);
      return convertToNode(resolvedValue);
    }

    if (node.isObject()) {
      ObjectNode objectNode = (ObjectNode) node;
      // 注意：这里需要创建一个新 Map 迭代，因为在迭代过程中不能修改 objectNode
      Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();
      Map<String, JsonNode> updates = new HashMap<>();
      while (fields.hasNext()) {
        Map.Entry<String, JsonNode> entry = fields.next();
        updates.put(entry.getKey(), resolveJsonNode(entry.getValue(), context));
      }
      updates.forEach(objectNode::set);
      return objectNode;
    }

    if (node.isArray()) {
      ArrayNode arrayNode = (ArrayNode) node;
      for (int i = 0; i < arrayNode.size(); i++) {
        arrayNode.set(i, resolveJsonNode(arrayNode.get(i), context));
      }
      return arrayNode;
    }

    return node;
  }

  public Object resolveString(String text, Map<String, Object> context) {
    if (text == null || !text.contains("${")) return text;

    // 处理纯变量引用，保持原始类型
    if (text.startsWith("${")
        && text.endsWith("}")
        && !text.substring(2, text.length() - 1).contains("${")) {
      String varName = text.substring(2, text.length() - 1);
      if (context.containsKey(varName)) return context.get(varName);
    }

    // 处理混合字符串模板
    StringSubstitutor sub = new StringSubstitutor(context);
    return sub.replace(text);
  }

  private Object evaluate(String expression, StandardEvaluationContext spelContext) {
    if (expression.startsWith("${") && expression.endsWith("}")) {
      String pureExp = expression.substring(2, expression.length() - 1);
      return spelParser.parseExpression(pureExp).getValue(spelContext);
    }
    return expression;
  }

  private JsonNode convertToNode(Object obj) {
    if (obj == null) return NullNode.instance;
    return switch (obj) {
      case String s -> TextNode.valueOf(s);
      case Integer i -> IntNode.valueOf(i);
      case Long l -> LongNode.valueOf(l);
      case Double d -> DoubleNode.valueOf(d);
      case Boolean b -> BooleanNode.valueOf(b);
      case TemporalAccessor t -> TextNode.valueOf(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(t));
      case JsonNode node -> node; // 如果已经是节点则直接返回
      default -> TextNode.valueOf(obj.toString()); // 兜底处理
    };
  }

  private StandardEvaluationContext createSpelContext() {
    StandardEvaluationContext ctx = new StandardEvaluationContext();
    ctx.setVariable("fn", new FunctionHelper());
    ctx.setVariable("time", new TimeHelper());
    ctx.setVariable("faker", faker);
    return ctx;
  }

  public String replacePlaceholders(String text, Map<String, Object> context) {
    if (text == null) {
      return null;
    }
    StringSubstitutor sub = new StringSubstitutor(context);
    sub.setEnableSubstitutionInVariables(true); // Support nested substitutions
    return sub.replace(text);
  }
}
