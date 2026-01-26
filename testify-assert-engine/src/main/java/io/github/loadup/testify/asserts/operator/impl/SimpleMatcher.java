package io.github.loadup.testify.asserts.operator.impl;

import io.github.loadup.testify.asserts.model.MatchResult;
import java.math.BigDecimal;
import java.util.Objects;

/** Simple equality and inequality matcher. Handles null values properly. */
public class SimpleMatcher {
  public static MatchResult eq(Object actual, Object expected) {
    if (actual == null && expected == null) return MatchResult.pass();
    if (actual == null || expected == null)
      return MatchResult.fail(actual, expected, "One of the values is null");

    // 核心：处理数字和字符串的等值比对（如 Long 123 vs Integer 123）
    if (actual instanceof Number && expected instanceof Number) {
      return new BigDecimal(actual.toString()).compareTo(new BigDecimal(expected.toString())) == 0
          ? MatchResult.pass()
          : MatchResult.fail(actual, expected, "Numeric values not equal");
    }

    return Objects.equals(actual, expected)
        ? MatchResult.pass()
        : MatchResult.fail(actual, expected, "Values are not equal");
  }

  public static MatchResult ne(Object actual, Object expected) {
    MatchResult eqResult = eq(actual, expected);
    return !eqResult.isPassed()
        ? MatchResult.pass()
        : MatchResult.fail(actual, expected, "Values should not be equal");
  }
}
