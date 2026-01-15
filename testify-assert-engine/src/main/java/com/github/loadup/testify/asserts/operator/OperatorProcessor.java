
package com.github.loadup.testify.asserts.operator;

import com.github.loadup.testify.asserts.model.MatchResult;
import com.github.loadup.testify.asserts.operator.impl.*;

import java.util.Map;

/**
 * Central processor for all operator-based assertions.
 * Uses JDK 21 pattern matching for switch expressions.
 */
public class OperatorProcessor {

    public static MatchResult process(Object actual, Object expected) {
        // If expected is not a Map, default to simple equality verification
        if (!(expected instanceof Map<?, ?> config)) {
            return SimpleMatcher.eq(actual, expected);
        }

        String op = String.valueOf(config.get("op"));
        Object val = config.get("val");

        return switch (op) {
            case "eq" -> SimpleMatcher.eq(actual, val);
            case "ne" -> SimpleMatcher.ne(actual, val);
            case "gt" -> NumberMatcher.compare(actual, val, "gt");
            case "ge" -> NumberMatcher.compare(actual, val, "ge");
            case "lt" -> NumberMatcher.compare(actual, val, "lt");
            case "le" -> NumberMatcher.compare(actual, val, "le");
            case "contains" -> {
                boolean match = String.valueOf(actual).contains(String.valueOf(val));
                yield match ? MatchResult.pass()
                        : MatchResult.fail(actual, val, "String does not contain expected substring");
            }
            case "regex" -> RegexMatcher.matchRegex(actual, val);
            case "approx" -> {
                ApproxTimeMatcher matcher = new ApproxTimeMatcher();
                String error = matcher.match(actual, expected);
                yield error == null ? MatchResult.pass()
                        : MatchResult.fail(actual, val, error);
            }
            case "json" -> {
                Object matchMode = config.get("mode");
                yield JsonMatcher.matchJson(actual, val, matchMode);
            }
            default -> SimpleMatcher.eq(actual, val);
        };
    }
}