package com.github.loadup.testify.starter.base;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.loadup.testify.asserts.facade.AssertionFacade;
import com.github.loadup.testify.core.model.TestContext;
import com.github.loadup.testify.core.util.JsonUtil;
import com.github.loadup.testify.core.variable.VariableContext;
import com.github.loadup.testify.data.engine.db.SqlExecutionEngine;
import com.github.loadup.testify.data.engine.variable.VariableEngine;
import com.github.loadup.testify.mock.engine.MockEngine;
import com.github.loadup.testify.starter.util.SpringContextHolder;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;

@Slf4j
@SpringBootTest
public abstract class TestifyBase extends AbstractTestNGSpringContextTests {

  @Autowired protected AssertionFacade assertionFacade;

  @Autowired protected SqlExecutionEngine sqlExecutionEngine;
  @Autowired protected MockEngine mockEngine;

  private TestContext currentTestContext;

  /** 在每个测试方法执行前，从 TestNG 上下文中提取 DataProvider 加载的 TestContext */
  @BeforeMethod
  public void prepareContext(Method method, ITestContext testngContext) {
    String contextKey = method.getDeclaringClass().getName() + "." + method.getName();
    this.currentTestContext = (TestContext) testngContext.getAttribute(contextKey);
  }

  /**
   * 核心编排方法
   *
   * @param action 业务逻辑闭包
   * @param <T> 返回值类型
   */
  protected <T> void runTest(Supplier<T> action) {
    // 1. 获取 Spring 容器中的 VariableEngine
    VariableEngine variableEngine = SpringContextHolder.getBean(VariableEngine.class);

    try {
      // --- 阶段 A: 变量与环境准备 ---

      // 1.1 解析 variables 块（处理 ${faker}, ${time} 等实时函数）
      Map<String, String> rawVars =
          currentTestContext.variables() != null
              ? JsonUtil.convertValue(currentTestContext.variables(), new TypeReference<>() {})
              : Map.of();

      // resolveVariables 内部会处理变量间的依赖及函数执行
      Map<String, Object> resolvedVars = variableEngine.resolveVariables(rawVars);

      // 1.2 存入线程上下文，供后续所有 Engine 使用
      VariableContext.set(resolvedVars);
      log.info("Resolved Variables: {}", resolvedVars);
      // 1.3 应用 Mock 配置，自动解析 Mock 配置中的变量
      // 传入 resolvedVars 使得 Mock 的返回值支持 ${faker} 等动态函数
      if (currentTestContext.mocks() != null) {
        mockEngine.applyMocks(currentTestContext.mocks(), resolvedVars);
      }
      System.out.println("Context Bean Hash: " + System.identityHashCode(applicationContext.getBean("orderService")));

      // 1.4 执行数据准备（Setup SQL），自动解析 SQL 中的变量
      if (currentTestContext.setup() != null) {
        sqlExecutionEngine.execute(
            currentTestContext.setup(), resolvedVars, currentTestContext.yamlPath());
      }

      // --- 阶段 B: 业务执行 ---
      T actualResult = null;
      Throwable businessError = null;
      try {
        actualResult = action.get();
      } catch (Throwable e) {
        businessError = e;
      }

      // --- 阶段 C: 断言编排 ---

      // 将实际结果和异常传入断言门面
      // 门面内部会根据 currentTestContext.expect() 分发给不同的 AssertEngine
      List<String> reports =
          assertionFacade.executeAll(currentTestContext.expect(), actualResult, businessError);

      // --- 阶段 D: 结果汇总 ---
      processReports(reports);

    } catch (Exception e) {
      log.error("Testify execution failed", e);
      throw new RuntimeException("Testify execution error", e);
    } finally {
      // 必须清理，防止 ThreadLocal 污染
      //mockEngine.resetAllMocks();
      VariableContext.clear();
    }
  }

  /** 处理断言报告，如果有失败则汇总抛出 */
  private void processReports(List<String> reports) {
    if (reports == null || reports.isEmpty()) {
      return;
    }

    StringBuilder sb = new StringBuilder("\n--- Testify Assertion Reports ---\n");
    boolean hasFailure = false;
    for (String report : reports) {
      sb.append(report).append("\n");
      if (report.contains("❌")) {
        hasFailure = true;
      }
    }

    String finalReport = sb.toString();
    Reporter.log(finalReport); // 输出到 TestNG 报表

    if (hasFailure) {
      throw new AssertionError(finalReport);
    }
  }

  /** 提供一个静态便捷方法给业务代码使用，尽量缩短代码长度 */
  @SuppressWarnings("unchecked")
  protected <V> V val(V raw) {
    if (raw == null) return null;
    VariableEngine ve = SpringContextHolder.getBean(VariableEngine.class);

    // 从 ThreadLocal 拿到已经解析好的 variables 映射表
    Map<String, Object> currentContext = VariableContext.get();

    if (raw instanceof String str) {
      // 调用 evaluate。此时如果 str 是 "${userId}"，且 variables 里定义了 userId，
      // evaluate 会优先从 currentContext 中获取。
      return (V) ve.evaluate(str, currentContext);
    }
    return raw;
  }
}
