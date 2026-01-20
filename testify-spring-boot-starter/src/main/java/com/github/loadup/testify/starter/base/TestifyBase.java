package com.github.loadup.testify.starter.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.loadup.testify.asserts.engine.DbAssertEngine;
import com.github.loadup.testify.asserts.engine.ResponseAssertEngine;
import com.github.loadup.testify.core.model.TestContext;
import com.github.loadup.testify.data.engine.db.SqlExecutionEngine;
import com.github.loadup.testify.data.engine.variable.VariableContext;
import com.github.loadup.testify.starter.config.TestifyAutoConfiguration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.Reporter;

/**
 * Base class for Testify integration tests. Provides helper methods for database assertions and
 * common test operations.
 */
@Getter
@Setter
@SpringBootTest
@ContextConfiguration(classes = TestifyAutoConfiguration.class)
public abstract class TestifyBase extends AbstractTestNGSpringContextTests {

  @Autowired private SqlExecutionEngine sqlEngine;
  @Autowired private ResponseAssertEngine responseAssertEngine;
  @Autowired private DbAssertEngine dbAssertEngine;

  /**
   * 主动编排方法：掌握测试全过程
   *
   * @param action 执行业务逻辑的 Lambda
   * @param <T> 业务返回的结果类型
   */
  protected <T> void runTest(Supplier<T> action) {
    // 1. 获取 DataProvider 存入的 TestContext
    ITestResult testResult = Reporter.getCurrentTestResult();
    String contextKey = testResult.getMethod().getQualifiedName();
    TestContext tc = (TestContext) testResult.getTestContext().getAttribute(contextKey);

    if (tc == null) {
      throw new RuntimeException("未能找到测试上下文，请确保使用了 testifyData DataProvider: " + contextKey);
    }
    List<String> reportList = new ArrayList<>();
    List<Throwable> businessErrors = new ArrayList<>();
    try {
      // 2. Setup: 数据库准备
      if (tc.setup() != null) {
        sqlEngine.executeSetup(tc.setup(), VariableContext.get(), tc.yamlPath());
      }

      // 3. Test: 执行被测 Bean 方法
      // 异常无需 catch，直接抛给 TestNG 记录 FAILURE
      T actualResponse = null;
      try {
        actualResponse = action.get();
      } catch (Throwable e) {
        businessErrors.add(e); // 记录业务异常
      }

      // 4. Assert: 结果比对 (Response & Database)
      if (tc.expect() != null) {
        JsonNode expectNode = tc.expect();

        // 4.1 API 响应对比
        if (expectNode.has("response")) {
          try {
            responseAssertEngine.compare(expectNode.get("response"), actualResponse, VariableContext.get());
          } catch (AssertionError e) {
            reportList.add(e.getMessage()); // 提取已经构建好的表格字符串
          }
        }

        // 4.2 数据库数据对比
        if (expectNode.has("database")) {
          try {
            dbAssertEngine.compare(expectNode.get("database"), VariableContext.get());
          } catch (AssertionError e) {
            reportList.add(e.getMessage()); // 提取数据库 Diff 表格
          }
        }
      }
    } finally {
      // 5. Cleanup: 保证线程变量被清理
      VariableContext.clear();
    }
    // 5. 汇总并抛出
    handleFinalReports(businessErrors, reportList);
  }

  private void handleFinalReports(List<Throwable> businessErrors, List<String> reportList) {
    // 优先抛出业务异常
    if (!businessErrors.isEmpty()) {
      throw new RuntimeException("业务方法执行异常", businessErrors.get(0));
    }

    // 如果有断言错误，将所有表格拼接
    if (!reportList.isEmpty()) {
      // 使用 String.join 分隔多个表格
      String fullReport = String.join("\n", reportList);
      throw new AssertionError(fullReport);
    }
  }
}
