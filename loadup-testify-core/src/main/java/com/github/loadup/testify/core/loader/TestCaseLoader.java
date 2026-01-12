package com.github.loadup.testify.core.loader;

/*-
 * #%L
 * LoadUp Testify - Core
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

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.loadup.testify.common.exception.DataLoadingException;
import com.github.loadup.testify.common.util.JsonYamlUtils;
import com.github.loadup.testify.common.util.PathUtils;
import com.github.loadup.testify.common.variable.SharedVariablePool;
import com.github.loadup.testify.common.variable.VariableResolver;
import com.github.loadup.testify.core.model.PrepareData;
import com.github.loadup.testify.core.model.TestCaseConfig;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Loader for test case configurations and data.
 *
 * <p>Supports the following directory structure:
 *
 * <pre>
 * |- UserServiceTest.java
 * ├── UserService.createUser/
 * │   ├── case01/
 * │   │   ├── test_config.yaml
 * │   │   ├── PrepareData/
 * │   │   │   ├── table_users.csv
 * │   │   │   └── table_roles.csv
 * │   │   └── ExpectedData/
 * │   │       ├── table_users.csv
 * │   │       └── table_roles.csv
 * │   └── case02/
 * ├── UserService.createUserWithRole/
 * │   └── case01/
 * </pre>
 */
@Slf4j
@Component
public class TestCaseLoader {

  private final VariableResolver variableResolver;
  private final ObjectMapper yamlMapper;
  private final ObjectMapper jsonMapper;

  public TestCaseLoader(VariableResolver variableResolver) {
    this.variableResolver = variableResolver;
    this.yamlMapper = JsonYamlUtils.getYamlMapper();
    this.jsonMapper = JsonYamlUtils.getJsonMapper();
  }

  /**
   * Load all test case configurations for a test class. Scans all method directories (format:
   * ServiceName.methodName) and their case subdirectories.
   *
   * @param testClass the test class
   * @return a list of PrepareData objects, one for each test case
   */
  public List<PrepareData> loadTestCases(Class<?> testClass) {
    List<PrepareData> testCases = new ArrayList<>();

    // Get all method directories
    List<String> methodDirs = PathUtils.listMethodDirectories(testClass);

    for (String methodDirName : methodDirs) {
      // Parse service name and method name from directory name
      int dotIndex = methodDirName.lastIndexOf('.');
      if (dotIndex <= 0) {
        continue;
      }
      String serviceName = methodDirName.substring(0, dotIndex);
      String methodName = methodDirName.substring(dotIndex + 1);

      // Get all case directories for this method
      List<String> caseIds = PathUtils.listCaseDirectories(testClass, serviceName, methodName);

      for (String caseId : caseIds) {
        PrepareData prepareData = loadTestCase(testClass, serviceName, methodName, caseId);
        if (prepareData != null) {
          testCases.add(prepareData);
        }
      }
    }

    return testCases;
  }

  /**
   * Load test cases for a specific method.
   *
   * @param testClass the test class
   * @param serviceName the service name (e.g., "UserService")
   * @param methodName the method name (e.g., "createUser")
   * @return a list of PrepareData objects for the method
   */
  public List<PrepareData> loadTestCasesForMethod(
      Class<?> testClass, String serviceName, String methodName) {
    List<PrepareData> testCases = new ArrayList<>();
    List<String> caseIds = PathUtils.listCaseDirectories(testClass, serviceName, methodName);

    for (String caseId : caseIds) {
      PrepareData prepareData = loadTestCase(testClass, serviceName, methodName, caseId);
      if (prepareData != null) {
        testCases.add(prepareData);
      }
    }

    return testCases;
  }

