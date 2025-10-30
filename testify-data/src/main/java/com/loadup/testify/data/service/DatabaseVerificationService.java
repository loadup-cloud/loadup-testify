package com.loadup.testify.data.service;

import com.loadup.testify.core.context.TestContext;
import com.loadup.testify.core.model.ColumnVerification;
import com.loadup.testify.core.model.DatabaseVerification;
import com.loadup.testify.core.model.TableVerification;
import com.loadup.testify.core.model.VerificationRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Service for database verification after test execution
 */
@Slf4j
@Service
public class DatabaseVerificationService {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseVerificationService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Verify database state according to configuration
     */
    public void verify(DatabaseVerification verification) {
        if (verification == null || verification.getTables() == null) {
            return;
        }

        List<String> errors = new ArrayList<>();

        for (TableVerification tableVerification : verification.getTables()) {
            verifyTable(tableVerification, errors);
        }

        if (!errors.isEmpty()) {
            String errorMessage = "Database verification failed:\n" + String.join("\n", errors);
            throw new AssertionError(errorMessage);
        }
    }

    /**
     * Verify single table
     */
    private void verifyTable(TableVerification verification, List<String> errors) {
        String tableName = verification.getTable();
        log.info("Verifying table: {}", tableName);

        // Build query
        String sql = buildSelectQuery(tableName, verification.getWhere());

        // Execute query
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        // Verify row count
        if (verification.getExpectedCount() != null) {
            if (rows.size() != verification.getExpectedCount()) {
                errors.add(String.format("Table %s: expected %d rows, found %d",
                        tableName, verification.getExpectedCount(), rows.size()));
            }
        }

        // Verify columns
        if (verification.getColumns() != null && !rows.isEmpty()) {
            for (Map<String, Object> row : rows) {
                verifyRow(tableName, row, verification.getColumns(), errors);
            }
        }
    }

    /**
     * Build SELECT query with WHERE clause
     */
    private String buildSelectQuery(String tableName, Map<String, Object> whereConditions) {
        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(tableName);

        if (whereConditions != null && !whereConditions.isEmpty()) {
            TestContext context = TestContext.current();
            sql.append(" WHERE ");

            List<String> conditions = new ArrayList<>();
            for (Map.Entry<String, Object> entry : whereConditions.entrySet()) {
                Object value = context.resolveReference(entry.getValue());
                if (value instanceof String) {
                    conditions.add(entry.getKey() + " = '" + value + "'");
                } else {
                    conditions.add(entry.getKey() + " = " + value);
                }
            }

            sql.append(String.join(" AND ", conditions));
        }

        return sql.toString();
    }

    /**
     * Verify single row
     */
    private void verifyRow(String tableName, Map<String, Object> row,
                          List<ColumnVerification> columnVerifications, List<String> errors) {
        for (ColumnVerification verification : columnVerifications) {
            String columnName = verification.getName();
            Object actualValue = row.get(columnName);

            try {
                verifyColumn(tableName, columnName, actualValue, verification);
            } catch (AssertionError e) {
                errors.add(e.getMessage());
            }
        }
    }

