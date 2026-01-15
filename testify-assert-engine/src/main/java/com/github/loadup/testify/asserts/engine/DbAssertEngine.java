package com.github.loadup.testify.asserts.engine;

import com.github.loadup.testify.asserts.diff.DiffReportBuilder;
import com.github.loadup.testify.asserts.model.FieldDiff;
import com.github.loadup.testify.asserts.model.MatchResult;
import com.github.loadup.testify.asserts.model.RowDiff;
import com.github.loadup.testify.asserts.operator.OperatorProcessor;
import com.github.loadup.testify.asserts.util.ColumnNormalizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Database assertion engine with support for:
 * - Case-insensitive column name matching
 * - Snake_case to camelCase conversion
 * - Rich diff reporting using DiffReportBuilder
 * - Row matching by index or custom criteria
 */
public class DbAssertEngine {

    private String columnNamingStrategy = "caseInsensitive"; // Default strategy

    public DbAssertEngine() {
    }

    public DbAssertEngine(String columnNamingStrategy) {
        this.columnNamingStrategy = columnNamingStrategy;
    }

    public void setColumnNamingStrategy(String strategy) {
        this.columnNamingStrategy = strategy;
    }

    /**
     * Verify database assertion.
     * 
     * @param tableName    Table being verified
     * @param actualRows   Actual rows from database query
     * @param expectedRows Expected rows from YAML
     * @param mode         "strict" for exact row count match, otherwise lenient
     */
    public void verify(String tableName, List<Map<String, Object>> actualRows,
            List<Map<String, Object>> expectedRows, String mode) {
        List<RowDiff> diffs = new ArrayList<>();

        // Normalize all actual rows for consistent comparison
        List<Map<String, Object>> normalizedActualRows = new ArrayList<>();
        for (Map<String, Object> row : actualRows) {
            normalizedActualRows.add(ColumnNormalizer.normalizeMap(row, columnNamingStrategy));
        }

        // 1. Row count validation (Strict mode)
        if ("strict".equalsIgnoreCase(mode) && normalizedActualRows.size() != expectedRows.size()) {
            throw new AssertionError(String.format(
                    "Table [%s] row count mismatch! Expected: %d, Actual: %d",
                    tableName, expectedRows.size(), normalizedActualRows.size()));
        }

        // 2. Row-by-row comparison
        for (int i = 0; i < expectedRows.size(); i++) {
            Map<String, Object> expectedRow = expectedRows.get(i);

            // Normalize expected row keys
            Map<String, Object> normalizedExpectedRow = ColumnNormalizer.normalizeMap(expectedRow,
                    columnNamingStrategy);

            // Find matching row: by criteria (_match field) or by index
            Map<String, Object> actualRow = findMatchedRow(normalizedActualRows, normalizedExpectedRow, i);

            if (actualRow == null) {
                diffs.add(new RowDiff(i, "MISSING", "No matching row found in database", expectedRow, null));
                continue;
            }

            // Validate each field in the row
            Map<String, FieldDiff> fieldDiffs = new HashMap<>();
            normalizedExpectedRow.forEach((field, expVal) -> {
                if ("_match".equals(field)) {
                    return; // Skip match criteria field
                }

                Object actVal = actualRow.get(field);
                MatchResult result = OperatorProcessor.process(actVal, expVal);

                if (!result.isPassed()) {
                    fieldDiffs.put(field, new FieldDiff(expVal, actVal, result.message()));
                }
            });

            if (!fieldDiffs.isEmpty()) {
                diffs.add(new RowDiff(i, "DIFF", "Field validation failed", expectedRow, fieldDiffs));
            }
        }

        // 3. Report and throw if there are differences
        if (!diffs.isEmpty()) {
            String report = DiffReportBuilder.build(tableName, diffs);
            throw new AssertionError(report);
        }
    }

    /**
     * Find matching row using _match criteria or by index.
     */
    private Map<String, Object> findMatchedRow(List<Map<String, Object>> actuals,
            Map<String, Object> expected, int index) {
        if (!expected.containsKey("_match")) {
            // Match by index
            return index < actuals.size() ? actuals.get(index) : null;
        }

        // Match by criteria
        @SuppressWarnings("unchecked")
        Map<String, Object> criteria = (Map<String, Object>) expected.get("_match");
        Map<String, Object> normalizedCriteria = ColumnNormalizer.normalizeMap(criteria, columnNamingStrategy);

        return actuals.stream()
                .filter(row -> normalizedCriteria.entrySet().stream()
                        .allMatch(e -> {
                            Object expectedValue = e.getValue();
                            Object actualValue = row.get(e.getKey());
                            // Use simple equality for match criteria
                            return String.valueOf(expectedValue).equals(String.valueOf(actualValue));
                        }))
                .findFirst()
                .orElse(null);
    }
}