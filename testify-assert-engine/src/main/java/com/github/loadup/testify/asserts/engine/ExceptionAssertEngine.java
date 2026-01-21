package com.github.loadup.testify.asserts.engine;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.loadup.testify.asserts.diff.DiffReportBuilder;
import com.github.loadup.testify.asserts.model.FieldDiff;
import com.github.loadup.testify.asserts.model.MatchResult;
import com.github.loadup.testify.asserts.operator.OperatorProcessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExceptionAssertEngine implements TestifyAssertEngine {
  @Override
  public String supportKey() {
    return "exception";
  }

  @Override
  public void compare(
      JsonNode expectEx, Object actual, Map<String, Object> context, List<String> reportList) {
    if (expectEx == null || actual == null) {
      return;
    }
    if (!(actual instanceof Throwable actualEx)) {
      return;
    }
    // 自动获取最底层的原始异常 (Root Cause)
    Throwable rootCause = getRootCause(actualEx);
    List<FieldDiff> diffs = new ArrayList<>();

    // 1. 校验异常类型 (支持简写)
    if (expectEx.has("type")) {
      String expectType = expectEx.get("type").asText();
      if (!isTypeMatch(expectType, rootCause)) {
        diffs.add(
            new FieldDiff(
                "exception.type",
                expectType,
                rootCause.getClass().getSimpleName(),
                "Exception type mismatch"));
      }
    }

    // 2. 校验异常消息
    if (expectEx.has("message")) {
      JsonNode messageNode = expectEx.get("message");
      String actualMsg = rootCause.getMessage();
      Object expectedObj = resolveExpectedValue(messageNode);
      MatchResult result = OperatorProcessor.process(actualMsg, expectedObj);

      if (!result.isPassed()) {
        diffs.add(new FieldDiff("exception.message", expectedObj, actualMsg, result.message()));
      }
    }

    if (!diffs.isEmpty()) {
      throw new AssertionError(DiffReportBuilder.build("Exception Assertion", diffs));
    }
  }

  /** 智能类型匹配：支持全类名或简单类名 */
  private boolean isTypeMatch(String expectType, Throwable actualEx) {
    String fullClassName = actualEx.getClass().getName();
    String simpleName = actualEx.getClass().getSimpleName();

    // 1. 完全一致 2. 简单类名一致
    return fullClassName.equals(expectType) || simpleName.equalsIgnoreCase(expectType);
  }

  /** 递归获取根异常 */
  private Throwable getRootCause(Throwable t) {
    Throwable cause = t;
    while (cause.getCause() != null && cause.getCause() != cause) {
      cause = cause.getCause();
    }
    return cause;
  }
}