    /**
     * Verify single column value
     */
    private void verifyColumn(String tableName, String columnName, Object actualValue,
                             ColumnVerification verification) {
        VerificationRule rule = verification.getRule();
        Object expectedValue = resolveValue(verification.getValue());

        String fieldLabel = tableName + "." + columnName;

        switch (rule) {
            case EQUALS:
                if (!equals(actualValue, expectedValue)) {
                    throw new AssertionError(String.format("%s: expected '%s', got '%s'",
                            fieldLabel, expectedValue, actualValue));
                }
                break;

            case NOT_EQUALS:
                if (equals(actualValue, expectedValue)) {
                    throw new AssertionError(String.format("%s: expected not equals '%s'",
                            fieldLabel, expectedValue));
                }
                break;

            case NOT_NULL:
                if (actualValue == null) {
                    throw new AssertionError(fieldLabel + ": expected not null");
                }
                break;

            case NULL:
                if (actualValue != null) {
                    throw new AssertionError(String.format("%s: expected null, got '%s'",
                            fieldLabel, actualValue));
                }
                break;

            case GREATER_THAN:
                if (!compareNumbers(actualValue, expectedValue, (a, e) -> a > e)) {
                    throw new AssertionError(String.format("%s: expected > %s, got %s",
                            fieldLabel, expectedValue, actualValue));
                }
                break;

            case LESS_THAN:
                if (!compareNumbers(actualValue, expectedValue, (a, e) -> a < e)) {
                    throw new AssertionError(String.format("%s: expected < %s, got %s",
                            fieldLabel, expectedValue, actualValue));
                }
                break;

            case GREATER_THAN_OR_EQUALS:
                if (!compareNumbers(actualValue, expectedValue, (a, e) -> a >= e)) {
                    throw new AssertionError(String.format("%s: expected >= %s, got %s",
                            fieldLabel, expectedValue, actualValue));
                }
                break;

            case LESS_THAN_OR_EQUALS:
                if (!compareNumbers(actualValue, expectedValue, (a, e) -> a <= e)) {
                    throw new AssertionError(String.format("%s: expected <= %s, got %s",
                            fieldLabel, expectedValue, actualValue));
                }
                break;

            case CONTAINS:
                if (actualValue == null || !actualValue.toString().contains(expectedValue.toString())) {
                    throw new AssertionError(String.format("%s: expected to contain '%s', got '%s'",
                            fieldLabel, expectedValue, actualValue));
                }
                break;

            case STARTS_WITH:
                if (actualValue == null || !actualValue.toString().startsWith(expectedValue.toString())) {
                    throw new AssertionError(String.format("%s: expected to start with '%s', got '%s'",
                            fieldLabel, expectedValue, actualValue));
                }
                break;

            case ENDS_WITH:
                if (actualValue == null || !actualValue.toString().endsWith(expectedValue.toString())) {
                    throw new AssertionError(String.format("%s: expected to end with '%s', got '%s'",
                            fieldLabel, expectedValue, actualValue));
                }
                break;

            case TIME_CLOSE_TO:
                verifyTimeCloseTo(fieldLabel, actualValue, expectedValue, verification.getToleranceSeconds());
                break;

            case REGEX_MATCH:
                if (actualValue == null || !Pattern.matches(expectedValue.toString(), actualValue.toString())) {
                    throw new AssertionError(String.format("%s: expected to match regex '%s', got '%s'",
                            fieldLabel, expectedValue, actualValue));
                }
                break;

            default:
                log.warn("Unsupported verification rule: {}", rule);
        }
    }

    /**
     * Compare two values for equality
     */
    private boolean equals(Object actual, Object expected) {
        if (actual == null) {
            return expected == null;
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
     * Verify timestamp is close to expected value within tolerance
     */
    private void verifyTimeCloseTo(String fieldLabel, Object actualValue, Object expectedValue,
                                   Integer toleranceSeconds) {
        if (actualValue == null) {
            throw new AssertionError(fieldLabel + ": actual value is null");
        }

        int tolerance = toleranceSeconds != null ? toleranceSeconds : 5; // Default 5 seconds

        try {
            LocalDateTime actual = convertToLocalDateTime(actualValue);
            LocalDateTime expected = convertToLocalDateTime(expectedValue);

            long diff = Math.abs(ChronoUnit.SECONDS.between(actual, expected));

            if (diff > tolerance) {
                throw new AssertionError(String.format("%s: time difference %d seconds exceeds tolerance %d seconds",
                        fieldLabel, diff, tolerance));
            }
        } catch (Exception e) {
            throw new AssertionError(fieldLabel + ": failed to compare timestamps: " + e.getMessage());
        }
    }

    /**
     * Convert value to LocalDateTime
     */
    private LocalDateTime convertToLocalDateTime(Object value) {
        if (value instanceof Timestamp) {
            return ((Timestamp) value).toLocalDateTime();
        } else if (value instanceof LocalDateTime) {
            return (LocalDateTime) value;
        } else if (value instanceof String) {
            return LocalDateTime.parse((String) value);
        } else {
            throw new IllegalArgumentException("Cannot convert to LocalDateTime: " + value);
        }
    }

    /**
     * Resolve reference value
     */
    private Object resolveValue(Object value) {
        return TestContext.current().resolveReference(value);
    }
}
