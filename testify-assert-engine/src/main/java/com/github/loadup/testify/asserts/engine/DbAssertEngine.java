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
import com.github.loadup.testify.data.engine.variable.VariableEngine;
import java.util.*;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
public class DbAssertEngine {
  private static final ObjectMapper objectMapper = new ObjectMapper();
  private String columnNamingStrategy = "caseInsensitive";

  public DbAssertEngine() {}

  public DbAssertEngine(String strategy) {
    this.columnNamingStrategy = strategy;
  }

  /** 对外入口：解析变量并触发单表/多表比对 */
  public void compare(JsonNode expectNode, Map<String, Object> context) {
    if (expectNode == null || !expectNode.has("database")) return;

    // 1. 变量解析：一次性解析整个 expect 块（包括 ${nowTime} 等）
    VariableEngine variableEngine = SpringContextHolder.getBean(VariableEngine.class);
    JsonNode resolvedExpect = variableEngine.resolveJsonNode(expectNode, context);

    JsonNode dbExpect = resolvedExpect.get("database");
    if (dbExpect.isArray()) {
      dbExpect.forEach(tableNode -> processTableAssertion(tableNode, context));
    } else {
      processTableAssertion(dbExpect, context);
    }
  }

  private void processTableAssertion(JsonNode tableNode, Map<String, Object> context) {
    String tableName = tableNode.get("table").asText();
    String mode = tableNode.has("mode") ? tableNode.get("mode").asText() : "lenient";
    JsonNode rowsNode = tableNode.get("rows");

    // 2. 转换数据结构
    List<Map<String, Object>> expectedRows =
        objectMapper.convertValue(rowsNode, new TypeReference<>() {});

    // 3. 抓取数据：根据解析后的 rowsNode 构造 SQL
    List<Map<String, Object>> actualRows = fetchActualRows(tableName, expectedRows);

    // 4. 执行验证
    verify(tableName, actualRows, expectedRows, mode);
  }

  public void verify(
      String tableName,
      List<Map<String, Object>> actualRows,
      List<Map<String, Object>> expectedRows,
      String mode) {

    List<RowDiff> diffs = new ArrayList<>();

    // 标准化实际行 Key (snake_case -> camelCase)
    List<Map<String, Object>> normalizedActuals =
        actualRows.stream()
            .map(row -> ColumnNormalizer.normalizeMap(row, columnNamingStrategy))
            .collect(Collectors.toList());

    // 1. 严格模式下的行数校验
    if ("strict".equalsIgnoreCase(mode) && normalizedActuals.size() != expectedRows.size()) {
      throw new AssertionError(
          String.format(
              "表 [%s] 行数不匹配! 期望: %d, 实际: %d",
              tableName, expectedRows.size(), normalizedActuals.size()));
    }

    // 2. 逐行比对
    for (int i = 0; i < expectedRows.size(); i++) {
      Map<String, Object> expectedRow = expectedRows.get(i);
      Map<String, Object> normalizedExpected =
          ColumnNormalizer.normalizeMap(expectedRow, columnNamingStrategy);

      // 寻找匹配行
      Map<String, Object> actualRow = findMatchedRow(normalizedActuals, normalizedExpected, i);

      if (actualRow == null) {
        diffs.add(new RowDiff(i, "MISSING", "数据库中未找到匹配行", expectedRow, null));
        continue;
      }

      // 字段级比对
      Map<String, FieldDiff> fieldDiffs = new HashMap<>();
      normalizedExpected.forEach(
          (field, expVal) -> {
            if ("_match".equals(field)) return;

            Object actVal = actualRow.get(field);
            MatchResult result = OperatorProcessor.process(actVal, expVal);

            if (!result.isPassed()) {
              fieldDiffs.put(field, new FieldDiff(expVal, actVal, result.message()));
            }
          });

      if (!fieldDiffs.isEmpty()) {
        diffs.add(new RowDiff(i, "DIFF", "字段校验失败", expectedRow, fieldDiffs));
      }
    }

    if (!diffs.isEmpty()) {
      throw new AssertionError(DiffReportBuilder.build(tableName, diffs));
    }
  }

  private Map<String, Object> findMatchedRow(
      List<Map<String, Object>> actuals, Map<String, Object> expected, int index) {
    if (!expected.containsKey("_match")) {
      return index < actuals.size() ? actuals.get(index) : null;
    }

    Map<String, Object> criteria = (Map<String, Object>) expected.get("_match");
    Map<String, Object> normalizedCriteria =
        ColumnNormalizer.normalizeMap(criteria, columnNamingStrategy);

    return actuals.stream()
        .filter(
            row ->
                normalizedCriteria.entrySet().stream()
                    .allMatch(
                        e -> {
                          // 使用 OperatorProcessor 而不是简单的 equals，这样 _match 里也能用算子
                          return OperatorProcessor.process(row.get(e.getKey()), e.getValue())
                              .isPassed();
                        }))
        .findFirst()
        .orElse(null);
  }

  private List<Map<String, Object>> fetchActualRows(
      String tableName, List<Map<String, Object>> expectedRows) {
    JdbcTemplate jdbcTemplate = SpringContextHolder.getBean(JdbcTemplate.class);
    List<Map<String, Object>> results = new ArrayList<>();

    for (Map<String, Object> row : expectedRows) {
      if (row.containsKey("_match")) {
        Map<String, Object> criteria = (Map<String, Object>) row.get("_match");
        StringBuilder sql =
            new StringBuilder("SELECT * FROM ").append(tableName).append(" WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        criteria.forEach(
            (k, v) -> {
              // 只有当 v 是简单值时才加入 SQL 条件
              if (!(v instanceof Map)) {
                sql.append(" AND ").append(k).append(" = ?");
                params.add(v);
              }
            });
        results.addAll(jdbcTemplate.queryForList(sql.toString(), params.toArray()));
      }
    }

    // 兜底：如果没有 _match 或没搜到，且期望行数较少，尝试全表前100条
    if (results.isEmpty() && !expectedRows.isEmpty()) {
      return jdbcTemplate.queryForList("SELECT * FROM " + tableName + " LIMIT 100");
    }
    return results;
  }
}
