package com.loadup.testify.assertions.service;

import com.loadup.testify.assertions.comparator.DataComparator;
import com.loadup.testify.assertions.rule.AssertionRule;
import com.loadup.testify.assertions.rule.CompareOperator;
import com.loadup.testify.common.exception.AssertionException;
import com.loadup.testify.common.variable.VariableResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Service for performing assertions on test results.
 */
@Slf4j
@Service
public class AssertionService {

    private final DataComparator dataComparator;
    private final VariableResolver variableResolver;

    public AssertionService(DataComparator dataComparator, VariableResolver variableResolver) {
        this.dataComparator = dataComparator;
        this.variableResolver = variableResolver;
    }

    /**
     * Assert that the actual value matches the expected value according to the rule.
     *
     * @param actual the actual value
     * @param rule   the assertion rule
     * @throws AssertionException if the assertion fails
     */
    public void assertValue(Object actual, AssertionRule rule) {
        dataComparator.compare(actual, rule);
    }

    /**
     * Assert that all values in the actual map match the expected map.
     *
     * @param actual      the actual data map
     * @param expected    the expected data map
     * @param ignoreFields fields to ignore during comparison
     * @throws AssertionException if any assertion fails
     */
    public void assertMap(Map<String, Object> actual, Map<String, Object> expected, Set<String> ignoreFields) {
        for (Map.Entry<String, Object> entry : expected.entrySet()) {
            String key = entry.getKey();
            if (ignoreFields != null && ignoreFields.contains(key)) {
                continue;
            }

            Object expectedValue = entry.getValue();
            Object actualValue = actual.get(key);

            // Resolve variable references
            if (expectedValue instanceof String) {
                expectedValue = variableResolver.resolve((String) expectedValue, false);
            }

            AssertionRule rule = AssertionRule.builder()
                    .field(key)
                    .operator(CompareOperator.EQUALS)
                    .expectedValue(expectedValue)
                    .build();

            assertValue(actualValue, rule);
        }
    }

    /**
     * Assert that all values in the actual map match the expected map.
     *
     * @param actual   the actual data map
     * @param expected the expected data map
     * @throws AssertionException if any assertion fails
     */
    public void assertMap(Map<String, Object> actual, Map<String, Object> expected) {
        assertMap(actual, expected, Collections.emptySet());
    }

    /**
     * Assert that the actual list matches the expected list.
     *
     * @param actual      the actual list
     * @param expected    the expected list
     * @param ignoreOrder whether to ignore order when comparing
     * @throws AssertionException if the assertion fails
     */
    public void assertList(List<?> actual, List<?> expected, boolean ignoreOrder) {
        if (actual == null && expected == null) {
            return;
        }
        if (actual == null || expected == null) {
            throw new AssertionException("List mismatch: one is null, the other is not");
        }
        if (actual.size() != expected.size()) {
            throw new AssertionException(String.format("List size mismatch: expected %d but was %d",
                    expected.size(), actual.size()));
        }

        if (ignoreOrder) {
            List<Object> sortedActual = new ArrayList<>(actual);
            List<Object> sortedExpected = new ArrayList<>(expected);
            sortedActual.sort(Comparator.comparing(Object::toString));
            sortedExpected.sort(Comparator.comparing(Object::toString));

            for (int i = 0; i < sortedExpected.size(); i++) {
                Object expectedItem = sortedExpected.get(i);
                Object actualItem = sortedActual.get(i);
                assertEqualsInternal(actualItem, expectedItem, "List item at sorted index " + i);
            }
        } else {
            for (int i = 0; i < expected.size(); i++) {
                Object expectedItem = expected.get(i);
                Object actualItem = actual.get(i);
                assertEqualsInternal(actualItem, expectedItem, "List item at index " + i);
            }
        }
    }

    /**
     * Assert that database rows match expected data.
     *
     * @param actualRows   the actual rows from the database
     * @param expectedRows the expected rows
     * @param ignoreFields fields to ignore during comparison
     * @throws AssertionException if any assertion fails
     */
    public void assertDatabaseRows(List<Map<String, Object>> actualRows,
                                   List<Map<String, String>> expectedRows,
                                   Set<String> ignoreFields) {
        if (actualRows.size() != expectedRows.size()) {
            throw new AssertionException(String.format("Row count mismatch: expected %d but was %d",
                    expectedRows.size(), actualRows.size()));
        }

        for (int i = 0; i < expectedRows.size(); i++) {
            Map<String, String> expectedRow = expectedRows.get(i);
            Map<String, Object> actualRow = actualRows.get(i);

            for (Map.Entry<String, String> entry : expectedRow.entrySet()) {
                String key = entry.getKey();
                if (ignoreFields != null && ignoreFields.contains(key)) {
                    continue;
                }

                String expectedValue = variableResolver.resolve(entry.getValue(), false);
                Object actualValue = actualRow.get(key);

                if (!Objects.equals(String.valueOf(actualValue), expectedValue)) {
                    throw new AssertionException(String.format(
                            "Row %d, field '%s' mismatch: expected '%s' but was '%s'",
                            i, key, expectedValue, actualValue));
                }
            }
        }
    }

