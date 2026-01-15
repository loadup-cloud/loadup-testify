package com.github.loadup.testify.asserts.operator.impl;

import com.github.loadup.testify.asserts.operator.OperatorMatcher;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

/**
 * Approximate time matcher for handling time comparisons with delta tolerance.
 * Supports multiple timestamp formats:
 * - java.time.Instant
 * - java.time.LocalDateTime
 * - java.sql.Timestamp
 * - ISO-8601 strings
 * - Epoch milliseconds (Long)
 */
public class ApproxTimeMatcher implements OperatorMatcher {

  /**
   * Match two timestamps within a delta tolerance (in milliseconds).
   */
  @Override
  public String match(Object actual, Object expected) {
    if (!(expected instanceof Map<?, ?> config)) {
      return "ApproxTimeMatcher requires operator config";
    }

    Object deltaValue = config.get("delta");
    long delta = 1000; // Default 1 second tolerance

    if (deltaValue != null) {
      if (deltaValue instanceof Number num) {
        delta = num.longValue();
      } else {
        try {
          delta = Long.parseLong(String.valueOf(deltaValue));
        } catch (NumberFormatException e) {
          return "Invalid delta value: " + deltaValue;
        }
      }
    }

    Object val = config.get("val");

    try {
      long actualTs = toTimestamp(actual);
      long expectedTs = toTimestamp(val);

      long diff = Math.abs(actualTs - expectedTs);

      if (diff <= delta) {
        return null; // Match successful
      } else {
        return String.format(
            "Time difference exceeds delta: expected offset < %d ms, actual offset = %d ms (actual: %s, expected: %s)",
            delta, diff, actual, val);
      }
    } catch (Exception e) {
      return "Error during time comparison: " + e.getMessage();
    }
  }

  /**
   * Convert various timestamp representations to epoch milliseconds.
   */
  private long toTimestamp(Object value) {
    if (value == null) {
      throw new IllegalArgumentException("Timestamp value cannot be null");
    }

    // Already epoch milliseconds
    if (value instanceof Long longVal) {
      return longVal;
    }

    if (value instanceof Integer intVal) {
      return intVal.longValue();
    }

    // java.time.Instant
    if (value instanceof Instant instant) {
      return instant.toEpochMilli();
    }

    // java.time.LocalDateTime
    if (value instanceof LocalDateTime ldt) {
      return ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    // java.sql.Timestamp
    if (value instanceof java.sql.Timestamp ts) {
      return ts.getTime();
    }

    // java.util.Date
    if (value instanceof java.util.Date date) {
      return date.getTime();
    }

    // Try parsing as string
    if (value instanceof String str) {
      return parseTimestampString(str);
    }

    throw new IllegalArgumentException(
        "Unsupported timestamp type: " + value.getClass().getName() + ", value: " + value);
  }

  /**
   * Parse timestamp string in various formats.
   */
  private long parseTimestampString(String str) {
    // Try epoch milliseconds
    try {
      return Long.parseLong(str);
    } catch (NumberFormatException ignored) {
      // Not a plain number, continue
    }

    // Try ISO-8601 instant format
    try {
      return Instant.parse(str).toEpochMilli();
    } catch (DateTimeParseException ignored) {
      // Continue to next format
    }

    // Try LocalDateTime formats
    DateTimeFormatter[] formatters = {
        DateTimeFormatter.ISO_LOCAL_DATE_TIME,
        DateTimeFormatter.ISO_DATE_TIME,
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
    };

    for (DateTimeFormatter formatter : formatters) {
      try {
        LocalDateTime ldt = LocalDateTime.parse(str, formatter);
        return ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
      } catch (DateTimeParseException ignored) {
        // Try next formatter
      }
    }

    throw new IllegalArgumentException(
        "Unable to parse timestamp string: " + str +
            ". Expected formats: epoch millis, ISO-8601, or 'yyyy-MM-dd HH:mm:ss'");
  }

  @Override
  public boolean support(Object expectedConfig) {
    return expectedConfig instanceof Map && "approx".equals(((Map<?, ?>) expectedConfig).get("op"));
  }
}
