package com.github.loadup.testify.data.engine.db;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.loadup.testify.data.engine.variable.VariableEngine;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.*;
import java.util.Map;

import io.micrometer.common.util.internal.logging.Slf4JLoggerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SqlExecutionEngine {

  private final JdbcTemplate jdbcTemplate;
  private final VariableEngine variableEngine;

  public void executeSetup(JsonNode setupNode, Map<String, Object> context, String yamlPath) {
    if (setupNode == null || setupNode.isNull()) return;

    // 1. 处理原始 SQL
    if (setupNode.has("clean_sql")) {
      String cleanSql = setupNode.get("clean_sql").asText();
      log.info("executing clean_sql: {}", cleanSql);
      executeRawSql(cleanSql, context);
    }

    // 2. 处理合并后的 db_setup (List 结构)
    if (setupNode.has("db_setup")) {
      JsonNode dbSetupNode = setupNode.get("db_setup");
      if (dbSetupNode.isArray()) {
        for (JsonNode item : dbSetupNode) {
          processSingleDbSetup(item, context, yamlPath);
        }
      }
    }
  }

  private void processSingleDbSetup(JsonNode item, Map<String, Object> context, String yamlPath) {
    String tableName = item.get("table").asText();

    if (item.has("file")) {
      // 模式 B: 从 CSV 加载
      String fileName = item.get("file").asText();
      executeCsvSetup(tableName, fileName, context, yamlPath);
    } else if (item.has("data")) {
      // 模式 A: 从 YAML Data 加载
      executeDataSetup(tableName, item.get("data"), context);
    }
  }

  /** 处理 YAML 中的 Data 列表 */
  private void executeDataSetup(String tableName, JsonNode dataNode, Map<String, Object> context) {
    if (!dataNode.isArray() || dataNode.isEmpty()) return;

    // 提取所有列名（以第一行为准）
    List<String> columns = new ArrayList<>();
    dataNode.get(0).fieldNames().forEachRemaining(columns::add);

    String sql = buildInsertSql(tableName, columns);
    List<Object[]> batchArgs = new ArrayList<>();

    for (JsonNode row : dataNode) {
      Object[] params = new Object[columns.size()];
      for (int i = 0; i < columns.size(); i++) {
        JsonNode cell = row.get(columns.get(i));
        // 解析变量并转换类型
        Object val = variableEngine.resolveJsonNode(cell, context);
        params[i] = extractValueFromJsonNode((JsonNode) val);
      }
      batchArgs.add(params);
    }

    jdbcTemplate.batchUpdate(sql, batchArgs);
    log.info("Inserted {} rows into {} from YAML data", batchArgs.size(), tableName);
  }

  /** 处理 CSV 文件加载 */
  private void executeCsvSetup(
      String tableName, String fileName, Map<String, Object> context, String yamlPath) {
    String csvPath = yamlPath.substring(0, yamlPath.lastIndexOf("/") + 1) + fileName;

    try (Reader reader =
        new InputStreamReader(
            new ClassPathResource(csvPath).getInputStream(), StandardCharsets.UTF_8)) {
      CSVFormat format =
          CSVFormat.DEFAULT
              .builder()
              .setHeader()
              .setSkipHeaderRecord(true)
              .setIgnoreSurroundingSpaces(true) // 自动 trim
              .setAllowMissingColumnNames(false)
              .build();

      CSVParser csvParser = format.parse(reader);

      List<String> headers = csvParser.getHeaderNames();
      String sql = buildInsertSql(tableName, headers);
      List<Object[]> batchArgs = new ArrayList<>();

      for (CSVRecord record : csvParser) {
        Object[] row = new Object[headers.size()];
        for (int i = 0; i < headers.size(); i++) {
          // CSV 里的每个 Cell 也支持变量解析
          String resolved = (String) variableEngine.resolveString(record.get(i), context);
          row[i] = "NULL".equalsIgnoreCase(resolved) ? null : resolved;
        }
        batchArgs.add(row);
      }

      jdbcTemplate.batchUpdate(sql, batchArgs);
      //      log.info("Inserted {} rows into {} from CSV: {}", batchArgs.size(), tableName,
      // fileName);

    } catch (Exception e) {
      throw new RuntimeException("Failed to load CSV: " + csvPath, e);
    }
  }

  private String buildInsertSql(String tableName, List<String> columns) {
    String cols = String.join(",", columns);
    String placeholders = String.join(",", Collections.nCopies(columns.size(), "?"));
    return String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, cols, placeholders);
  }

  private Object extractValueFromJsonNode(JsonNode node) {
    if (node.isNumber()) return node.numberValue();
    if (node.isBoolean()) return node.booleanValue();
    if (node.isNull()) return null;
    return node.asText();
  }

  /**
   * 执行原始 SQL 脚本，支持多行和变量替换
   *
   * @param rawSql 包含占位符的原始 SQL 字符串
   * @param context 变量上下文
   */
  private void executeRawSql(String rawSql, Map<String, Object> context) {
    if (rawSql == null || rawSql.isBlank()) {
      return;
    }

    // 1. 替换占位符 (例如将 ${userId} 替换为 123)
    String processedSql = variableEngine.replacePlaceholders(rawSql, context);

    // 使用 Spring 提供的 ScriptUtils 自动处理注释、分号拆分和换行
    jdbcTemplate.execute(
        (Connection conn) -> {
          ScriptUtils.executeSqlScript(
              conn, new org.springframework.core.io.ByteArrayResource(processedSql.getBytes()));
          return null;
        });
  }
}
