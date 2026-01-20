package com.github.loadup.testify.starter.testng;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.loadup.testify.asserts.engine.DbAssertEngine;
import com.github.loadup.testify.asserts.engine.ResponseAssertEngine;
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
        if (tc == null || tc.expect() == null) return;
        // 1. 自动获取返回值作为实际响应 (Actual Response)
        Object actualResponse = null;//testResult.getReturnValue();
        JsonNode expect = tc.expect(); // 这是 expect 根节点
        // 1. 校验 Response
        if (expect.has("response") ) {
          log.info(">>> [Testify] 检测到响应值预期，开始比对...");
          new ResponseAssertEngine()
              .compare(expect.get("response"), actualResponse, VariableContext.get());
        }
        if (expect.has("database")) {
          log.info(">>> [Testify] 检测到数据库预期，开始比对...");
          new DbAssertEngine().compare(expect.get("database"), VariableContext.get());
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
