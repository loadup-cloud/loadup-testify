package com.github.loadup.testify.common.variable;

import com.github.loadup.testify.common.exception.VariableResolutionException;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Variable resolver that supports Datafaker expressions and variable references.
 * 
 * Supported patterns:
 * - ${faker.name.firstName} - Generate random data using Datafaker
 * - ${=variable_name} - Reference a variable from the shared pool
 * - ${variable_name} - Simple variable substitution from the shared pool
 */
@Slf4j
@Component
public class VariableResolver {

    private static final Pattern FAKER_PATTERN = Pattern.compile("\\$\\{faker\\.([^}]+)}");
    private static final Pattern VARIABLE_REF_PATTERN = Pattern.compile("\\$\\{=([^}]+)}");
    private static final Pattern SIMPLE_VAR_PATTERN = Pattern.compile("\\$\\{([^=][^}]*)}");

    // Cache for Datafaker method lookups to avoid repeated reflection
    private static final Map<String, Method> METHOD_CACHE = new ConcurrentHashMap<>();

    private final Faker faker;

    public VariableResolver() {
        this.faker = new Faker(Locale.ENGLISH);
    }

    public VariableResolver(Locale locale) {
        this.faker = new Faker(locale);
    }

    /**
     * Resolve all variables and Datafaker expressions in a string.
     *
     * @param input              the input string containing placeholders
     * @param captureToPool      whether to capture generated values to the shared pool
     * @param captureVariableMap optional map to track captured variables (key: original expression, value: generated value)
     * @return the resolved string
     */
    public String resolve(String input, boolean captureToPool, Map<String, Object> captureVariableMap) {
        if (input == null) {
            return null;
        }

        String result = input;

        // First, resolve Datafaker expressions
        result = resolveFakerExpressions(result, captureToPool, captureVariableMap);

        // Then, resolve variable references from the pool
        result = resolveVariableReferences(result);

        // Finally, resolve simple variables
        result = resolveSimpleVariables(result);

        return result;
    }

    /**
     * Resolve all variables and Datafaker expressions in a string.
     *
     * @param input         the input string containing placeholders
     * @param captureToPool whether to capture generated values to the shared pool
     * @return the resolved string
     */
    public String resolve(String input, boolean captureToPool) {
        return resolve(input, captureToPool, null);
    }

    /**
     * Resolve all variables and Datafaker expressions in a string without capturing.
     *
     * @param input the input string containing placeholders
     * @return the resolved string
     */
    public String resolve(String input) {
        return resolve(input, false, null);
    }

    /**
     * Resolve Datafaker expressions like ${faker.name.firstName}.
     */
    private String resolveFakerExpressions(String input, boolean captureToPool, Map<String, Object> captureVariableMap) {
        Matcher matcher = FAKER_PATTERN.matcher(input);
        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            String expression = matcher.group(1);
            String generatedValue = generateFakerValue(expression);

            if (captureToPool) {
                // Use the expression as the variable name
                String variableName = "faker." + expression;
                SharedVariablePool.put(variableName, generatedValue);
                log.debug("Captured faker variable: {} = {}", variableName, generatedValue);
            }

            if (captureVariableMap != null) {
                captureVariableMap.put("${faker." + expression + "}", generatedValue);
            }

            matcher.appendReplacement(result, Matcher.quoteReplacement(generatedValue));
        }
        matcher.appendTail(result);

        return result.toString();
    }

    /**
     * Resolve variable references like ${=variable_name} from the shared pool.
     */
    private String resolveVariableReferences(String input) {
        Matcher matcher = VARIABLE_REF_PATTERN.matcher(input);
        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            String variableName = matcher.group(1).trim();
            Object value = SharedVariablePool.get(variableName);

            if (value == null) {
                throw new VariableResolutionException("Variable not found in shared pool: " + variableName);
            }

            matcher.appendReplacement(result, Matcher.quoteReplacement(String.valueOf(value)));
        }
        matcher.appendTail(result);

        return result.toString();
    }

    /**
     * Resolve simple variable patterns like ${variable_name} from the shared pool.
     */
    private String resolveSimpleVariables(String input) {
        Matcher matcher = SIMPLE_VAR_PATTERN.matcher(input);
        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            String variableName = matcher.group(1).trim();
            Object value = SharedVariablePool.get(variableName);

            if (value != null) {
                matcher.appendReplacement(result, Matcher.quoteReplacement(String.valueOf(value)));
            } else {
                // Keep the original expression if variable not found
                matcher.appendReplacement(result, Matcher.quoteReplacement(matcher.group(0)));
            }
        }
        matcher.appendTail(result);

        return result.toString();
    }

    /**
     * Generate a value using Datafaker based on the expression.
     *
     * @param expression the Datafaker expression (e.g., "name.firstName", "address.city")
     * @return the generated value
     */
    private String generateFakerValue(String expression) {
        try {
            String[] parts = expression.split("\\.");
            Object current = faker;

            for (String part : parts) {
                final Class<?> currentClass = current.getClass();
                String cacheKey = currentClass.getName() + "#" + part.toLowerCase();
                Method method = METHOD_CACHE.computeIfAbsent(cacheKey, 
                        key -> findMethod(currentClass, part));
                
                if (method == null) {
                    throw new VariableResolutionException("Unknown Datafaker method: " + part + " in expression: " + expression);
                }
                current = method.invoke(current);
            }

            return String.valueOf(current);
        } catch (Exception e) {
            throw new VariableResolutionException("Failed to generate Datafaker value for expression: " + expression, e);
        }
    }

    /**
     * Find a method by name in a class, ignoring case.
     */
    private Method findMethod(Class<?> clazz, String methodName) {
        for (Method method : clazz.getMethods()) {
            if (method.getName().equalsIgnoreCase(methodName) && method.getParameterCount() == 0) {
                return method;
            }
        }
        return null;
    }
}
