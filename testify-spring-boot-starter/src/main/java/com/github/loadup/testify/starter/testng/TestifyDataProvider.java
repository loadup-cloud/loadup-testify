package com.github.loadup.testify.starter.testng;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.loadup.testify.starter.loader.YamlLoader;
import com.github.loadup.testify.core.model.TestContext;
import com.github.loadup.testify.data.engine.variable.VariableContext;
import com.github.loadup.testify.data.engine.variable.VariableEngine;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * TestNG Data Provider for Testify framework. Automatically converts YAML input data to test method
 * parameters.
 *
 * <p>Usage: @Test(dataProvider = "testifyData", dataProviderClass = TestifyDataProvider.class)
 * public void testMethod(String param1, int param2) { ... }
 */
public class TestifyDataProvider {

  private static final ObjectMapper objectMapper = new ObjectMapper();
  private static final String TESTCASES_DIR = "testcases";
  private final YamlLoader yamlLoader = new YamlLoader();
  private static final VariableEngine variableEngine = new VariableEngine();

  /** Data provider that extracts input parameters from loaded TestContext. */
  @DataProvider(name = "testifyData")
  public Iterator<Object[]> provideTestData(ITestContext testngContext, Method method) {
    List<Object[]> dataList = new ArrayList<>();

    try {
      // 1. 约定加载 YAML
      String className = method.getDeclaringClass().getSimpleName();
      String methodName = method.getName();
      String yamlPath = "testcases/" + className + "/" + methodName + ".yaml";

      TestContext testContext = yamlLoader.load(yamlPath);
      // 2. 解析全局变量块
      Map<String, Object> resolvedVars =
          variableEngine.resolveVariables(
              objectMapper.convertValue(testContext.variables(), Map.class));

      // 3. 将变量存入当前线程上下文
      VariableContext.set(resolvedVars);

      // 4. 将 TestContext 绑定到 TestNG 运行上下文中，供 Listener 使用
      String contextKey = method.getDeclaringClass().getName() + "." + method.getName();
      testngContext.setAttribute(contextKey, testContext);

      // 5. 解析并转换 Input 参数
      if (testContext.input() != null) {
        // 递归解析 input 中的占位符
        JsonNode resolvedInput = variableEngine.resolveJsonNode(testContext.input(), resolvedVars);

        // 处理多组数据 (Array of Array) 或 单组数据 (Array)
        if (resolvedInput.isArray()) {
          // 如果 input 是数组，我们认为每个元素对应一个参数，或者每个子数组对应一组用例
          // 简化版：这里演示单组参数注入
          Object[] params = convertToArgs(resolvedInput, method.getParameterTypes());
          dataList.add(params);
        }
      } else {
        dataList.add(new Object[method.getParameterCount()]);
      }

    } catch (Exception e) {
      throw new RuntimeException("Failed to provide test data", e);
    }

    return dataList.iterator();
  }
  /**
   * 将解析后的 JsonNode 转换为 Java 方法参数
   */
  private static Object[] convertToArgs(JsonNode inputNode, Class<?>[] parameterTypes) {
    Object[] args = new Object[parameterTypes.length];
    for (int i = 0; i < parameterTypes.length; i++) {
      if (i < inputNode.size()) {
        args[i] = objectMapper.convertValue(inputNode.get(i), parameterTypes[i]);
      } else {
        args[i] = null;
      }
    }
    return args;
  }
  /**
   * Load YAML test case for the current test method. Convention:
   * src/test/resources/testcases/[ClassName]/[methodName].yaml
   */
  private TestContext loadTestCase(Method method, ITestContext context) {
    Class<?> testClass = method.getDeclaringClass();

    // Build YAML file path following convention
    String className = testClass.getSimpleName();
    String methodName = method.getName();
    String yamlPath = TESTCASES_DIR + "/" + className + "/" + methodName + ".yaml";

    try {
      // Load YAML using YamlLoader from classpath
      TestContext testContext = yamlLoader.load(yamlPath);

      // Store in test context for listener access
      context.setAttribute("testContext_" + methodName, testContext);

      return testContext;
    } catch (Exception e) {
      // For debugging: throw exception instead of returning null
      System.err.println("Failed to load YAML test case: " + yamlPath);
      e.printStackTrace();
      throw new RuntimeException("Failed to load YAML test case: " + yamlPath, e);
    }
  }

  /**
   * Convert JSON input array to method parameters.
   *
   * @param inputNode JsonNode array from YAML
   * @param parameterTypes Method parameter types
   * @return Object array for test method
   */
  private static Object[] convertInput(JsonNode inputNode, Class<?>[] parameterTypes) {
    if (inputNode == null || !inputNode.isArray()) {
      return new Object[0];
    }

    int paramCount = parameterTypes.length;
    Object[] params = new Object[paramCount];

    for (int i = 0; i < paramCount && i < inputNode.size(); i++) {
      JsonNode valueNode = inputNode.get(i);
      Class<?> targetType = parameterTypes[i];

      // Convert using Jackson
      params[i] = objectMapper.convertValue(valueNode, targetType);
    }

    return params;
  }
}
