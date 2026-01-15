package com.github.loadup.testify.asserts.operator.impl;

import com.github.loadup.testify.asserts.model.MatchResult;
import com.github.loadup.testify.asserts.operator.OperatorMatcher;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Regular expression matcher with pattern caching for performance.
 */
public class RegexMatcher implements OperatorMatcher {

    // Cache compiled patterns to avoid repeated compilation
    private static final Map<String, Pattern> PATTERN_CACHE = new ConcurrentHashMap<>();
    private static final int MAX_CACHE_SIZE = 100;

    /**
     * Match actual value against regex pattern.
     */
    public static MatchResult matchRegex(Object actual, Object regexPattern) {
        if (actual == null) {
            return MatchResult.fail(null, regexPattern, "Actual value is null, cannot match against regex");
        }

        if (regexPattern == null) {
            return MatchResult.fail(actual, null, "Regex pattern is null");
        }

        String actualStr = String.valueOf(actual);
        String patternStr = String.valueOf(regexPattern);

        try {
            Pattern pattern = getPattern(patternStr);
            boolean matches = pattern.matcher(actualStr).matches();

            if (matches) {
                return MatchResult.pass();
            } else {
                return MatchResult.fail(actual, regexPattern,
                        String.format("String '%s' does not match regex pattern '%s'", actualStr, patternStr));
            }
        } catch (PatternSyntaxException e) {
            return MatchResult.fail(actual, regexPattern,
                    "Invalid regex pattern: " + e.getMessage());
        }
    }

    /**
     * Get compiled pattern from cache or compile and cache it.
     */
    private static Pattern getPattern(String regex) {
        return PATTERN_CACHE.computeIfAbsent(regex, r -> {
            // Prevent cache from growing too large
            if (PATTERN_CACHE.size() > MAX_CACHE_SIZE) {
                PATTERN_CACHE.clear();
            }
            return Pattern.compile(r);
        });
    }

    @Override
    public String match(Object actual, Object expected) {
        if (!(expected instanceof Map<?, ?> config)) {
            return "RegexMatcher requires operator config";
        }

        Object val = config.get("val");
        MatchResult result = matchRegex(actual, val);
        return result.isPassed() ? null : result.message();
    }

    @Override
    public boolean support(Object expectedConfig) {
        if (!(expectedConfig instanceof Map<?, ?> config)) {
            return false;
        }
        return "regex".equals(config.get("op"));
    }
}