  /**
   * Load a single test case configuration.
   *
   * @param testClass the test class
   * @param serviceName the service name
   * @param methodName the method name
   * @param caseId the case ID
   * @return the PrepareData object
   */
  public PrepareData loadTestCase(
      Class<?> testClass, String serviceName, String methodName, String caseId) {
    Path configPath = PathUtils.getTestConfigPath(testClass, serviceName, methodName, caseId);

    TestCaseConfig config;

    if (!Files.exists(configPath)) {
      log.debug(
          "No test_config.yaml found for {}.{}/{}, using default configuration",
          serviceName,
          methodName,
          caseId);
      config = new TestCaseConfig();
      config.setEnabled(true);
      config.setDescription("Implicit test case: " + caseId);
    } else {
      try (InputStream is = Files.newInputStream(configPath)) {
        config = yamlMapper.readValue(is, TestCaseConfig.class);
      } catch (IOException e) {
        log.error(
            "Failed to load test case config for {}.{}/{}", serviceName, methodName, caseId, e);
        return PrepareData.builder()
            .caseId(caseId)
            .serviceName(serviceName)
            .methodName(methodName)
            .loaded(false)
            .errorMessage(e.getMessage())
            .build();
      }
    }

    config.setCaseId(caseId);
    // Set method name from directory structure (overrides any config value)
    config.setMethod(methodName);

    // Clear the global pool and capture fresh variables for this test case.
    SharedVariablePool.clear();

    // Resolve variables in the configuration (this captures to SharedVariablePool)
    resolveVariablesInConfig(config);

    // Copy captured variables to PrepareData for per-test-case scoping
    Map<String, Object> capturedVariables = SharedVariablePool.getAll();

    return PrepareData.builder()
        .caseId(caseId)
        .serviceName(serviceName)
        .methodName(methodName)
        .config(config)
        .loaded(true)
        .capturedVariables(capturedVariables)
        .build();
  }

  /**
   * Convert test case arguments to method parameter types. If explicit args are missing, tries to
   * infer them from PrepareData CSVs.
   *
   * @param prepareData the prepare data object (contains config and context)
   * @param method the target method
   * @param testClass the test class (needed for path resolution)
   * @return an array of converted arguments
   */
  public Object[] convertArgs(PrepareData prepareData, Method method, Class<?> testClass) {
    TestCaseConfig config = prepareData.getConfig();
    List<Object> args = config.getArgs();
    Parameter[] parameters = method.getParameters();
    Object[] result = new Object[parameters.length];

    // 1. Explicit arguments provided
    if (args != null && !args.isEmpty()) {
      Type[] genericTypes = method.getGenericParameterTypes();
      for (int i = 0; i < parameters.length && i < args.size(); i++) {
        result[i] = convertToType(args.get(i), genericTypes[i]);
      }
      return result;
    }

    // 2. Smart Argument Injection (Implicit)
    // Scan PrepareData CSVs for matching column names
    Path prepareDataDir =
        PathUtils.getPrepareDataDirectory(
            testClass,
            prepareData.getServiceName(),
            prepareData.getMethodName(),
            prepareData.getCaseId());

    Map<String, String> candidateValues = new HashMap<>();
    Path[] csvFiles = PathUtils.getCsvFiles(prepareDataDir);

    // Read first row of each CSV to build candidate pool
    for (Path csvFile : csvFiles) {
      List<Map<String, String>> rows =
          com.github.loadup.testify.common.util.CsvUtils.readCsv(csvFile);
      if (!rows.isEmpty()) {
        candidateValues.putAll(rows.get(0));
      }
    }

    // Match parameters against candidate pool
    Type[] genericTypes = method.getGenericParameterTypes();
    for (int i = 0; i < parameters.length; i++) {
      Parameter param = parameters[i];
      String paramName = param.getName(); // Requires -parameters flag

      if (candidateValues.containsKey(paramName)) {
        String value = candidateValues.get(paramName);
        result[i] = convertToType(value, genericTypes[i]);
        log.debug("Auto-injected argument '{}' for parameter '{}'", value, paramName);
      } else {
        log.warn(
            "No candidate value found for parameter '{}' in test case {}.{}/{}",
            paramName,
            prepareData.getServiceName(),
            prepareData.getMethodName(),
            prepareData.getCaseId());
        result[i] =
            null; // Or default primitive value? convertToType handles nulls gracefully usually?
        // Wait, convertToType(null, int.class) -> exception or 0?
        // Let's rely on convertToType handling nulls for objects. Primitives might be
        // an issue.
        if (method.getParameterTypes()[i].isPrimitive()) {
          // Creating default for primitive
          if (method.getParameterTypes()[i] == boolean.class) result[i] = false;
          else result[i] = 0;
        }
      }
    }

    return result;
  }

