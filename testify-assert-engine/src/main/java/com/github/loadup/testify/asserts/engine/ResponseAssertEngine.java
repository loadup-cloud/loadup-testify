package com.github.loadup.testify.asserts.engine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.loadup.testify.asserts.diff.DiffReportBuilder;
import com.github.loadup.testify.asserts.model.FieldDiff;
import com.github.loadup.testify.asserts.model.MatchResult;
import com.github.loadup.testify.asserts.operator.OperatorProcessor;
import com.github.loadup.testify.core.util.JsonUtil;
import com.github.loadup.testify.data.engine.variable.VariableEngine;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResponseAssertEngine implements TestifyAssertEngine {
  private final VariableEngine variableEngine;

  public ResponseAssertEngine(VariableEngine variableEngine) {
    this.variableEngine = variableEngine;
  }

  @Override
  public String supportKey() {
    return "response";
  }

  @Override
  public void compare(
      JsonNode expectRes, Object actualRes, Map<String, Object> context, List<String> reportList) {
    // 1. 准备数据：解析变量 & 转换为树
    JsonNode resolvedExpect = variableEngine.resolveJsonNode(expectRes, context);
    JsonNode actualNode = JsonUtil.valueToTree(actualRes);

    List<FieldDiff> diffs = new ArrayList<>();
    // 2. 递归校验
    recursiveCheck("res", resolvedExpect, actualNode, diffs);

    // 3. 结果处理
    if (!diffs.isEmpty()) {
      throw new AssertionError(DiffReportBuilder.build("API Response Assertion", diffs));
    }
  }

  private void recursiveCheck(String path, JsonNode exp, JsonNode act, List<FieldDiff> diffs) {
    // 情况 A: 期望节点是操作符配置 (如 {op: approx, val: ...})
    if (isOperatorNode(exp)) {
      Map<String, Object> expVal = JsonUtil.convertValue(exp, new TypeReference<>() {});
      Object actVal = getValueFromJsonNode(act);

      // 复用数据库的 OperatorProcessor
      MatchResult result = OperatorProcessor.process(actVal, expVal);
      if (!result.isPassed()) {
        diffs.add(new FieldDiff(path, expVal.get("val"), actVal, result.message()));
      }
      return;
    }

    // 情况 B: 期望节点是普通的 JSON 对象(递归)
    if (exp.isObject()) {
      exp.fields()
          .forEachRemaining(
              entry -> {
                String key = entry.getKey();
                recursiveCheck(path + "." + key, entry.getValue(), act.path(key), diffs);
              });
    }
    // 情况 C: 期望节点是数组(递归)
    else if (exp.isArray()) {
      if (!act.isArray() || exp.size() != act.size()) {
        diffs.add(
            new FieldDiff(
                path, "Size: " + exp.size(), "Size: " + act.size(), "Array size mismatch"));
      } else {
        for (int i = 0; i < exp.size(); i++) {
          recursiveCheck(path + "[" + i + "]", exp.get(i), act.get(i), diffs);
        }
      }
    }
    // 情况 D: 叶子节点，直接进行等值对比
    else {
      Object expVal = getValueFromJsonNode(exp);
      Object actVal = getValueFromJsonNode(act);
      // 统一走 OperatorProcessor，这样即使是普通值，也能享受 SimpleMatcher 提供的类型兼容性比对
      MatchResult result = OperatorProcessor.process(actVal, expVal);
      if (!result.isPassed()) {
        diffs.add(new FieldDiff(path, expVal, actVal, result.message()));
      }
    }
  }

  private boolean isOperatorNode(JsonNode node) {
    return node.isObject() && node.has("op") && node.has("val");
  }

  private Object getValueFromJsonNode(JsonNode node) {
    if (node == null || node.isMissingNode() || node.isNull()) return null;
    if (node.isNumber()) return node.numberValue();
    if (node.isBoolean()) return node.booleanValue();
    return node.asText();
  }
}
