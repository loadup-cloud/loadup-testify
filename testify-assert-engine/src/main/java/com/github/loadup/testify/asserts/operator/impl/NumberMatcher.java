package com.github.loadup.testify.asserts.operator.impl;

import com.github.loadup.testify.asserts.model.MatchResult;
import com.github.loadup.testify.asserts.operator.OperatorMatcher;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Numeric comparison matcher supporting gt, ge, lt, le operations.
 * Handles various numeric types through BigDecimal conversion.
 */
public class NumberMatcher implements OperatorMatcher {

    /**
     * Compare two numeric values based on operator.
     * 
     * @param actual   Actual value from database
     * @param expected Expected value
     * @param operator Comparison operator: gt, ge, lt, le
     * @return MatchResult
     */
    public static MatchResult compare(Object actual, Object expected, String operator) {
        try {
            BigDecimal actualNum = toBigDecimal(actual);
            BigDecimal expectedNum = toBigDecimal(expected);

            if (actualNum == null || expectedNum == null) {
                return MatchResult.fail(actual, expected,
                        "Cannot compare non-numeric values: actual=" + actual + ", expected=" + expected);
            }

            int comparison = actualNum.compareTo(expectedNum);
            boolean passed = switch (operator) {
                case "gt" -> comparison > 0;
                case "ge" -> comparison >= 0;
                case "lt" -> comparison < 0;
                case "le" -> comparison <= 0;
                default -> throw new IllegalArgumentException("Unknown operator: " + operator);
            };

            if (passed) {
                return MatchResult.pass();
            } else {
                String message = String.format("Numeric comparison failed: %s %s %s is false",
                        actual, operatorSymbol(operator), expected);
                return MatchResult.fail(actual, expected, message);
            }
        } catch (Exception e) {
            return MatchResult.fail(actual, expected,
                    "Error during numeric comparison: " + e.getMessage());
        }
    }

    /**
     * Convert various numeric types to BigDecimal for precise comparison.
     */
    private static BigDecimal toBigDecimal(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof BigDecimal bd) {
            return bd;
        }

        if (value instanceof Integer || value instanceof Long ||
                value instanceof Short || value instanceof Byte) {
            return BigDecimal.valueOf(((Number) value).longValue());
        }

        if (value instanceof Float || value instanceof Double) {
            return BigDecimal.valueOf(((Number) value).doubleValue());
        }

        // Try to parse string
        if (value instanceof String str) {
            try {
                return new BigDecimal(str);
            } catch (NumberFormatException e) {
                return null;
            }
        }

        // Try generic Number conversion
        if (value instanceof Number num) {
            return BigDecimal.valueOf(num.doubleValue());
        }

        return null;
    }

    private static String operatorSymbol(String operator) {
        return switch (operator) {
            case "gt" -> ">";
            case "ge" -> ">=";
            case "lt" -> "<";
            case "le" -> "<=";
            default -> operator;
        };
    }

    @Override
    public String match(Object actual, Object expected) {
        if (!(expected instanceof Map<?, ?> config)) {
            return "NumberMatcher requires operator config";
        }

        String op = String.valueOf(config.get("op"));
        Object val = config.get("val");

        MatchResult result = compare(actual, val, op);
        return result.isPassed() ? null : result.message();
    }

    @Override
    public boolean support(Object expectedConfig) {
        if (!(expectedConfig instanceof Map<?, ?> config)) {
            return false;
        }
        String op = String.valueOf(config.get("op"));
        return "gt".equals(op) || "ge".equals(op) || "lt".equals(op) || "le".equals(op);
    }
}
