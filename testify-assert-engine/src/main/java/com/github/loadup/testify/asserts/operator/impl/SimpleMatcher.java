package com.github.loadup.testify.asserts.operator.impl;

import com.github.loadup.testify.asserts.model.MatchResult;
import com.github.loadup.testify.asserts.operator.OperatorMatcher;

import java.util.Map;
import java.util.Objects;

/**
 * Simple equality and inequality matcher.
 * Handles null values properly.
 */
public class SimpleMatcher implements OperatorMatcher {

    /**
     * Perform equality check with null-safe comparison.
     */
    public static MatchResult eq(Object actual, Object expected) {
        if (Objects.equals(actual, expected)) {
            return MatchResult.pass();
        }

        // Special handling for numeric types
        if (actual != null && expected != null) {
            if (actual instanceof Number && expected instanceof Number) {
                // Convert both to double for comparison
                double actualNum = ((Number) actual).doubleValue();
                double expectedNum = ((Number) expected).doubleValue();
                if (Math.abs(actualNum - expectedNum) < 0.0000001) {
                    return MatchResult.pass();
                }
            }

            // String comparison (case-sensitive by default)
            if (String.valueOf(actual).equals(String.valueOf(expected))) {
                return MatchResult.pass();
            }
        }

        return MatchResult.fail(actual, expected, "Values are not equal");
    }

    /**
     * Perform inequality check.
     */
    public static MatchResult ne(Object actual, Object expected) {
        if (!Objects.equals(actual, expected)) {
            return MatchResult.pass();
        }
        return MatchResult.fail(actual, expected, "Values are equal but expected to be different");
    }

    @Override
    public String match(Object actual, Object expected) {
        MatchResult result = eq(actual, expected);
        return result.isPassed() ? null : result.message();
    }

    @Override
    public boolean support(Object expectedConfig) {
        // Supports plain values (not operator configs)
        return !(expectedConfig instanceof Map);
    }
}
