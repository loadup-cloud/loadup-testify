package com.github.loadup.testify.starter.testng;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.loadup.testify.core.model.TestContext;
import com.github.loadup.testify.data.engine.variable.VariableContext;
import com.github.loadup.testify.data.engine.variable.VariableEngine;
import com.github.loadup.testify.starter.loader.YamlLoader;
import java.lang.reflect.Method;
import java.util.*;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

/**
 * TestNG Data Provider for Testify framework. Automatically converts YAML input data to test method
 * parameters.
 *
 * <p>Usage: @Test(dataProvider = "testifyData", dataProviderClass = TestifyDataProvider.class)
 * public void testMethod(String param1, int param2) { ... }
 */
public class TestifyDataProvider {

  private static final ObjectMapper objectMapper = new ObjectMapper();
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
      String yamlPath = String.format("testcases/%s/%s.yaml", className, methodName);

      TestContext testContext = yamlLoader.load(yamlPath);
      if (testContext == null) {
        throw new RuntimeException("Cannot find YAML file at: " + yamlPath);
      }
      // 2. 解析全局变量块,处理 null 情况
      Map<String, String> rawVars =
          testContext.variables() != null
              ? objectMapper.convertValue(
                  testContext.variables(), new TypeReference<Map<String, String>>() {})
              : Collections.emptyMap();
      // 解析变量依赖关系
      Map<String, Object> resolvedVars = variableEngine.resolveVariables(rawVars);
      // 3. 将变量存入当前线程上下文,，确保后续 Hook 逻辑能拿到这些变量
      VariableContext.set(resolvedVars);

      // 4. 将 TestContext 绑定到 TestNG 运行上下文中，供 Listener 使用
      String contextKey = method.getDeclaringClass().getName() + "." + method.getName();
      testngContext.setAttribute(contextKey, testContext);

      // 5. 解析并转换 Input 参数
      if (testContext.input() != null) {
        // 递归替换 input 里的所有占位符
        JsonNode resolvedInput = variableEngine.resolveJsonNode(testContext.input(), resolvedVars);

        // 支持多组数据驱动 (若 input 是 Array 的 Array)
        if (resolvedInput.isArray()) {
          // 如果第一个元素是数组，视为多组参数：[[p1, p2], [p3, p4]]
          if (resolvedInput.size() > 0 && resolvedInput.get(0).isArray()) {
            for (JsonNode row : resolvedInput) {
              dataList.add(convertToArgs(row, method.getParameterTypes()));
            }
          } else {
            // 否则视为单组参数：[p1, p2]
            dataList.add(convertToArgs(resolvedInput, method.getParameterTypes()));
          }
        }
      } else {
        dataList.add(new Object[method.getParameterCount()]);
      }

    } catch (Exception e) {
      throw new RuntimeException(
          "Testify DataProvider failed for " + method.getName() + ": " + e.getMessage(), e);
    }

    return dataList.iterator();
  }

  /** 将解析后的 JsonNode 转换为 Java 方法参数 */
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
}