  /**
   * Convert an object to a specific type using Jackson.
   *
   * @param value the value to convert
   * @param targetType the target type
   * @return the converted object
   */
  public Object convertToType(Object value, Type targetType) {
    if (value == null) {
      return null;
    }

    try {
      // First try to determine target raw class. For primitive/wrapper/simple types
      // we will
      // do a direct conversion without going through Jackson to improve
      // compatibility.
      Class<?> targetClass;
      if (targetType instanceof Class<?>) {
        targetClass = (Class<?>) targetType;
      } else {
        JavaType jt = jsonMapper.getTypeFactory().constructType(targetType);
        targetClass = jt.getRawClass();
      }

      if (isDirectlyConvertible(targetClass)) {
        return convertSimpleType(value, targetClass);
      }

      JavaType javaType = jsonMapper.getTypeFactory().constructType(targetType);

      // If it's already the correct type, return it
      if (javaType.getRawClass().isInstance(value)) {
        return value;
      }

      // Convert through JSON serialization
      String json = jsonMapper.writeValueAsString(value);
      return jsonMapper.readValue(json, javaType);
    } catch (IOException e) {
      throw new DataLoadingException("Failed to convert argument to type: " + targetType, e);
    }
  }

  // New helper to decide which classes should be converted without Jackson
  private boolean isDirectlyConvertible(Class<?> cls) {
    return cls == String.class
        || cls.isPrimitive()
        || isWrapperType(cls)
        || cls == BigDecimal.class
        || cls == BigInteger.class
        || Date.class.isAssignableFrom(cls)
        || isJavaTimeType(cls)
        || cls.isEnum();
  }

  // Helper to check for wrapper classes
  private boolean isWrapperType(Class<?> cls) {
    return cls == Boolean.class
        || cls == Byte.class
        || cls == Short.class
        || cls == Integer.class
        || cls == Long.class
        || cls == Float.class
        || cls == Double.class
        || cls == Character.class
        || cls == Void.class;
  }

