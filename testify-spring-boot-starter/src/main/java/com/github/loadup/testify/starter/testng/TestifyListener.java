package com.github.loadup.testify.starter.testng;

import com.github.loadup.testify.asserts.engine.DbAssertEngine;
import com.github.loadup.testify.core.model.TestContext;
import com.github.loadup.testify.data.engine.variable.VariableContext;
import com.github.loadup.testify.data.engine.db.SqlExecutionEngine;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestNG listener for Testify framework. Automatically executes YAML-defined setup and assertions.
 *
 * <p>Usage: Add @Listeners(TestifyListener.class) to your test class or configure in testng.xml
 */
@Slf4j
public class TestifyListener implements ITestListener {
  private final DbAssertEngine dbAssertEngine = new DbAssertEngine();

  // Added debug logs to trace TestContext loading and listener execution
  @Override
  public void onTestStart(ITestResult result) {
    try {
      TestContext tc = getTestContext(result);
      log.info("Test started: {}", result.getName());
      if (tc == null || tc.setup() == null) return;
      SqlExecutionEngine sqlEngine = SpringContextHolder.getBean(SqlExecutionEngine.class);

      // 执行 setup (传入 yamlPath 解决 CSV 寻址问题)
      sqlEngine.executeSetup(
              tc.setup(),
              VariableContext.get(),
              tc.yamlPath()
      );
    } catch (Exception e) {
      result.setStatus(ITestResult.FAILURE);
      result.setThrowable(new RuntimeException("Failed to execute test setup", e));
    }
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    try {
      TestContext tc = getTestContext(result);
      if (tc == null || tc.expect() == null) return;
      dbAssertEngine.compare(tc.expect(), VariableContext.get());

    } catch (Exception e) {
      result.setStatus(ITestResult.FAILURE);
      result.setThrowable(new RuntimeException("Failed to execute test assertions", e));
    } finally {
      cleanup();
    }
  }

  @Override
  public void onTestFailure(ITestResult result) {
    cleanup();
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    cleanup();
  }

  /** Get TestContext from test context attributes. */
  private TestContext getTestContext(ITestResult result) {
    String contextKey =
        result.getMethod().getRealClass().getName() + "." + result.getMethod().getMethodName();
    Object attr = result.getTestContext().getAttribute(contextKey);
    return attr instanceof TestContext ? (TestContext) attr : null;
  }

  /** Cleanup after test execution. Clears variable context and mock registry. */
  private void cleanup() {
    // Clear variable context for this thread
    VariableContext.clear();

    // Clear mock registry (if mocks were used)
    try {
      Class<?> mockRegistryClass =
          Class.forName("com.github.loadup.testify.mock.registry.MockRegistry");
      mockRegistryClass.getMethod("clear").invoke(null);
    } catch (Exception e) {
      // MockRegistry not available or already cleared - ignore
    }
  }
}
