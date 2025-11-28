package com.loadup.testify.data.service;

import com.loadup.testify.common.util.CsvUtils;
import com.loadup.testify.common.util.PathUtils;
import com.loadup.testify.common.variable.VariableResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.*;

/**
 * Service for querying and comparing expected data from the database.
 * 
 * <p>Supports multiple CSV files per ExpectedData directory, each corresponding to a different table.</p>
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
     * @param testClass   the test class
     * @param serviceName the service name (e.g., "UserService")
     * @param methodName  the method name (e.g., "createUser")
     * @param caseId      the case ID (e.g., "case01")
     * @return a map of table name to expected rows
     */
    public Map<String, List<Map<String, String>>> loadExpectedData(Class<?> testClass, String serviceName, 
                                                                    String methodName, String caseId) {
        Path expectedDataDir = PathUtils.getExpectedDataDirectory(testClass, serviceName, methodName, caseId);
        Map<String, List<Map<String, String>>> expectedData = new LinkedHashMap<>();

        if (!PathUtils.exists(expectedDataDir)) {
            log.debug("No ExpectedData directory found for case: {}.{}/{}", serviceName, methodName, caseId);
            return expectedData;
        }

        Path[] csvFiles = PathUtils.getCsvFiles(expectedDataDir);
        
        if (csvFiles.length == 0) {
            log.debug("No CSV files found in ExpectedData directory for case: {}.{}/{}", serviceName, methodName, caseId);
            return expectedData;
        }
        
        log.info("Found {} CSV files in ExpectedData for {}.{}/{}", csvFiles.length, serviceName, methodName, caseId);
        
        for (Path csvFile : csvFiles) {
            String tableName = PathUtils.extractTableName(csvFile);
            // Read CSV with variable resolution (reference mode, not capture mode)
            List<Map<String, String>> data = CsvUtils.readCsv(csvFile, variableResolver, false);
            expectedData.put(tableName, data);
            log.debug("Loaded {} expected rows for table: {}", data.size(), tableName);
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