  // Convert simple/primitive/wrapper/string/big types/enum/java-time/date without
  // Jackson
  private Object convertSimpleType(Object value, Class<?> targetClass) {
    // If already instance of target (covers wrapper types and many cases), return
    // directly
    if (targetClass.isInstance(value)) {
      return value;
    }

    // Handle String explicitly
    if (targetClass == String.class) {
      return String.valueOf(value);
    }

    // Handle BigDecimal / BigInteger - prefer creating from String to preserve
    // precision
    if (targetClass == BigDecimal.class) {
      if (value instanceof BigDecimal) return value;
      try {
        // Prefer the exact string representation to avoid double precision loss
        return new BigDecimal(String.valueOf(value));
      } catch (NumberFormatException nfe) {
        throw new DataLoadingException("Failed to convert value to BigDecimal: " + value, nfe);
      }
    }
    if (targetClass == BigInteger.class) {
      if (value instanceof BigInteger) return value;
      try {
        return new BigInteger(String.valueOf(value));
      } catch (NumberFormatException nfe) {
        throw new DataLoadingException("Failed to convert value to BigInteger: " + value, nfe);
      }
    }

    // Handle booleans
    if (targetClass == boolean.class || targetClass == Boolean.class) {
      if (value instanceof Boolean) {
        return value;
      }
      String s = String.valueOf(value);
      return Boolean.parseBoolean(s);
    }

    // Handle characters
    if (targetClass == char.class || targetClass == Character.class) {
      if (value instanceof Character) {
        return value;
      }
      String s = String.valueOf(value);
      if (!s.isEmpty()) {
        return s.charAt(0);
      }
      throw new DataLoadingException("Cannot convert value to char: " + value);
    }

    // Handle numbers (primitive wrappers)
    if (Number.class.isAssignableFrom(targetClass) || targetClass.isPrimitive()) {
      Number number = null;
      if (value instanceof Number) {
        number = (Number) value;
      } else {
        String s = String.valueOf(value);
        try {
          if (targetClass == byte.class || targetClass == Byte.class) {
            return Byte.parseByte(s);
          }
          if (targetClass == short.class || targetClass == Short.class) {
            return Short.parseShort(s);
          }
          if (targetClass == int.class || targetClass == Integer.class) {
            return Integer.parseInt(s);
          }
          if (targetClass == long.class || targetClass == Long.class) {
            return Long.parseLong(s);
          }
          if (targetClass == float.class || targetClass == Float.class) {
            return Float.parseFloat(s);
          }
          if (targetClass == double.class || targetClass == Double.class) {
            return Double.parseDouble(s);
          }
        } catch (NumberFormatException nfe) {
          throw new DataLoadingException(
              "Failed to convert value to number type " + targetClass + ": " + value, nfe);
        }
      }

      if (number != null) {
        if (targetClass == byte.class || targetClass == Byte.class) {
          return number.byteValue();
        }
        if (targetClass == short.class || targetClass == Short.class) {
          return number.shortValue();
        }
        if (targetClass == int.class || targetClass == Integer.class) {
          return number.intValue();
        }
        if (targetClass == long.class || targetClass == Long.class) {
          return number.longValue();
        }
        if (targetClass == float.class || targetClass == Float.class) {
          return number.floatValue();
        }
        if (targetClass == double.class || targetClass == Double.class) {
          return number.doubleValue();
        }
      }
    }

    // Handle enums
    if (targetClass.isEnum()) {
      String s = String.valueOf(value);
      // try by name
      try {
        @SuppressWarnings({"unchecked", "rawtypes"})
        Object enumVal = Enum.valueOf((Class<? extends Enum>) targetClass, s);
        return enumVal;
      } catch (IllegalArgumentException iae) {
        // try case-insensitive match
        for (Object constant : targetClass.getEnumConstants()) {
          if (String.valueOf(constant).equalsIgnoreCase(s)) {
            return constant;
          }
        }
        // try numeric ordinal
        try {
          int ord = Integer.parseInt(s);
          Object[] consts = targetClass.getEnumConstants();
          if (ord >= 0 && ord < consts.length) {
            return consts[ord];
          }
        } catch (NumberFormatException nfe) {
          // ignore
        }
        throw new DataLoadingException(
            "Failed to convert value to enum " + targetClass + ": " + value, iae);
      }
    }

    // Handle java.util.Date
    if (Date.class.isAssignableFrom(targetClass)) {
      if (value instanceof Date) return value;
      if (value instanceof Number) {
        return new Date(((Number) value).longValue());
      }
      String s = String.valueOf(value);
      // try ISO first
      Instant inst = tryParseToInstant(s);
      if (inst != null) {
        return Date.from(inst);
      }
      // try parse as epoch millis
      try {
        long ms = Long.parseLong(s);
        return new Date(ms);
      } catch (NumberFormatException nfe) {
        throw new DataLoadingException("Failed to convert value to Date: " + value, nfe);
      }
    }

    // Handle java.time types
    if (isJavaTimeType(targetClass)) {
      if (value instanceof Number) {
        long epoch = ((Number) value).longValue();
        if (targetClass == Instant.class) return Instant.ofEpochMilli(epoch);
        if (targetClass == LocalDate.class)
          return Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDate();
        if (targetClass == LocalDateTime.class)
          return Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime();
        if (targetClass == LocalTime.class)
          return Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalTime();
        if (targetClass == OffsetDateTime.class)
          return OffsetDateTime.ofInstant(Instant.ofEpochMilli(epoch), ZoneId.systemDefault());
        if (targetClass == ZonedDateTime.class)
          return ZonedDateTime.ofInstant(Instant.ofEpochMilli(epoch), ZoneId.systemDefault());
      }
      String s = String.valueOf(value);
      // try ISO or common formats
      Instant parsed = tryParseToInstant(s);
      if (parsed != null) {
        if (targetClass == Instant.class) return parsed;
        if (targetClass == LocalDate.class)
          return parsed.atZone(ZoneId.systemDefault()).toLocalDate();
        if (targetClass == LocalDateTime.class)
          return parsed.atZone(ZoneId.systemDefault()).toLocalDateTime();
        if (targetClass == LocalTime.class)
          return parsed.atZone(ZoneId.systemDefault()).toLocalTime();
        if (targetClass == OffsetDateTime.class)
          return OffsetDateTime.ofInstant(parsed, ZoneId.systemDefault());
        if (targetClass == ZonedDateTime.class)
          return ZonedDateTime.ofInstant(parsed, ZoneId.systemDefault());
      }
      // if parsing failed at this point, throw
      throw new DataLoadingException(
          "Failed to convert value to java.time type " + targetClass + ": " + value);
    }

    // Fallback: try to convert via string representation using Jackson as last
    // resort
    try {
      JavaType jt = jsonMapper.getTypeFactory().constructType(targetClass);
      String json = jsonMapper.writeValueAsString(value);
      return jsonMapper.readValue(json, jt);
    } catch (IOException e) {
      throw new DataLoadingException(
          "Failed to convert simple type to target type " + targetClass + ": " + value, e);
    }
  }

