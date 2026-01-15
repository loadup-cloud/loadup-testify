package com.github.loadup.testify.starter.base;

import com.github.loadup.testify.asserts.engine.DbAssertEngine;
import com.github.loadup.testify.starter.config.TestifyProperties;
import com.github.loadup.testify.starter.sql.SqlExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Base class for Testify integration tests.
 * Provides helper methods for database assertions and common test operations.
 */
@SpringBootTest
public abstract class TestifyBase {

    @Autowired
    protected ApplicationContext applicationContext;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired(required = false)
    protected TestifyProperties testifyProperties;

    /**
     * Assert database state matches expected data.
     * 
     * @param tableName    Table to verify
     * @param expectedRows Expected row data with operators
     */
    protected void assertDatabase(String tableName, List<Map<String, Object>> expectedRows) {
        assertDatabase(tableName, expectedRows, "lenient");
    }

    /**
     * Assert database state matches expected data with mode.
     * 
     * @param tableName    Table to verify
     * @param expectedRows Expected row data with operators
     * @param mode         "strict" for exact row count match, "lenient" otherwise
     */
    protected void assertDatabase(String tableName, List<Map<String, Object>> expectedRows, String mode) {
        // Create SQL executor
        SqlExecutor sqlExecutor = new SqlExecutor(jdbcTemplate);

        // Query actual data
        List<Map<String, Object>> actualRows = sqlExecutor.queryTable(tableName);

        // Create assert engine with appropriate column naming strategy
        String columnNamingStrategy = "caseInsensitive";
        if (testifyProperties != null && testifyProperties.getDatabase() != null) {
            columnNamingStrategy = testifyProperties.getDatabase().getColumnNamingStrategy();
        }

        DbAssertEngine assertEngine = new DbAssertEngine(columnNamingStrategy);

        // Verify
        assertEngine.verify(tableName, actualRows, expectedRows, mode);
    }

    /**
     * Execute cleanup SQL with variable substitution.
     * 
     * @param cleanupSql SQL statements (can contain variables like ${userId})
     */
    protected void executeCleanup(String cleanupSql) {
        SqlExecutor sqlExecutor = new SqlExecutor(jdbcTemplate);
        sqlExecutor.executeCleanup(cleanupSql);
    }

    /**
     * Insert test data into database.
     * 
     * @param tableName Target table
     * @param data      Row data to insert
     */
    protected void insertTestData(String tableName, List<Map<String, Object>> data) {
        SqlExecutor sqlExecutor = new SqlExecutor(jdbcTemplate);
        sqlExecutor.insertTestData(tableName, data);
    }

    /**
     * Get a bean from Spring context by name.
     * Useful for manual mock setup in tests.
     */
    protected <T> T getBean(String beanName, Class<T> requiredType) {
        return applicationContext.getBean(beanName, requiredType);
    }

    /**
     * Get a bean from Spring context by type.
     */
    protected <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }
}
