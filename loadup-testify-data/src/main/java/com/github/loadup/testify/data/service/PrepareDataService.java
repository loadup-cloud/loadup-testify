package com.github.loadup.testify.data.service;

/*-
 * #%L
 * LoadUp Testify - Data
 * %%
 * Copyright (C) 2026 LoadUp Cloud
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import com.github.loadup.testify.common.exception.DataLoadingException;
import com.github.loadup.testify.common.util.CsvUtils;
import com.github.loadup.testify.common.util.PathUtils;
import com.github.loadup.testify.common.variable.VariableResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service for preparing test data by inserting data into the database from CSV files.
 * 
 * <p>Supports multiple CSV files per PrepareData directory, each corresponding to a different table.</p>
 */
@Slf4j
@Service
public class PrepareDataService {

    private final JdbcTemplate jdbcTemplate;
    private final VariableResolver variableResolver;

    public PrepareDataService(JdbcTemplate jdbcTemplate, VariableResolver variableResolver) {
        this.jdbcTemplate = jdbcTemplate;
        this.variableResolver = variableResolver;
    }

    /**
     * Prepare test data by loading CSV files from the PrepareData directory and inserting into the database.
     * This method first clears the target tables, then inserts the data.
     *
     * @param testClass   the test class
     * @param serviceName the service name (e.g., "UserService")
     * @param methodName  the method name (e.g., "createUser")
     * @param caseId      the case ID (e.g., "case01")
     */
    @Transactional
    public void prepareData(Class<?> testClass, String serviceName, String methodName, String caseId) {
        Path prepareDataDir = PathUtils.getPrepareDataDirectory(testClass, serviceName, methodName, caseId);
        
        if (!PathUtils.exists(prepareDataDir)) {
            log.debug("No PrepareData directory found for case: {}.{}/{}", serviceName, methodName, caseId);
            return;
        }

        Path[] csvFiles = PathUtils.getCsvFiles(prepareDataDir);
        
        if (csvFiles.length == 0) {
            log.debug("No CSV files found in PrepareData directory for case: {}.{}/{}", serviceName, methodName, caseId);
            return;
        }
        
        log.info("Found {} CSV files in PrepareData for {}.{}/{}", csvFiles.length, serviceName, methodName, caseId);
        
        // Clear tables before inserting new data.
        // Note: Tables are cleared in reverse alphabetical order (based on filename).
        for (int i = csvFiles.length - 1; i >= 0; i--) {
            String tableName = PathUtils.extractTableName(csvFiles[i]);
            clearTables(tableName);
        }
        
        // Then, insert the data for each table
        for (Path csvFile : csvFiles) {
            String tableName = PathUtils.extractTableName(csvFile);
            insertData(tableName, csvFile);
        }
    }

    /**
     * Insert data from a CSV file into a database table.
     *
     * @param tableName the target table name
     * @param csvPath   the path to the CSV file
     */
    @Transactional
    public void insertData(String tableName, Path csvPath) {
        List<Map<String, String>> data = CsvUtils.readCsv(csvPath, variableResolver, true);
        
        if (data.isEmpty()) {
            log.debug("No data to insert for table: {}", tableName);
            return;
        }

        for (Map<String, String> row : data) {
            insertRow(tableName, row);
        }

        log.info("Inserted {} rows into table: {}", data.size(), tableName);
    }

    /**
     * Insert a single row into a table.
     *
     * @param tableName the target table name
     * @param row       the row data as a map of column name to value
     */
    private void insertRow(String tableName, Map<String, String> row) {
        if (row.isEmpty()) {
            return;
        }

        List<String> columns = new ArrayList<>(row.keySet());
        List<String> placeholders = columns.stream()
                .map(c -> "?")
                .collect(Collectors.toList());
        List<Object> values = columns.stream()
                .map(row::get)
                .collect(Collectors.toList());

        String sql = String.format(
                "INSERT INTO %s (%s) VALUES (%s)",
                sanitizeIdentifier(tableName),
                columns.stream().map(this::sanitizeIdentifier).collect(Collectors.joining(", ")),
                String.join(", ", placeholders)
        );

        log.debug("Executing SQL: {} with values: {}", sql, values);
        jdbcTemplate.update(sql, values.toArray());
    }

    /**
     * Clear all data from the specified tables.
     *
     * @param tableNames the table names to clear
     */
    @Transactional
    public void clearTables(String... tableNames) {
        for (String tableName : tableNames) {
            String sql = "DELETE FROM " + sanitizeIdentifier(tableName);
            jdbcTemplate.update(sql);
            log.info("Cleared table: {}", tableName);
        }
    }

    /**
     * Sanitize a SQL identifier (table or column name) to prevent SQL injection.
     *
     * @param identifier the identifier to sanitize
     * @return the sanitized identifier
     */
    private String sanitizeIdentifier(String identifier) {
        if (identifier == null || identifier.isEmpty()) {
            throw new DataLoadingException("Invalid identifier: null or empty");
        }
        // Only allow alphanumeric characters and underscores
        if (!identifier.matches("^[a-zA-Z_][a-zA-Z0-9_]*$")) {
            throw new DataLoadingException("Invalid identifier: " + identifier);
        }
        return identifier;
    }
}
