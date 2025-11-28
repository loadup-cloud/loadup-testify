package com.loadup.testify.assertions.comparator;

import com.loadup.testify.assertions.rule.AssertionRule;
import com.loadup.testify.assertions.rule.CompareOperator;
import com.loadup.testify.common.exception.AssertionException;
import com.loadup.testify.common.variable.VariableResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Comparator for comparing expected and actual values based on assertion rules.
 */
@Slf4j
@Component
public class DataComparator {

    private static final List<DateTimeFormatter> DATE_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ISO_DATE_TIME,
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,
            DateTimeFormatter.ISO_DATE,
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy")
    );

    private final VariableResolver variableResolver;

    public DataComparator(VariableResolver variableResolver) {
        this.variableResolver = variableResolver;
    }

    /**
     * Compare two values using the specified assertion rule.
     *
     * @param actual the actual value
     * @param rule   the assertion rule
     * @return true if the comparison passes
     * @throws AssertionException if the comparison fails
     */
    public boolean compare(Object actual, AssertionRule rule) {
        Object expected = rule.getExpectedValue();
        CompareOperator operator = rule.getOperator();

        // Resolve variable references in expected value
        if (expected instanceof String) {
            expected = variableResolver.resolve((String) expected, false);
        }

        log.debug("Comparing actual: {} with expected: {} using operator: {}", actual, expected, operator);

        boolean result = switch (operator) {
            case EQUALS -> compareEquals(actual, expected, rule);
            case NOT_EQUALS -> !compareEquals(actual, expected, rule);
            case NOT_NULL -> actual != null;
            case IS_NULL -> actual == null;
            case GT -> compareNumeric(actual, expected) > 0;
            case GTE -> compareNumeric(actual, expected) >= 0;
            case LT -> compareNumeric(actual, expected) < 0;
            case LTE -> compareNumeric(actual, expected) <= 0;
            case CONTAINS -> String.valueOf(actual).contains(String.valueOf(expected));
            case STARTS_WITH -> String.valueOf(actual).startsWith(String.valueOf(expected));
            case ENDS_WITH -> String.valueOf(actual).endsWith(String.valueOf(expected));
            case REGEX -> Pattern.matches(String.valueOf(expected), String.valueOf(actual));
            case IGNORE_ORDER -> compareCollectionsIgnoringOrder(actual, expected);
            case DATETIME_TOLERANCE -> compareDates(actual, expected, rule);
            case SKIP, IGNORE_FIELDS -> true; // Always pass
        };

        if (!result) {
            String message = rule.getMessage() != null ? rule.getMessage()
                    : String.format("Assertion failed for field '%s': expected %s %s but was %s",
                    rule.getField(), operator, expected, actual);
            throw new AssertionException(message);
        }

        return true;
    }

    /**
     * Compare two values for equality with tolerance for dates and numbers.
     */
    private boolean compareEquals(Object actual, Object expected, AssertionRule rule) {
        if (actual == null && expected == null) {
            return true;
        }
        if (actual == null || expected == null) {
            return false;
        }

        // Handle date comparison with tolerance
        if (rule.getDateTolerance() != null || rule.getDateFormat() != null) {
            return compareDates(actual, expected, rule);
        }

        // Handle numeric comparison with tolerance
        if (rule.getNumericTolerance() != null) {
            return compareNumbersWithTolerance(actual, expected, rule.getNumericTolerance());
        }

        // Handle collections
        if (actual instanceof Collection && expected instanceof Collection) {
            return compareCollections((Collection<?>) actual, (Collection<?>) expected);
        }

        // Handle maps
        if (actual instanceof Map && expected instanceof Map) {
            return compareMaps((Map<?, ?>) actual, (Map<?, ?>) expected, rule);
        }

        // Standard equality
        return Objects.equals(String.valueOf(actual), String.valueOf(expected));
    }

    /**
     * Compare numeric values.
     */
    private int compareNumeric(Object actual, Object expected) {
        BigDecimal actualNum = toBigDecimal(actual);
        BigDecimal expectedNum = toBigDecimal(expected);
        return actualNum.compareTo(expectedNum);
    }

    /**
     * Compare numbers with tolerance.
     */
    private boolean compareNumbersWithTolerance(Object actual, Object expected, double tolerance) {
        BigDecimal actualNum = toBigDecimal(actual);
        BigDecimal expectedNum = toBigDecimal(expected);
        BigDecimal diff = actualNum.subtract(expectedNum).abs();
        return diff.compareTo(BigDecimal.valueOf(tolerance)) <= 0;
    }

    /**
     * Convert an object to BigDecimal.
     */
    private BigDecimal toBigDecimal(Object value) {
        if (value instanceof Number) {
            return BigDecimal.valueOf(((Number) value).doubleValue());
        }
        return new BigDecimal(String.valueOf(value));
    }

    /**
     * Compare dates with optional tolerance.
     */
    private boolean compareDates(Object actual, Object expected, AssertionRule rule) {
        LocalDateTime actualDate = parseDate(actual, rule.getDateFormat());
        LocalDateTime expectedDate = parseDate(expected, rule.getDateFormat());

        if (actualDate == null || expectedDate == null) {
            return false;
        }

        if (rule.getDateTolerance() != null) {
            long diffMillis = Math.abs(Duration.between(actualDate, expectedDate).toMillis());
            return diffMillis <= rule.getDateTolerance();
        }

        return actualDate.equals(expectedDate);
    }

    /**
     * Parse a date from various formats.
     */
    private LocalDateTime parseDate(Object value, String customFormat) {
        if (value instanceof LocalDateTime) {
            return (LocalDateTime) value;
        }
        if (value instanceof LocalDate) {
            return ((LocalDate) value).atStartOfDay();
        }
        if (value instanceof java.sql.Timestamp) {
            return ((java.sql.Timestamp) value).toLocalDateTime();
        }
        if (value instanceof java.util.Date) {
            return ((java.util.Date) value).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }

        String strValue = String.valueOf(value);

        // Try custom format first
        if (customFormat != null) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(customFormat);
                return LocalDateTime.parse(strValue, formatter);
            } catch (DateTimeParseException e) {
                // Fall through to default formats
            }
        }

        // Try default formats
        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                return LocalDateTime.parse(strValue, formatter);
            } catch (DateTimeParseException e) {
                // Try next format
            }
        }

        // Try date only
        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                LocalDate date = LocalDate.parse(strValue, formatter);
                return date.atStartOfDay();
            } catch (DateTimeParseException e) {
                // Try next format
            }
        }

        return null;
    }

    /**
     * Compare two collections.
     */
    private boolean compareCollections(Collection<?> actual, Collection<?> expected) {
        if (actual.size() != expected.size()) {
            return false;
        }

        Iterator<?> actualIt = actual.iterator();
        Iterator<?> expectedIt = expected.iterator();

        while (actualIt.hasNext() && expectedIt.hasNext()) {
            if (!Objects.equals(String.valueOf(actualIt.next()), String.valueOf(expectedIt.next()))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Compare two collections ignoring order.
     */
    private boolean compareCollectionsIgnoringOrder(Object actual, Object expected) {
        if (!(actual instanceof Collection) || !(expected instanceof Collection)) {
            return Objects.equals(actual, expected);
        }

        Collection<?> actualColl = (Collection<?>) actual;
        Collection<?> expectedColl = (Collection<?>) expected;

        if (actualColl.size() != expectedColl.size()) {
            return false;
        }

        List<String> actualList = new ArrayList<>();
        List<String> expectedList = new ArrayList<>();

        for (Object item : actualColl) {
            actualList.add(String.valueOf(item));
        }
        for (Object item : expectedColl) {
            expectedList.add(String.valueOf(item));
        }

        Collections.sort(actualList);
        Collections.sort(expectedList);

        return actualList.equals(expectedList);
    }

    /**
     * Compare two maps.
     */
    private boolean compareMaps(Map<?, ?> actual, Map<?, ?> expected, AssertionRule rule) {
        Set<String> ignoreFields = rule.getIgnoreFields() != null
                ? new HashSet<>(rule.getIgnoreFields())
                : Collections.emptySet();

        for (Object key : expected.keySet()) {
            String keyStr = String.valueOf(key);
            if (ignoreFields.contains(keyStr)) {
                continue;
            }

            Object expectedValue = expected.get(key);
            Object actualValue = actual.get(key);

            if (!Objects.equals(String.valueOf(actualValue), String.valueOf(expectedValue))) {
                return false;
            }
        }

        return true;
    }
}
