package io.github.loadup.testify.asserts.engine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import io.github.loadup.testify.core.util.JsonUtil;
import io.github.loadup.testify.data.engine.variable.VariableEngine;

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

}
