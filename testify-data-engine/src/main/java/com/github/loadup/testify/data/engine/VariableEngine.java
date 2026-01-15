package com.github.loadup.testify.data.engine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.datafaker.Faker;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Variable resolution engine supporting:
 * - Datafaker expressions: ${faker.name.firstName}
 * - Time functions with offsets: ${time.now('+1d')}, ${time.now('-2h')}
 * - Custom functions: ${fn.uuid()}, ${fn.random(1, 100)}
 * - Variable cross-references with dependency resolution
 */
public class VariableEngine {

    private final Faker faker = new Faker();
    private final ExpressionParser spelParser = new SpelExpressionParser();

    // Pattern to extract time offset like '+1d' or '-2h'
    private static final Pattern TIME_OFFSET_PATTERN = Pattern.compile("([+-])(\\d+)([hdms])");

    /**
     * Resolve variables block with dependency ordering.
     * Variables can reference other variables, and this method ensures
     * they are resolved in the correct order.
     */
    public Map<String, Object> resolveVariables(Map<String, String> rawVariables) {
        Map<String, Object> context = new LinkedHashMap<>();
        if (rawVariables == null || rawVariables.isEmpty()) {
            return context;
        }

        // Create SpEL context with built-in functions
        StandardEvaluationContext spelContext = new StandardEvaluationContext();
        spelContext.setVariable("fn", new FunctionHelper());
        spelContext.setVariable("time", new TimeHelper());

        // Resolve variables in order, allowing cross-references
        // Simple implementation: iterate multiple times until all resolved
        Set<String> resolved = new HashSet<>();
        int maxIterations = rawVariables.size() + 1;
        int iteration = 0;

        while (resolved.size() < rawVariables.size() && iteration < maxIterations) {
            for (Map.Entry<String, String> entry : rawVariables.entrySet()) {
                String key = entry.getKey();
                if (resolved.contains(key)) {
                    continue;
                }

                String expression = entry.getValue();

                // Check if this expression depends on unresolved variables
                if (hasDependencies(expression, rawVariables.keySet(), resolved)) {
                    continue; // Skip for now, will resolve in next iteration
                }

                try {
                    // 1. Parse Faker expressions
                    String evaluated = parseFaker(expression);

                    // 2. Parse SpEL with current context
                    Object finalValue = evaluateSpel(evaluated, spelContext, context);

                    context.put(key, finalValue);
                    spelContext.setVariable(key, finalValue);
                    resolved.add(key);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to resolve variable: " + key + " = " + expression, e);
                }
            }
            iteration++;
        }

        // Check for circular dependencies
        if (resolved.size() < rawVariables.size()) {
            Set<String> unresolved = new HashSet<>(rawVariables.keySet());
            unresolved.removeAll(resolved);
            throw new RuntimeException("Circular dependency or unresolvable variables detected: " + unresolved);
        }

        return context;
    }

    /**
     * Replace placeholders in a string with resolved variable values.
     */
    public String replacePlaceholders(String text, Map<String, Object> context) {
        if (text == null) {
            return null;
        }
        StringSubstitutor sub = new StringSubstitutor(context);
        sub.setEnableSubstitutionInVariables(true); // Support nested substitutions
        return sub.replace(text);
    }

    // --- Internal Helper Methods ---

