package com.github.loadup.testify.starter.base;

import com.github.loadup.testify.asserts.facade.AssertionFacade;
import com.github.loadup.testify.core.model.TestContext;
import com.github.loadup.testify.data.engine.db.SqlExecutionEngine;
import com.github.loadup.testify.data.engine.variable.VariableContext;
import com.github.loadup.testify.starter.config.TestifyAutoConfiguration;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.function.ThrowingSupplier;
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
  @Autowired private AssertionFacade assertionFacade;

  /**
   * 主动编排方法：掌握测试全过程
   *
   * @param action 执行业务逻辑的 Lambda
   * @param <T> 业务返回的结果类型
   */
  protected final <T> void runTest(ThrowingSupplier<T> action) {
    // 1. 获取 DataProvider 存入的 TestContext
    ITestResult testResult = Reporter.getCurrentTestResult();
    String contextKey = testResult.getMethod().getQualifiedName();
    TestContext tc = (TestContext) testResult.getTestContext().getAttribute(contextKey);

    if (tc == null) {
      throw new RuntimeException("未能找到测试上下文，请确保使用了 testifyData DataProvider: " + contextKey);
    }
    List<String> reportList = new ArrayList<>();
    Throwable businessError = null;
    try {
      // 2. Setup: 数据库准备
      if (tc.setup() != null) {
        sqlEngine.executeSetup(tc.setup(), VariableContext.get(), tc.yamlPath());
      }

      // 3. Test: 执行被测 Bean 方法
      // 异常无需 catch，直接抛给 TestNG 记录 FAILURE
      T result = null;
      try {
        result = action.get();
      } catch (Throwable e) {
        businessError = e; // 记录业务异常
      }

      // 4. Assert: 结果比对 (Response & Database)
      // 3. 统一断言：一行代码解决所有引擎调用
      if (tc.expect() != null) {
        List<String> reports = assertionFacade.executeAll(tc.expect(), result, businessError);

        // 如果预期没有异常但业务报错了，手动补一个错误报告
        if (businessError != null && !tc.expect().has("exception")) {
          reports.add(0, "❌ 业务意外报错: " + businessError.getClass().getSimpleName());
        }
        // 5. 汇总并抛出
        handleFinalReports(reports); // 抛出汇总异常
      }
    } finally {
      // 5. Cleanup: 保证线程变量被清理
      VariableContext.clear();
    }
  }

  private void handleFinalReports(List<String> reportList) {
    // 如果有断言错误，将所有表格拼接
    if (!reportList.isEmpty()) {
      // 使用 String.join 分隔多个表格
      String fullReport = String.join("\n", reportList);
      throw new AssertionError(fullReport);
    }
  }
}
