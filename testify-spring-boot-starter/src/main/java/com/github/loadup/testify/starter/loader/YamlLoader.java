package com.github.loadup.testify.starter.loader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.loadup.testify.core.model.TestContext;
import java.io.InputStream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/** YamlLoader 仅负责将 YAML 物理文件读取为 TestContext 对象。 不再持有 VariableEngine，不再进行变量解析。 */
@Slf4j
public class YamlLoader {

  private static final ObjectMapper yamlMapper;

  static {
    yamlMapper = new ObjectMapper(new YAMLFactory());
    yamlMapper.registerModule(new JavaTimeModule());
    // 允许 YAML 中存在 TestContext 未定义的字段
    yamlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @SneakyThrows
  public TestContext load(String path) {
    log.info("Loading raw YAML test case from classpath: {}", path);

    try (InputStream is = getClass().getClassLoader().getResourceAsStream(path)) {
      if (is == null) {
        throw new IllegalArgumentException("Test case file not found in classpath: " + path);
      }

      // 直接将 YAML 文件反序列化为 TestContext Record
      // 此时里面的 JsonNode 仍然保留着原始的占位符文本，如 "${faker.name()}"
      TestContext context = yamlMapper.readValue(is, TestContext.class);
      // 如果需要从路径推断测试名称，可以在这里进行简单的元数据补充
      String testName =
          path.substring(path.lastIndexOf("/") + 1).replace(".yaml", "").replace(".yml", "");
      context.setTestName(testName);
      context.setYamlPath(path);

      // 返回最原始的 Context 对象，变量解析交给后续的 runTest 环节
      return context;
    }
  }
}