  // Common formatters to try for non-ISO date/time strings (order matters)
  private static final DateTimeFormatter[] COMMON_FORMATTERS =
      new DateTimeFormatter[] {
        DateTimeFormatter.ISO_DATE_TIME,
        DateTimeFormatter.ISO_OFFSET_DATE_TIME,
        DateTimeFormatter.ISO_INSTANT,
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault()),
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault()),
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault()),
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()),
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault()),
        DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss", Locale.getDefault()),
        DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault()),
        DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.getDefault()),
        DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault()),
        DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.getDefault()),
        DateTimeFormatter.ofPattern("yyyyMMdd", Locale.getDefault())
      };

  // Try parsing a string to Instant using ISO or common formatters; return null
  // if none match
  private Instant tryParseToInstant(String s) {
    if (s == null || s.isEmpty()) return null;
    // try ISO instant first
    try {
      return Instant.parse(s);
    } catch (DateTimeParseException ignored) {
    }

    // try common formatters
    for (DateTimeFormatter fmt : COMMON_FORMATTERS) {
      try {
        // try parsing as ZonedDateTime
        try {
          return ZonedDateTime.parse(s, fmt).toInstant();
        } catch (DateTimeParseException ignored) {
        }
        // try OffsetDateTime
        try {
          return OffsetDateTime.parse(s, fmt).toInstant();
        } catch (DateTimeParseException ignored) {
        }
        // try LocalDateTime
        try {
          LocalDateTime ldt = LocalDateTime.parse(s, fmt);
          return ldt.atZone(ZoneId.systemDefault()).toInstant();
        } catch (DateTimeParseException ignored) {
        }
        // try LocalDate (at start of day)
        try {
          LocalDate ld = LocalDate.parse(s, fmt);
          return ld.atStartOfDay(ZoneId.systemDefault()).toInstant();
        } catch (DateTimeParseException ignored) {
        }
      } catch (Exception ignore) {
        // move to next formatter
      }
    }
    return null;
  }

  private boolean isJavaTimeType(Class<?> cls) {
    return cls == Instant.class
        || cls == LocalDate.class
        || cls == LocalDateTime.class
        || cls == LocalTime.class
        || cls == OffsetDateTime.class
        || cls == ZonedDateTime.class;
  }

  /** Resolve variable expressions in the test case configuration. */
  private void resolveVariablesInConfig(TestCaseConfig config) {
    if (config.getArgs() != null) {
      List<Object> resolvedArgs = new ArrayList<>();
      for (Object arg : config.getArgs()) {
        resolvedArgs.add(resolveVariablesInObject(arg, true));
      }
      config.setArgs(resolvedArgs);
    }

    if (config.getResult() != null) {
      config.setResult(resolveVariablesInObject(config.getResult(), false));
    }
  }

  /** Recursively resolve variables in an object (map, list, or string). */
  @SuppressWarnings("unchecked")
  private Object resolveVariablesInObject(Object obj, boolean captureToPool) {
    if (obj == null) {
      return null;
    }

    if (obj instanceof String) {
      return variableResolver.resolve((String) obj, captureToPool);
    }

    if (obj instanceof Map) {
      Map<String, Object> map = (Map<String, Object>) obj;
      for (Map.Entry<String, Object> entry : map.entrySet()) {
        entry.setValue(resolveVariablesInObject(entry.getValue(), captureToPool));
      }
      return map;
    }

    if (obj instanceof List) {
      List<Object> list = (List<Object>) obj;
      List<Object> resolvedList = new ArrayList<>();
      for (Object item : list) {
        resolvedList.add(resolveVariablesInObject(item, captureToPool));
      }
      return resolvedList;
    }

    return obj;
  }
}
