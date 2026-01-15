package com.github.loadup.testify.asserts.diff;

import com.github.loadup.testify.asserts.model.FieldDiff;
import com.github.loadup.testify.asserts.model.RowDiff;

import java.util.Map;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 负责格式化断言失败后的详细报告
 */
public class DiffReportBuilder {

    private static final int COL_WIDTH_FIELD = 20;
    private static final int COL_WIDTH_VAL = 25;
    private static final String LINE_SEP = System.lineSeparator();

    /**
     * 构建完整的数据库校验失败报告
     */
    public static String build(String tableName, List<RowDiff> diffs) {
        StringBuilder sb = new StringBuilder();
        sb.append(LINE_SEP).append("❌ [Database Assertion Failed] Table: ").append(tableName).append(LINE_SEP);
        sb.append("=".repeat(85)).append(LINE_SEP);

        for (RowDiff row : diffs) {
            sb.append(String.format("Row Index: [%d] | Status: %-8s | Match Criteria: %s",
                            row.index(), row.status(), formatMatchCriteria(row.rawExpected())))
                    .append(LINE_SEP);

            if ("MISSING".equals(row.status())) {
                sb.append("  ↳ Error: In the database, no record was found that matches the above criteria.")
                        .append(LINE_SEP);
            } else if (row.fieldDiffs() != null && !row.fieldDiffs().isEmpty()) {
                sb.append(drawHeader());
                row.fieldDiffs().forEach((field, fd) -> sb.append(drawRow(field, fd)));
                sb.append(drawFooter());
            }
            sb.append("-".repeat(85)).append(LINE_SEP);
        }

        return sb.toString();
    }

    private static String formatMatchCriteria(Map<String, Object> expected) {
        if (expected.containsKey("_match")) {
            return expected.get("_match").toString();
        }
        return "By Row Index (No _match key provided)";
    }

    private static String drawHeader() {
        return String.format("  | %-" + COL_WIDTH_FIELD + "s | %-" + COL_WIDTH_VAL + "s | %-" + COL_WIDTH_VAL + "s | %s",
                "Field", "Expected (Operator)", "Actual Value", "Message") + LINE_SEP +
                "  | " + "-".repeat(COL_WIDTH_FIELD) + " | " + "-".repeat(COL_WIDTH_VAL) + " | " + "-".repeat(COL_WIDTH_VAL) + " | " + "-".repeat(15) + LINE_SEP;
    }

    private static String drawRow(String field, FieldDiff fd) {
        // 对长内容（如 JSON）进行截断展示，防止破坏表格结构
        String exp = truncate(String.valueOf(fd.expected()));
        String act = truncate(String.valueOf(fd.actual()));

        return String.format("  | %-" + COL_WIDTH_FIELD + "s | %-" + COL_WIDTH_VAL + "s | %-" + COL_WIDTH_VAL + "s | %s",
                field, exp, act, fd.message()) + LINE_SEP;
    }

    private static String drawFooter() {
        return LINE_SEP;
    }

    private static String truncate(String text) {
        if (text == null) return "null";
        // 如果是换行符（如多行 JSON），转义后展示前 22 位
        String flat = text.replace("\n", "\\n").replace("\r", "");
        return flat.length() > COL_WIDTH_VAL ? flat.substring(0, COL_WIDTH_VAL - 3) + "..." : flat;
    }
}