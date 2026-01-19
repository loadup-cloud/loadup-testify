package com.github.loadup.testify.asserts.engine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.loadup.testify.asserts.diff.DiffReportBuilder;
import com.github.loadup.testify.asserts.model.FieldDiff;
import com.github.loadup.testify.asserts.model.MatchResult;
import com.github.loadup.testify.asserts.model.RowDiff;
import com.github.loadup.testify.asserts.operator.OperatorProcessor;
import com.github.loadup.testify.asserts.util.ColumnNormalizer;
import com.github.loadup.testify.core.util.SpringContextHolder;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

/**
 * Database assertion engine with support for: - Case-insensitive column name matching - Snake_case
 * to camelCase conversion - Rich diff reporting using DiffReportBuilder - Row matching by index or
 * custom criteria
 */
public class DbAssertEngine {
  private static final ObjectMapper objectMapper = new ObjectMapper();
  private String columnNamingStrategy = "caseInsensitive"; // Default strategy

  public DbAssertEngine() {}

  public DbAssertEngine(String columnNamingStrategy) {
    this.columnNamingStrategy = columnNamingStrategy;
  }

  public void setColumnNamingStrategy(String strategy) {
    this.columnNamingStrategy = strategy;
  }

  /**
   * Verify database assertion.
   *
   * @param tableName Table being verified
   * @param actualRows Actual rows from database query
   * @param expectedRows Expected rows from YAML
   * @param mode "strict" for exact row count match, otherwise lenient
   */
  public void verify(
      String tableName,
      List<Map<String, Object>> actualRows,
      List<Map<String, Object>> expectedRows,
      String mode) {
    List<RowDiff> diffs = new ArrayList<>();

    // Normalize all actual rows for consistent comparison
    List<Map<String, Object>> normalizedActualRows = new ArrayList<>();
    for (Map<String, Object> row : actualRows) {
      normalizedActualRows.add(ColumnNormalizer.normalizeMap(row, columnNamingStrategy));
    }

    // 1. Row count validation (Strict mode)
    if ("strict".equalsIgnoreCase(mode) && normalizedActualRows.size() != expectedRows.size()) {
      throw new AssertionError(
          String.format(
              "Table [%s] row count mismatch! Expected: %d, Actual: %d",
              tableName, expectedRows.size(), normalizedActualRows.size()));
    }

    // 2. Row-by-row comparison
    for (int i = 0; i < expectedRows.size(); i++) {
      Map<String, Object> expectedRow = expectedRows.get(i);

      // Normalize expected row keys
      Map<String, Object> normalizedExpectedRow =
          ColumnNormalizer.normalizeMap(expectedRow, columnNamingStrategy);

      // Find matching row: by criteria (_match field) or by index
      Map<String, Object> actualRow =
          findMatchedRow(normalizedActualRows, normalizedExpectedRow, i);

      if (actualRow == null) {
        diffs.add(
            new RowDiff(i, "MISSING", "No matching row found in database", expectedRow, null));
        continue;
      }

      // Validate each field in the row
      Map<String, FieldDiff> fieldDiffs = new HashMap<>();
      normalizedExpectedRow.forEach(
          (field, expVal) -> {
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

  /** Find matching row using _match criteria or by index. */
  private Map<String, Object> findMatchedRow(
      List<Map<String, Object>> actuals, Map<String, Object> expected, int index) {
    if (!expected.containsKey("_match")) {
      // Match by index
      return index < actuals.size() ? actuals.get(index) : null;
    }

    // Match by criteria
    @SuppressWarnings("unchecked")
    Map<String, Object> criteria = (Map<String, Object>) expected.get("_match");
    Map<String, Object> normalizedCriteria =
        ColumnNormalizer.normalizeMap(criteria, columnNamingStrategy);

    // 修改 findMatchedRow 内部的 filter 逻辑
    return actuals.stream()
        .filter(
            row ->
                normalizedCriteria.entrySet().stream()
                    .allMatch(
                        e -> {
                          Object exp = e.getValue();
                          Object act = row.get(e.getKey());
                          // 使用更健壮的比对，处理数值类型差异
                          if (exp instanceof Number && act instanceof Number) {
                            return Double.compare(
                                    ((Number) exp).doubleValue(), ((Number) act).doubleValue())
                                == 0;
                          }
                          return String.valueOf(exp).equals(String.valueOf(act));
                        }))
        .findFirst()
        .orElse(null);
  }

  public void compare(JsonNode expectNode, Map<String, Object> context) {
    if (expectNode == null || !expectNode.has("database")) return;

    JsonNode dbExpect = expectNode.get("database");
    // 支持 database 块是对象（单表）或数组（多表）
    if (dbExpect.isArray()) {
      for (JsonNode tableExpect : dbExpect) {
        processTableAssertion(tableExpect, context);
      }
    } else {
      processTableAssertion(dbExpect, context);
    }
  }

  private void processTableAssertion(JsonNode tableNode, Map<String, Object> context) {
    String tableName = tableNode.get("table").asText();
    String mode = tableNode.has("mode") ? tableNode.get("mode").asText() : "lenient";
    JsonNode rowsNode = tableNode.get("rows");

    // 1. 转换期望行
    List<Map<String, Object>> expectedRows = convertJsonNodeToList(rowsNode);

    // 2. 从数据库抓取实际行
    List<Map<String, Object>> actualRows = fetchActualRows(tableName, rowsNode, context);

    // 3. 执行比对
    verify(tableName, actualRows, expectedRows, mode);
  }

  /** 将 JsonNode (ArrayNode) 转换为 List<Map> */
  private List<Map<String, Object>> convertJsonNodeToList(JsonNode rowsNode) {
    if (rowsNode == null || !rowsNode.isArray()) {
      return Collections.emptyList();
    }
    // 使用 Jackson 的 TypeReference 进行深度转换，保持类型正确（如 Boolean, Number）
    return objectMapper.convertValue(rowsNode, new TypeReference<>() {});
  }

  private List<Map<String, Object>> fetchActualRows(
      String tableName, JsonNode rowsNode, Map<String, Object> context) {
    JdbcTemplate jdbcTemplate = SpringContextHolder.getBean(JdbcTemplate.class);
    List<Map<String, Object>> results = new ArrayList<>();

    for (JsonNode rowNode : rowsNode) {
      if (rowNode.has("_match")) {
        JsonNode matchNode = rowNode.get("_match");
        Map<String, Object> criteria =
            objectMapper.convertValue(matchNode, new TypeReference<>() {});

        StringBuilder sql =
            new StringBuilder("SELECT * FROM ").append(tableName).append(" WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
          sql.append(" AND ").append(entry.getKey()).append(" = ?");
          params.add(entry.getValue());
        }

        results.addAll(jdbcTemplate.queryForList(sql.toString(), params.toArray()));
      }
    }

    // 如果 rows 里面没有任何 _match 且 actual 依然为空，可能需要兜底全表扫描（视业务而定）
    if (results.isEmpty() && !rowsNode.isEmpty() && !rowsNode.get(0).has("_match")) {
      return jdbcTemplate.queryForList("SELECT * FROM " + tableName + " LIMIT 100");
    }

    return results;
  }
}
