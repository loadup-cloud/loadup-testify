package com.loadup.testify.data.service;

import com.github.loadup.testify.context.TestContext;
import com.github.loadup.testify.model.DatabaseSetup;
import com.github.loadup.testify.model.TableData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service for database initialization operations
 */
@Slf4j
@Service
public class DatabaseInitService {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseInitService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Setup database according to configuration
     */
    @Transactional
    public void setup(DatabaseSetup setup) {
        if (setup == null) {
            return;
        }

        // Clean before if configured
        if (setup.isCleanBefore()) {
            cleanTables(setup.getTruncateTables());
        }

        // Insert data
        if (setup.getData() != null) {
            insertData(setup.getData());
        }
    }

    /**
     * Clean database after test
     */
    @Transactional
    public void cleanup(DatabaseSetup setup) {
        if (setup == null || !setup.isCleanAfter()) {
            return;
        }

        cleanTables(setup.getTruncateTables());
    }

    /**
     * Truncate tables
     */
    private void cleanTables(List<String> tables) {
        if (tables == null || tables.isEmpty()) {
            return;
        }

        for (String table : tables) {
            log.info("Truncating table: {}", table);
            try {
                jdbcTemplate.execute("TRUNCATE TABLE " + table);
            } catch (Exception e) {
                log.warn("Failed to truncate table {}, trying DELETE: {}", table, e.getMessage());
                jdbcTemplate.execute("DELETE FROM " + table);
            }
        }
    }

    /**
     * Insert data into tables
     */
    private void insertData(Map<String, List<TableData>> data) {
        TestContext context = TestContext.current();

        for (Map.Entry<String, List<TableData>> entry : data.entrySet()) {
            String tableName = entry.getKey();
            List<TableData> records = entry.getValue();

            for (TableData record : records) {
                insertRecord(tableName, record, context);
            }
        }
    }

    /**
     * Insert single record
     */
    private void insertRecord(String tableName, TableData record, TestContext context) {
        Map<String, Object> columns = record.getColumns();

        if (columns == null || columns.isEmpty()) {
            log.warn("No columns specified for table: {}", tableName);
            return;
        }

        // Resolve references in column values
        for (Map.Entry<String, Object> entry : columns.entrySet()) {
            Object resolved = context.resolveReference(entry.getValue());
            entry.setValue(resolved);
        }

        // Build INSERT statement
        String columnNames = String.join(", ", columns.keySet());
        String placeholders = columns.keySet().stream()
                .map(k -> "?")
                .collect(Collectors.joining(", "));

        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)",
                tableName, columnNames, placeholders);

        log.debug("Executing SQL: {} with values: {}", sql, columns.values());

        // Execute insert and capture generated key
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int index = 1;
            for (Object value : columns.values()) {
                ps.setObject(index++, value);
            }
            return ps;
        }, keyHolder);

        // Store generated key if refId is specified
        if (record.getRefId() != null && keyHolder.getKey() != null) {
            Object generatedKey = keyHolder.getKey();
            context.putDatabaseReference(record.getRefId(), generatedKey);
            log.info("Stored database reference: {} -> {}", record.getRefId(), generatedKey);
        }
    }
}
