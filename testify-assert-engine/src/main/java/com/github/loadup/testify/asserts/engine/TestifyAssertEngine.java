package com.github.loadup.testify.asserts.engine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.loadup.testify.core.util.JsonUtil;
import java.util.List;
import java.util.Map;

public interface TestifyAssertEngine {
  /** 该引擎负责处理的 YAML 节点名称，如 "response", "database", "exception" */
  String supportKey();

  /**
   * 执行断言逻辑
   *
   * @param expectNode 对应的 YAML 子节点内容
   * @param actual 实际值（由编排器传入）
   * @param context testcontext
   * @param reportList 错误报表收集器
   */
  void compare(
      JsonNode expectNode, Object actual, Map<String, Object> context, List<String> reportList);

  // 在 TestifyAssertEngine 接口中增加默认转换逻辑
  default Object resolveExpectedValue(JsonNode node) {
    if (node == null || node.isNull()) return null;
    if (node.isObject()) {
      // 如果是配置对象 {op: ..., val: ...}，转为 Map
      return JsonUtil.convertValue(node, new TypeReference<Map<String, Object>>() {});
    }
    if (node.isNumber()) return node.numberValue();
    if (node.isBoolean()) return node.booleanValue();
    // 关键点：使用 asText() 拿掉双引号
    return node.asText();
  }
}