    /**
     * Assert that the actual response matches the expected response.
     *
     * @param actual   the actual response
     * @param expected the expected response
     * @throws AssertionException if the assertion fails
     */
    @SuppressWarnings("unchecked")
    public void assertResponse(Object actual, Object expected) {
        assertResponse(actual, expected, Collections.emptySet());
    }

    /**
     * Assert that the actual response matches the expected response with ignore fields.
     *
     * @param actual       the actual response
     * @param expected     the expected response
     * @param ignoreFields fields to ignore during comparison
     * @throws AssertionException if the assertion fails
     */
    @SuppressWarnings("unchecked")
    public void assertResponse(Object actual, Object expected, Set<String> ignoreFields) {
        if (expected instanceof Map && actual instanceof Map) {
            assertMap((Map<String, Object>) actual, (Map<String, Object>) expected, ignoreFields);
        } else if (expected instanceof Map && actual != null) {
            // Convert actual object to Map for comparison
            Map<String, Object> actualMap = convertObjectToMap(actual);
            // Only compare fields that exist in expected, respecting ignoreFields
            assertMapWithExpectedFields(actualMap, (Map<String, Object>) expected, ignoreFields);
        } else if (expected instanceof List && actual instanceof List) {
            assertList((List<?>) actual, (List<?>) expected, false);
        } else {
            assertEqualsInternal(actual, expected, "Response");
        }
    }

    /**
     * Assert that actual map contains expected values, only checking fields in expected.
     * Extra fields in actual are allowed (useful when actual has more fields than expected).
     */
    @SuppressWarnings("unchecked")
    private void assertMapWithExpectedFields(Map<String, Object> actual, Map<String, Object> expected, Set<String> ignoreFields) {
        for (Map.Entry<String, Object> entry : expected.entrySet()) {
            String key = entry.getKey();
            if (ignoreFields != null && ignoreFields.contains(key)) {
                continue;
            }

            Object expectedValue = entry.getValue();
            Object actualValue = actual.get(key);

            // Resolve variable references
            if (expectedValue instanceof String) {
                expectedValue = variableResolver.resolve((String) expectedValue, false);
            }

            // Handle nested objects - if expected is a Map and actual is an object, recurse
            if (expectedValue instanceof Map && actualValue != null && !(actualValue instanceof Map)) {
                // Convert actual object to map and compare recursively
                Map<String, Object> actualNestedMap = convertObjectToMap(actualValue);
                // Create nested ignoreFields for this key (e.g., "role.id" -> "id" for nested comparison)
                Set<String> nestedIgnoreFields = new HashSet<>();
                if (ignoreFields != null) {
                    String prefix = key + ".";
                    for (String ignoredField : ignoreFields) {
                        if (ignoredField.startsWith(prefix)) {
                            nestedIgnoreFields.add(ignoredField.substring(prefix.length()));
                        }
                    }
                }
                assertMapWithExpectedFields(actualNestedMap, (Map<String, Object>) expectedValue, nestedIgnoreFields);
            } else if (expectedValue instanceof Map && actualValue instanceof Map) {
                // Both are maps, compare recursively
                Set<String> nestedIgnoreFields = new HashSet<>();
                if (ignoreFields != null) {
                    String prefix = key + ".";
                    for (String ignoredField : ignoreFields) {
                        if (ignoredField.startsWith(prefix)) {
                            nestedIgnoreFields.add(ignoredField.substring(prefix.length()));
                        }
                    }
                }
                assertMapWithExpectedFields((Map<String, Object>) actualValue, (Map<String, Object>) expectedValue, nestedIgnoreFields);
            } else {
                AssertionRule rule = AssertionRule.builder()
                        .field(key)
                        .operator(CompareOperator.EQUALS)
                        .expectedValue(expectedValue)
                        .build();

                assertValue(actualValue, rule);
            }
        }
    }

    /**
     * Convert an object to a Map using reflection.
     */
    private Map<String, Object> convertObjectToMap(Object obj) {
        Map<String, Object> result = new HashMap<>();
        
        if (obj == null) {
            return result;
        }
        
        for (Method method : obj.getClass().getMethods()) {
            String name = method.getName();
            if (method.getParameterCount() == 0 && !name.equals("getClass")) {
                try {
                    String fieldName = null;
                    if (name.startsWith("get") && name.length() > 3) {
                        fieldName = name.substring(3, 4).toLowerCase() + name.substring(4);
                    } else if (name.startsWith("is") && name.length() > 2) {
                        fieldName = name.substring(2, 3).toLowerCase() + name.substring(3);
                    }
                    if (fieldName != null) {
                        Object value = method.invoke(obj);
                        result.put(fieldName, value);
                    }
                } catch (Exception e) {
                    // Skip inaccessible properties
                }
            }
        }
        
        return result;
    }

    /**
     * Internal equals assertion with error message.
     */
    private void assertEqualsInternal(Object actual, Object expected, String context) {
        // Resolve variable references
        if (expected instanceof String) {
            expected = variableResolver.resolve((String) expected, false);
        }

        if (!Objects.equals(String.valueOf(actual), String.valueOf(expected))) {
            throw new AssertionException(String.format("%s mismatch: expected '%s' but was '%s'",
                    context, expected, actual));
        }
    }
}
