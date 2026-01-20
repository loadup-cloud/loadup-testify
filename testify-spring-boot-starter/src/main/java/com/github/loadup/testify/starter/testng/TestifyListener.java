package com.github.loadup.testify.starter.testng;

import com.github.loadup.testify.asserts.engine.DbAssertEngine;
import com.github.loadup.testify.core.model.TestContext;
import com.github.loadup.testify.core.util.SpringContextHolder;
import com.github.loadup.testify.data.engine.db.SqlExecutionEngine;
import com.github.loadup.testify.data.engine.variable.VariableContext;
import lombok.extern.slf4j.Slf4j;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

@Slf4j
public class TestifyListener implements IInvokedMethodListener {

  @Override
  public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    if (!method.isTestMethod()) return;

    TestContext tc = lookup(testResult);
    if (tc != null && tc.setup() != null) {
      log.info(">>> [Testify] 开始 Setup: {}", method.getTestMethod().getMethodName());
      SqlExecutionEngine sqlEngine = SpringContextHolder.getBean(SqlExecutionEngine.class);
      // 传入 VariableContext.get() 确保 DataProvider 解析的变量生效
      sqlEngine.executeSetup(tc.setup(), VariableContext.get(), tc.yamlPath());
    }
  }

  @Override
  public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
    if (!method.isTestMethod()) return;

    try {
      // 核心逻辑：只有业务代码执行成功（Status=SUCCESS）时，才运行断言
      if (testResult.isSuccess()) {
        TestContext tc = lookup(testResult);
        if (tc != null && tc.expect() != null) {
          log.info(">>> [Testify] 开始数据库断言...");
          DbAssertEngine dbAssertEngine = new DbAssertEngine();
          dbAssertEngine.compare(tc.expect(), VariableContext.get());
        }
      }
    } catch (Throwable e) {
      // 【关键操作】在这里改写状态，不会产生 IDEA 副本
      testResult.setStatus(ITestResult.FAILURE);
      testResult.setThrowable(e);
      log.error(">>> [Testify] 断言失败或运行异常: {}", e.getMessage());
    } finally {
      VariableContext.clear();
    }
  }

  private TestContext lookup(ITestResult result) {
    // 必须与 TestifyDataProvider 中的 contextKey 逻辑完全一致
    String contextKey = result.getMethod().getQualifiedName();
    return (TestContext) result.getTestContext().getAttribute(contextKey);
  }
}