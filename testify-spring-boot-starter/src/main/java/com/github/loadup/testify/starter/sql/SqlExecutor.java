package com.github.loadup.testify.starter.sql;

import com.github.loadup.testify.data.engine.VariableContext;
import com.github.loadup.testify.data.engine.VariableEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * SQL executor for test setup and cleanup operations.
 * Supports variable substitution in SQL statements.
 */
public class SqlExecutor {

    private static final Logger logger = LoggerFactory.getLogger(SqlExecutor.class);
    private final JdbcTemplate jdbcTemplate;
    private final VariableEngine variableEngine;

    public SqlExecutor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.variableEngine = new VariableEngine();
    }

    /**
     * Execute cleanup SQL with variable substitution.
     * 
     * @param cleanupSql SQL statement(s) with variables like ${userId}
     */
    public void executeCleanup(String cleanupSql) {
        if (cleanupSql == null || cleanupSql.isBlank()) {
            return;
        }

        // Get current variable context
        Map<String, Object> variables = VariableContext.get();

        // Replace variables in SQL
        String processedSql = variableEngine.replacePlaceholders(cleanupSql, variables);

        logger.info("Executing cleanup SQL: {}", processedSql);

        // Split by semicolon and execute each statement
        String[] statements = processedSql.split(";");
        for (String statement : statements) {
            String trimmed = statement.trim();
            if (!trimmed.isEmpty()) {
                try {
                    jdbcTemplate.execute(trimmed);
                    logger.debug("Executed: {}", trimmed);
                } catch (Exception e) {
                    logger.error("Failed to execute cleanup SQL: {}", trimmed, e);
                    throw new RuntimeException("SQL cleanup failed: " + trimmed, e);
                }
            }
        }
    }

    /**
     * Insert test data into database.
     * 
     * @param tableName Target table
     * @param data      List of row data (each row is a Map)
     */
    public void insertTestData(String tableName, List<Map<String, Object>> data) {
        if (data == null || data.isEmpty()) {
            return;
        }

        Map<String, Object> variables = VariableContext.get();

        for (Map<String, Object> row : data) {
            // Build INSERT statement
            StringBuilder columnsPart = new StringBuilder();
            StringBuilder valuesPart = new StringBuilder();
            Object[] values = new Object[row.size()];
            int index = 0;

            for (Map.Entry<String, Object> entry : row.entrySet()) {
                if (index > 0) {
                    columnsPart.append(", ");
                    valuesPart.append(", ");
                }

                columnsPart.append(entry.getKey());
                valuesPart.append("?");

                // Replace variables in values
                Object value = entry.getValue();
                if (value instanceof String strValue) {
                    value = variableEngine.replacePlaceholders(strValue, variables);
                }
                values[index++] = value;
            }

            String insertSql = String.format("INSERT INTO %s (%s) VALUES (%s)",
                    tableName, columnsPart, valuesPart);

            logger.info("Inserting test data: {}", insertSql);

            try {
                jdbcTemplate.update(insertSql, values);
            } catch (Exception e) {
                logger.error("Failed to insert test data into {}: {}", tableName, row, e);
                throw new RuntimeException("Database setup failed for table: " + tableName, e);
            }
        }
    }

    /**
     * Query database for assertion.
     * 
     * @param tableName Table to query
     * @return List of rows as Maps
     */
    public List<Map<String, Object>> queryTable(String tableName) {
        return queryTable(tableName, null);
    }

    /**
     * Query database with WHERE clause.
     * 
     * @param tableName   Table to query
     * @param whereClause Optional WHERE clause (without WHERE keyword)
     * @return List of rows as Maps
     */
    public List<Map<String, Object>> queryTable(String tableName, String whereClause) {
        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(tableName);

        if (whereClause != null && !whereClause.isBlank()) {
            Map<String, Object> variables = VariableContext.get();
            String processedWhere = variableEngine.replacePlaceholders(whereClause, variables);
            sql.append(" WHERE ").append(processedWhere);
        }

        logger.debug("Querying: {}", sql);

        try {
            return jdbcTemplate.queryForList(sql.toString());
        } catch (Exception e) {
            logger.error("Failed to query table {}: {}", tableName, sql, e);
            throw new RuntimeException("Database query failed: " + sql, e);
        }
    }
}
