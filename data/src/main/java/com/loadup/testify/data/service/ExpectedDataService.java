package com.loadup.testify.data.service;

import com.loadup.testify.common.util.CsvUtils;
import com.loadup.testify.common.util.PathUtils;
import com.loadup.testify.common.variable.VariableResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service for querying and comparing expected data from the database.
 */
@Slf4j
@Service
public class ExpectedDataService {

    private final JdbcTemplate jdbcTemplate;
    private final VariableResolver variableResolver;

    public ExpectedDataService(JdbcTemplate jdbcTemplate, VariableResolver variableResolver) {
        this.jdbcTemplate = jdbcTemplate;
        this.variableResolver = variableResolver;
    }

    /**
     * Load expected data from CSV files in the ExpectedData directory.
     *
     * @param testClass the test class
     * @param caseId    the case ID
     * @return a map of table name to expected rows
     */
    public Map<String, List<Map<String, String>>> loadExpectedData(Class<?> testClass, String caseId) {
        Path expectedDataDir = PathUtils.getExpectedDataDirectory(testClass, caseId);
        Map<String, List<Map<String, String>>> expectedData = new LinkedHashMap<>();

        if (!PathUtils.exists(expectedDataDir)) {
            log.debug("No ExpectedData directory found for case: {}", caseId);
            return expectedData;
        }

        Path[] csvFiles = PathUtils.getCsvFiles(expectedDataDir);
        for (Path csvFile : csvFiles) {
            String tableName = PathUtils.extractTableName(csvFile);
            // Read CSV with variable resolution (reference mode, not capture mode)
            List<Map<String, String>> data = CsvUtils.readCsv(csvFile, variableResolver, false);
            expectedData.put(tableName, data);
        }

        return expectedData;
    }

    /**
     * Query actual data from the database based on expected data conditions.
     * The first column in each expected data row is used as the query condition.
     *
     * @param tableName    the table name
     * @param expectedRows the expected rows (first column used as condition)
     * @return the actual data from the database
     */
    public List<Map<String, Object>> queryActualData(String tableName, List<Map<String, String>> expectedRows) {
        if (expectedRows == null || expectedRows.isEmpty()) {
            return Collections.emptyList();
        }

        List<Map<String, Object>> actualData = new ArrayList<>();

        for (Map<String, String> expectedRow : expectedRows) {
            // Get the first column as the condition
            Iterator<Map.Entry<String, String>> iterator = expectedRow.entrySet().iterator();
            if (!iterator.hasNext()) {
                continue;
            }

            Map.Entry<String, String> firstEntry = iterator.next();
            String conditionColumn = firstEntry.getKey();
            String conditionValue = variableResolver.resolve(firstEntry.getValue(), false);

            String sql = String.format(
                    "SELECT * FROM %s WHERE %s = ?",
                    sanitizeIdentifier(tableName),
                    sanitizeIdentifier(conditionColumn)
            );

            log.debug("Executing query: {} with value: {}", sql, conditionValue);

            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, conditionValue);
            actualData.addAll(rows);
        }

        return actualData;
    }

    /**
     * Query all data from a table.
     *
     * @param tableName the table name
     * @return all rows from the table
     */
    public List<Map<String, Object>> queryAllData(String tableName) {
        String sql = "SELECT * FROM " + sanitizeIdentifier(tableName);
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * Query data from a table with a specific condition.
     *
     * @param tableName       the table name
     * @param conditionColumn the condition column name
     * @param conditionValue  the condition value
     * @return the matching rows
     */
    public List<Map<String, Object>> queryData(String tableName, String conditionColumn, Object conditionValue) {
        String sql = String.format(
                "SELECT * FROM %s WHERE %s = ?",
                sanitizeIdentifier(tableName),
                sanitizeIdentifier(conditionColumn)
        );
        return jdbcTemplate.queryForList(sql, conditionValue);
    }

    /**
     * Sanitize a SQL identifier to prevent SQL injection.
     */
    private String sanitizeIdentifier(String identifier) {
        if (identifier == null || identifier.isEmpty()) {
            throw new IllegalArgumentException("Invalid identifier: null or empty");
        }
        if (!identifier.matches("^[a-zA-Z_][a-zA-Z0-9_]*$")) {
            throw new IllegalArgumentException("Invalid identifier: " + identifier);
        }
        return identifier;
    }
}
