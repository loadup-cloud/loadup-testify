package com.loadup.testify.assertions.service;

import com.github.loadup.testify.context.TestContext;
import com.github.loadup.testify.model.AssertionType;
import com.github.loadup.testify.model.ExpectedResult;
import com.github.loadup.testify.model.FieldAssertion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Service for performing assertions on test results
 */
@Slf4j
@Service
public class AssertionService {

    /**
     * Verify actual result matches expected result
     */
    public void verify(Object actualResult, ExpectedResult expected) {
        if (expected == null) {
            return;
        }

        TestContext context = TestContext.current();

        // Check if exception was expected
        if (expected.getException() != null) {
            throw new AssertionError("Expected exception: " + expected.getException() +
                    " but got result: " + actualResult);
        }

        // Verify simple value equality
        if (expected.getValue() != null) {
            Object expectedValue = context.resolveReference(expected.getValue());
            if (!equals(actualResult, expectedValue)) {
                throw new AssertionError(String.format("Expected: %s, but got: %s",
                        expectedValue, actualResult));
            }
        }

        // Verify field-level assertions
        if (expected.getAssertions() != null && !expected.getAssertions().isEmpty()) {
            verifyFieldAssertions(actualResult, expected.getAssertions());
        }
    }

    /**
     * Verify exception
     */
    public void verifyException(Throwable actualException, ExpectedResult expected) {
        if (expected == null || expected.getException() == null) {
            throw new AssertionError("Unexpected exception: " + actualException.getClass().getName() +
                    ": " + actualException.getMessage());
        }

        String expectedExceptionClass = expected.getException();

        if (!actualException.getClass().getName().equals(expectedExceptionClass) &&
                !actualException.getClass().getSimpleName().equals(expectedExceptionClass)) {
            throw new AssertionError(String.format("Expected exception: %s, but got: %s",
                    expectedExceptionClass, actualException.getClass().getName()));
        }

        log.info("Exception verification passed: {}", expectedExceptionClass);
    }

    /**
     * Verify field-level assertions
     */
    private void verifyFieldAssertions(Object actualResult, List<FieldAssertion> assertions) {
        for (FieldAssertion assertion : assertions) {
            verifyFieldAssertion(actualResult, assertion);
        }
    }

    /**
     * Verify single field assertion
     */
    private void verifyFieldAssertion(Object actualResult, FieldAssertion assertion) {
        String fieldPath = assertion.getField();
        Object actualValue = extractFieldValue(actualResult, fieldPath);
        Object expectedValue = TestContext.current().resolveReference(assertion.getValue());

        AssertionType type = assertion.getType();
        String errorMessage = assertion.getMessage() != null ?
                assertion.getMessage() :
                String.format("Field '%s' assertion failed", fieldPath);

        try {
            performAssertion(actualValue, expectedValue, type, fieldPath);
        } catch (AssertionError e) {
            throw new AssertionError(errorMessage + ": " + e.getMessage());
        }
    }

    /**
     * Extract field value from object (supports nested fields)
     */
    private Object extractFieldValue(Object obj, String fieldPath) {
        if (obj == null) {
            return null;
        }

        String[] parts = fieldPath.split("\\.");
        Object current = obj;

        for (String part : parts) {
            if (current == null) {
                return null;
            }
            current = getFieldValue(current, part);
        }

        return current;
    }

