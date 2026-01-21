package com.github.loadup.testify.starter.testng;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.loadup.testify.core.model.TestContext;
import com.github.loadup.testify.core.util.JsonUtil;
import com.github.loadup.testify.starter.loader.YamlLoader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

/**
 * DataProvider 职责： 1. 加载物理 YAML 文件。
 *
 * <p>2. 绑定 TestContext 到 TestNG 上下文。
 *
 * <p>3. 将原始的 input 节点转换为方法入参（不解析变量）。
 */
public class TestifyDataProvider {

  private final YamlLoader yamlLoader = new YamlLoader();

  @DataProvider(name = "testifyData")
  public Iterator<Object[]> provideTestData(ITestContext testngContext, Method method) {
    List<Object[]> dataList = new ArrayList<>();

    try {
      // 1. 根据约定路径加载原始 YAML (此时不包含变量解析逻辑)
      String className = method.getDeclaringClass().getSimpleName();
      String methodName = method.getName();
      String yamlPath = String.format("testcases/%s/%s.yaml", className, methodName);

      TestContext testContext = yamlLoader.load(yamlPath);
      if (testContext == null) {
        throw new RuntimeException("Cannot find YAML file at: " + yamlPath);
      }

      // 2. 将 TestContext 绑定到 TestNG 运行上下文中，供 TestifyBase 获取
      // 使用类名+方法名作为唯一 Key
      String contextKey = method.getDeclaringClass().getName() + "." + method.getName();
      testngContext.setAttribute(contextKey, testContext);

      // 3. 将 input 节点映射为方法参数
      JsonNode inputNode = testContext.input();
      if (inputNode != null) {
        // 处理多组数据驱动：[[p1, p2], [p3, p4]]
        if (inputNode.isArray() && inputNode.size() > 0 && inputNode.get(0).isArray()) {
          for (JsonNode row : inputNode) {
            dataList.add(convertToArgs(row, method.getParameterTypes()));
          }
        } else {
          // 处理单组参数：[p1, p2] 或单个对象
          dataList.add(convertToArgs(inputNode, method.getParameterTypes()));
        }
      } else {
        // 无参数情况
        dataList.add(new Object[method.getParameterCount()]);
      }

    } catch (Exception e) {
      throw new RuntimeException(
          "Testify DataProvider failed for " + method.getName() + ": " + e.getMessage(), e);
    }

    return dataList.iterator();
  }

  /** 将 JsonNode 转换为 Java 方法参数。 注意：此时参数值可能包含未解析的占位符字符串，如 "${faker.name()}"。 */
  private static Object[] convertToArgs(JsonNode inputNode, Class<?>[] parameterTypes) {
    Object[] args = new Object[parameterTypes.length];
    for (int i = 0; i < parameterTypes.length; i++) {
      if (inputNode.isArray() && i < inputNode.size()) {
        // 如果入参是数组，按索引映射
        args[i] = JsonUtil.convertValue(inputNode.get(i), parameterTypes[i]);
      } else if (!inputNode.isArray() && i == 0) {
        // 如果入参是单个对象且方法只有一个参数，直接映射
        args[i] = JsonUtil.convertValue(inputNode, parameterTypes[i]);
      } else {
        args[i] = null;
      }
    }
    return args;
  }
}
