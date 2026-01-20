package com.github.loadup.testify.asserts.operator.impl;

import com.github.loadup.testify.asserts.model.MatchResult;
import com.github.loadup.testify.asserts.operator.OperatorMatcher;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

/**
 * Approximate time matcher for handling time comparisons with delta tolerance. Supports multiple
 * timestamp formats: - java.time.Instant - java.time.LocalDateTime - java.sql.Timestamp - ISO-8601
 * strings - Epoch milliseconds (Long)
 */
public class ApproxTimeMatcher implements OperatorMatcher {
  @Override
  public boolean support(String op) {
    return "approx".equals(op);
  }

  @Override
  public MatchResult match(Object actual, Object val, Map<String, Object> config) {
    // 默认容差为 3000 毫秒
    long threshold = Long.parseLong(String.valueOf(config.getOrDefault("threshold", 3000)));

    try {
      long actTime = toMillis(actual);
      long expTime = toMillis(val);

      long diff = Math.abs(actTime - expTime);
      return diff <= threshold
          ? MatchResult.pass()
          : MatchResult.fail(
              actual, val, String.format("Time diff %dms exceeds threshold %dms", diff, threshold));
    } catch (Exception e) {
      return MatchResult.fail(actual, val, "Time parsing failed: " + e.getMessage());
    }
  }

  private long toMillis(Object value) {
    if (value == null) throw new IllegalArgumentException("时间值不能为空");

    // 如果是数字（时间戳）
    if (value instanceof Number n) return n.longValue();

    // 如果是 Java 8 时间对象
    if (value instanceof java.time.temporal.TemporalAccessor ta) {
      return LocalDateTime.from(ta).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    // 如果是 SQL Timestamp 或 Date
    if (value instanceof java.util.Date d) return d.getTime();

    // 如果是字符串，尝试多种常用格式解析
    String str = value.toString();
    try {
      // 尝试 ISO 格式: 2023-10-01T10:00:00
      return LocalDateTime.parse(str.replace(" ", "T"))
          .atZone(ZoneId.systemDefault())
          .toInstant()
          .toEpochMilli();
    } catch (Exception e) {
      // 兜底方案：使用 Timestamp.valueOf
      return java.sql.Timestamp.valueOf(str).getTime();
    }
  }
}