    /**
     * Check if an expression depends on variables that are not yet resolved.
     */
    private boolean hasDependencies(String expression, Set<String> allVarNames, Set<String> resolvedVars) {
        for (String varName : allVarNames) {
            if (!resolvedVars.contains(varName) && expression.contains("${" + varName + "}")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Parse Datafaker expressions.
     * Converts ${faker.name.firstName} -> #{Name.firstName} and evaluates
     */
    private String parseFaker(String expression) {
        if (expression == null || !expression.contains("faker.")) {
            return expression;
        }

        // Extract faker expression pattern: ${faker.XXX}
        Pattern fakerPattern = Pattern.compile("\\$\\{faker\\.([^}]+)}");
        Matcher matcher = fakerPattern.matcher(expression);

        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String fakerExp = matcher.group(1); // e.g., "name.firstName"
            // Convert to Datafaker format: #{Name.firstName}
            String fakerResult = faker.expression("#{" + fakerExp + "}");
            matcher.appendReplacement(result, java.util.regex.Matcher.quoteReplacement(fakerResult));
        }
        matcher.appendTail(result);

        return result.toString();
    }

    /**
     * Evaluate SpEL expressions with the given context.
     */
    private Object evaluateSpel(String expression, StandardEvaluationContext spelContext,
            Map<String, Object> resolvedVars) {
        // If it's a plain string without ${...}, return as-is
        if (expression == null || (!expression.contains("${") && !expression.contains("#{"))) {
            return expression;
        }

        // First, replace any already-resolved variables
        String preprocessed = replacePlaceholders(expression, resolvedVars);

        // Check if it's a SpEL expression: ${...}
        if (preprocessed.startsWith("${") && preprocessed.endsWith("}")) {
            String spelExp = preprocessed.substring(2, preprocessed.length() - 1);
            try {
                return spelParser.parseExpression(spelExp).getValue(spelContext);
            } catch (Exception e) {
                // If SpEL parsing fails, might be a nested expression, return as-is
                return preprocessed;
            }
        }

        return preprocessed;
    }

    // --- Built-in Function Classes ---

    /**
     * Custom functions accessible via ${fn.XXX()}
     */
    public static class FunctionHelper {
        public String uuid() {
            return UUID.randomUUID().toString();
        }

        public int random(int min, int max) {
            return (int) (Math.random() * (max - min) + min);
        }

        public String randomString(int length) {
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            StringBuilder sb = new StringBuilder(length);
            Random random = new Random();
            for (int i = 0; i < length; i++) {
                sb.append(chars.charAt(random.nextInt(chars.length())));
            }
            return sb.toString();
        }
    }

    /**
     * Time functions accessible via ${time.XXX()}
     * Supports offset calculations like ${time.now('+1d')}
     */
    public static class TimeHelper {

        /**
         * Get current timestamp as ISO-8601 string.
         */
        public String now() {
            return LocalDateTime.now().toString();
        }

        /**
         * Get current timestamp with offset.
         * Supported formats:
         * - '+1d' or '-1d' for days
         * - '+2h' or '-2h' for hours
         * - '+30m' or '-30m' for minutes
         * - '+60s' or '-60s' for seconds
         */
        public String now(String offset) {
            LocalDateTime base = LocalDateTime.now();
            LocalDateTime result = applyOffset(base, offset);
            return result.toString();
        }

        /**
         * Get current date/time formatted with pattern.
         */
        public String format(String pattern) {
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
        }

        /**
         * Get current date/time with offset and format.
         */
        public String format(String offset, String pattern) {
            LocalDateTime base = LocalDateTime.now();
            LocalDateTime result = applyOffset(base, offset);
            return result.format(DateTimeFormatter.ofPattern(pattern));
        }

        /**
         * Get current epoch milliseconds.
         */
        public long epochMilli() {
            return System.currentTimeMillis();
        }

        /**
         * Get epoch milliseconds with offset.
         */
        public long epochMilli(String offset) {
            LocalDateTime base = LocalDateTime.now();
            LocalDateTime result = applyOffset(base, offset);
            return result.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli();
        }

        // Apply time offset like '+1d', '-2h', etc.
        private LocalDateTime applyOffset(LocalDateTime base, String offset) {
            if (offset == null || offset.isBlank()) {
                return base;
            }

            Matcher matcher = TIME_OFFSET_PATTERN.matcher(offset);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Invalid time offset format: " + offset +
                        ". Expected format: [+/-]<number>[d|h|m|s], e.g., '+1d' or '-2h'");
            }

            String sign = matcher.group(1);
            int amount = Integer.parseInt(matcher.group(2));
            String unit = matcher.group(3);

            if ("-".equals(sign)) {
                amount = -amount;
            }

            return switch (unit) {
                case "d" -> base.plusDays(amount);
                case "h" -> base.plusHours(amount);
                case "m" -> base.plusMinutes(amount);
                case "s" -> base.plusSeconds(amount);
                default -> throw new IllegalArgumentException("Unknown time unit: " + unit);
            };
        }
    }
}