    /**
     * Get field value using reflection
     */
    private Object getFieldValue(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // Try getter method
            try {
                String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                return obj.getClass().getMethod(getterName).invoke(obj);
            } catch (Exception ex) {
                log.warn("Failed to extract field: {} from object: {}", fieldName, obj.getClass());
                return null;
            }
        }
    }

    /**
     * Perform assertion based on type
     */
    private void performAssertion(Object actualValue, Object expectedValue, AssertionType type, String fieldPath) {
        switch (type) {
            case EQUALS:
                if (!equals(actualValue, expectedValue)) {
                    throw new AssertionError(String.format("%s: expected '%s', got '%s'",
                            fieldPath, expectedValue, actualValue));
                }
                break;

            case NOT_EQUALS:
                if (equals(actualValue, expectedValue)) {
                    throw new AssertionError(String.format("%s: expected not equals to '%s'",
                            fieldPath, expectedValue));
                }
                break;

            case NOT_NULL:
                if (actualValue == null) {
                    throw new AssertionError(fieldPath + ": expected not null");
                }
                break;

            case NULL:
                if (actualValue != null) {
                    throw new AssertionError(String.format("%s: expected null, got '%s'",
                            fieldPath, actualValue));
                }
                break;

            case CONTAINS:
                if (actualValue == null || !actualValue.toString().contains(expectedValue.toString())) {
                    throw new AssertionError(String.format("%s: expected to contain '%s', got '%s'",
                            fieldPath, expectedValue, actualValue));
                }
                break;

            case NOT_CONTAINS:
                if (actualValue != null && actualValue.toString().contains(expectedValue.toString())) {
                    throw new AssertionError(String.format("%s: expected not to contain '%s', got '%s'",
                            fieldPath, expectedValue, actualValue));
                }
                break;

            case STARTS_WITH:
                if (actualValue == null || !actualValue.toString().startsWith(expectedValue.toString())) {
                    throw new AssertionError(String.format("%s: expected to start with '%s', got '%s'",
                            fieldPath, expectedValue, actualValue));
                }
                break;

            case ENDS_WITH:
                if (actualValue == null || !actualValue.toString().endsWith(expectedValue.toString())) {
                    throw new AssertionError(String.format("%s: expected to end with '%s', got '%s'",
                            fieldPath, expectedValue, actualValue));
                }
                break;

            case GREATER_THAN:
                if (!compareNumbers(actualValue, expectedValue, (a, e) -> a > e)) {
                    throw new AssertionError(String.format("%s: expected > %s, got %s",
                            fieldPath, expectedValue, actualValue));
                }
                break;

            case LESS_THAN:
                if (!compareNumbers(actualValue, expectedValue, (a, e) -> a < e)) {
                    throw new AssertionError(String.format("%s: expected < %s, got %s",
                            fieldPath, expectedValue, actualValue));
                }
                break;

            case GREATER_THAN_OR_EQUALS:
                if (!compareNumbers(actualValue, expectedValue, (a, e) -> a >= e)) {
                    throw new AssertionError(String.format("%s: expected >= %s, got %s",
                            fieldPath, expectedValue, actualValue));
                }
                break;

            case LESS_THAN_OR_EQUALS:
                if (!compareNumbers(actualValue, expectedValue, (a, e) -> a <= e)) {
                    throw new AssertionError(String.format("%s: expected <= %s, got %s",
                            fieldPath, expectedValue, actualValue));
                }
                break;

            case MATCHES_REGEX:
                if (actualValue == null || !Pattern.matches(expectedValue.toString(), actualValue.toString())) {
                    throw new AssertionError(String.format("%s: expected to match regex '%s', got '%s'",
                            fieldPath, expectedValue, actualValue));
                }
                break;

            case HAS_SIZE:
                verifySize(actualValue, expectedValue, fieldPath);
                break;

            case IS_EMPTY:
                if (!isEmpty(actualValue)) {
                    throw new AssertionError(String.format("%s: expected empty, got '%s'",
                            fieldPath, actualValue));
                }
                break;

            case IS_NOT_EMPTY:
                if (isEmpty(actualValue)) {
                    throw new AssertionError(fieldPath + ": expected not empty");
                }
                break;

            case INSTANCE_OF:
                verifyInstanceOf(actualValue, expectedValue, fieldPath);
                break;

            case COLLECTION_CONTAINS:
                verifyCollectionContains(actualValue, expectedValue, fieldPath);
                break;

            case COLLECTION_NOT_CONTAINS:
                verifyCollectionNotContains(actualValue, expectedValue, fieldPath);
                break;

            default:
                log.warn("Unsupported assertion type: {}", type);
        }
    }

    /**
     * Compare two values for equality
     */
    private boolean equals(Object actual, Object expected) {
        if (actual == null) {
            return expected == null;
        }

        // Special handling for BigDecimal to compare values ignoring scale
        if (actual instanceof BigDecimal && expected instanceof BigDecimal) {
            return ((BigDecimal) actual).compareTo((BigDecimal) expected) == 0;
        }

        // Try to convert to BigDecimal if one is BigDecimal and other is a number
        if (actual instanceof BigDecimal || expected instanceof BigDecimal) {
            try {
                BigDecimal actualBD = actual instanceof BigDecimal ?
                        (BigDecimal) actual : new BigDecimal(actual.toString());
                BigDecimal expectedBD = expected instanceof BigDecimal ?
                        (BigDecimal) expected : new BigDecimal(expected.toString());
                return actualBD.compareTo(expectedBD) == 0;
            } catch (NumberFormatException e) {
                // If conversion fails, fall back to regular equals
            }
        }

        // Handle enum comparisons - compare string representations
        if (actual instanceof Enum || expected instanceof Enum) {
            return actual.toString().equals(expected.toString());
        }

        return actual.equals(expected);
    }

    /**
     * Compare numbers
     */
    private boolean compareNumbers(Object actual, Object expected, NumberComparator comparator) {
        if (actual == null || expected == null) {
            return false;
        }

        try {
            double a = Double.parseDouble(actual.toString());
            double e = Double.parseDouble(expected.toString());
            return comparator.compare(a, e);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FunctionalInterface
    private interface NumberComparator {
        boolean compare(double actual, double expected);
    }

    /**
     * Verify size
     */
    private void verifySize(Object actualValue, Object expectedValue, String fieldPath) {
        int expectedSize = Integer.parseInt(expectedValue.toString());
        int actualSize;

        if (actualValue instanceof Collection) {
            actualSize = ((Collection<?>) actualValue).size();
        } else if (actualValue instanceof String) {
            actualSize = ((String) actualValue).length();
        } else if (actualValue != null && actualValue.getClass().isArray()) {
            actualSize = ((Object[]) actualValue).length;
        } else {
            throw new AssertionError(fieldPath + ": cannot determine size of type: " +
                    (actualValue != null ? actualValue.getClass() : "null"));
        }

        if (actualSize != expectedSize) {
            throw new AssertionError(String.format("%s: expected size %d, got %d",
                    fieldPath, expectedSize, actualSize));
        }
    }

    /**
     * Check if empty
     */
    private boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        }
        if (value instanceof String) {
            return ((String) value).isEmpty();
        }
        if (value instanceof Collection) {
            return ((Collection<?>) value).isEmpty();
        }
        if (value.getClass().isArray()) {
            return ((Object[]) value).length == 0;
        }
        return false;
    }

    /**
     * Verify instance of
     */
    private void verifyInstanceOf(Object actualValue, Object expectedValue, String fieldPath) {
        if (actualValue == null) {
            throw new AssertionError(fieldPath + ": actual value is null");
        }

        try {
            Class<?> expectedClass = Class.forName(expectedValue.toString());
            if (!expectedClass.isInstance(actualValue)) {
                throw new AssertionError(String.format("%s: expected instance of %s, got %s",
                        fieldPath, expectedClass.getName(), actualValue.getClass().getName()));
            }
        } catch (ClassNotFoundException e) {
            throw new AssertionError(fieldPath + ": class not found: " + expectedValue);
        }
    }

    /**
     * Verify collection contains element
     */
    private void verifyCollectionContains(Object actualValue, Object expectedValue, String fieldPath) {
        if (!(actualValue instanceof Collection)) {
            throw new AssertionError(fieldPath + ": expected collection, got " +
                    (actualValue != null ? actualValue.getClass() : "null"));
        }

        Collection<?> collection = (Collection<?>) actualValue;
        if (!collection.contains(expectedValue)) {
            throw new AssertionError(String.format("%s: expected to contain '%s'",
                    fieldPath, expectedValue));
        }
    }

    /**
     * Verify collection does not contain element
     */
    private void verifyCollectionNotContains(Object actualValue, Object expectedValue, String fieldPath) {
        if (!(actualValue instanceof Collection)) {
            throw new AssertionError(fieldPath + ": expected collection, got " +
                    (actualValue != null ? actualValue.getClass() : "null"));
        }

        Collection<?> collection = (Collection<?>) actualValue;
        if (collection.contains(expectedValue)) {
            throw new AssertionError(String.format("%s: expected not to contain '%s'",
                    fieldPath, expectedValue));
        }
    }
}

