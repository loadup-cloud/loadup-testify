package com.github.loadup.testify.asserts.operator.impl;

import com.github.loadup.testify.asserts.model.MatchResult;
import com.github.loadup.testify.asserts.operator.OperatorMatcher;
import com.github.loadup.testify.asserts.util.JsonUtil;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;

import java.util.Map;

/** JSON matcher supporting partial (LENIENT) and full (STRICT) comparison modes. */
public class JsonMatcher implements OperatorMatcher {

  @Override
  public boolean support(String op) {
    return "json".equals(op);
  }

  /**
   * Match JSON strings with specified comparison mode.
   *
   * @param actual Actual JSON value
   * @param expected Expected JSON value
   * @param matchMode "full" for strict comparison, anything else for lenient (default)
   * @return MatchResult
   */
  public static MatchResult matchJson(Object actual, Object expected, Object matchMode) {
    try {
      String actJson = String.valueOf(actual);
      String expJson = String.valueOf(expected);

      // match: "full" -> STRICT (exact match), default -> LENIENT (partial match,
      // ignoring extra fields)
      JSONCompareMode mode =
          "full".equals(matchMode) ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT;

      JSONCompareResult result = JSONCompare.compareJSON(expJson, actJson, mode);

      if (result.passed()) {
        return MatchResult.pass();
      } else {
        return MatchResult.fail(actJson, expJson, "JSON Diff:\n" + result.getMessage());
      }
    } catch (Exception e) {
      return MatchResult.fail(actual, expected, "JSON parsing error: " + e.getMessage());
    }
  }

  @Override
  public MatchResult match(Object actual, Object val, Map<String, Object> config) {
    String mode = String.valueOf(config.getOrDefault("mode", "lenient"));
    // 具体的 JSON 比对逻辑...
    return JsonUtil.compare(actual, val, mode);
  }
}
