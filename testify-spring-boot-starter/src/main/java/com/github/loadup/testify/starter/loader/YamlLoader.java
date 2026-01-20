package com.github.loadup.testify.starter.loader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.loadup.testify.core.model.TestContext;
import com.github.loadup.testify.data.engine.variable.VariableEngine;
import java.io.InputStream;
import java.util.Map;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class YamlLoader {

  private static final ObjectMapper yamlMapper;
  private final VariableEngine variableEngine = new VariableEngine();

  static {
    yamlMapper = new ObjectMapper(new YAMLFactory());
    yamlMapper.registerModule(new JavaTimeModule());
    yamlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @SneakyThrows
  @SuppressWarnings("unchecked")
  public TestContext load(String path) {
    // Added debug logs to trace YAML loading and variable resolution
    log.info("Loading YAML test case from: {}", path);

    try (InputStream is = getClass().getClassLoader().getResourceAsStream(path)) {
      if (is == null) {
        throw new IllegalArgumentException("Test case file not found in classpath: " + path);
      }

      // 1. 直接读取为 Jackson 的 JsonNode，保留原始结构和类型
      JsonNode rootNode = yamlMapper.readTree(is);

      // 2. 提取并预解析 variables 块
      // 注意：variables 本身可能引用了 faker/time 等函数
      JsonNode varsNode = rootNode.get("variables");
      Map<String, String> rawVars = yamlMapper.convertValue(varsNode, Map.class);
      Map<String, Object> evaluatedVars = variableEngine.resolveVariables(rawVars);

      // 3. 【核心优化】使用 VariableEngine 递归解析 JsonNode 中的占位符
      // 这样 input/expect 中的 ${var} 会被替换，且保持类型（如数字、布尔）
      JsonNode resolvedNode = variableEngine.resolveJsonNode(rootNode, evaluatedVars);

      // 4. 将解析后的 JsonNode 映射为 TestContext Record
      // 因为你的 TestContext 字段（input, expect 等）定义为 JsonNode 类型，
      // 这里的转换会非常快，且保留了延迟解析的能力。
      TestContext context = yamlMapper.treeToValue(resolvedNode, TestContext.class);

      // 5. 补充变量到 Context 中以便后续使用（如果 Record 中没有包含它）
      // 如果你的 TestContext Record 包含 testName，可以从路径中推断
      String testName =
          path.substring(path.lastIndexOf("/") + 1).replace(".yaml", "").replace(".yml", "");

      return new TestContext(
          testName,path,
          evaluatedVars,
          context.input(),
          context.mocks(),
          context.setup(),
          context.expect());
    }
  }
}
