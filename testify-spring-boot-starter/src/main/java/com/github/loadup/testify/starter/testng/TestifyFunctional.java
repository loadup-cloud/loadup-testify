package com.github.loadup.testify.starter.testng;

import com.github.loadup.testify.asserts.engine.DbAssertEngine;
import com.github.loadup.testify.asserts.engine.ResponseAssertEngine;
import com.github.loadup.testify.core.model.TestContext;
import com.github.loadup.testify.core.util.SpringContextHolder;
import com.github.loadup.testify.data.engine.db.SqlExecutionEngine;
import com.github.loadup.testify.data.engine.variable.VariableContext;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

public interface TestifyFunctional extends IHookable {

  @Override
  default void run(IHookCallBack callBack, ITestResult testResult) {
    // 1. 获取 DataProvider 存入的上下文
    TestContext tc = lookupTestContext(testResult);

    try {
      // 2. 执行 Setup (数据准备)
      if (tc != null && tc.setup() != null) {
        SqlExecutionEngine sqlEngine = SpringContextHolder.getBean(SqlExecutionEngine.class);
        sqlEngine.executeSetup(tc.setup(), VariableContext.get(), tc.yamlPath());
      }

      /**
       * 3. 核心修复：执行测试方法并获取返回值 注意：AbstractTestNGSpringContextTests 的子类必须通过这种方式执行 以确保 Spring
       * 的测试生命周期正常运行。
       */
      callBack.runTestMethod(testResult);

      // 4. 重点：从 testResult 尝试获取结果（如果是 Hook 触发，部分版本支持在此获取）
      // 或者通过显式的 ResultHolder 获取
      Object actualResponse = testResult.getAttribute("actualResponse");

      // 5. 断言闭环
      if (testResult.isSuccess() && tc != null && tc.expect() != null) {
        // A. 响应断言
        if (tc.expect().has("response")) {
          new ResponseAssertEngine()
              .compare(tc.expect().get("response"), actualResponse, VariableContext.get());
        }
        // B. 数据库断言
        if (tc.expect().has("database")) {
          new DbAssertEngine().compare(tc.expect().get("database"), VariableContext.get());
        }
      }
    } catch (Throwable e) {
      testResult.setThrowable(e);
      testResult.setStatus(ITestResult.FAILURE);
    } finally {
      VariableContext.clear();
    }
  }

  private TestContext lookupTestContext(ITestResult result) {
    String contextKey = result.getMethod().getQualifiedName();
    return (TestContext) result.getTestContext().getAttribute(contextKey);
  }
}
