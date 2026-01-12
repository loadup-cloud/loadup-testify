package com.github.loadup.testify.core.engine;

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

import com.github.loadup.testify.assertions.service.AssertionService;
import com.github.loadup.testify.common.exception.TestifyException;
import com.github.loadup.testify.common.variable.SharedVariablePool;
import com.github.loadup.testify.core.loader.TestCaseLoader;
import com.github.loadup.testify.core.model.PrepareData;
import com.github.loadup.testify.core.model.TestCaseConfig;
import com.github.loadup.testify.data.service.ExpectedDataService;
import com.github.loadup.testify.data.service.PrepareDataService;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Core test execution engine that orchestrates the data-driven test lifecycle.
 *
 * <p>Directory structure convention:
 *
 * <pre>
 * {ServiceName}.{methodName}/{caseId}/
 * ├── test_config.yaml
 * ├── PrepareData/
 * │   ├── table_users.csv
 * │   └── table_roles.csv
 * └── ExpectedData/
 *     ├── table_users.csv
 *     └── table_roles.csv
 * </pre>
 */
@Slf4j
@Component
public class TestExecutionEngine {

  private final PrepareDataService prepareDataService;
  private final ExpectedDataService expectedDataService;
  private final AssertionService assertionService;
  private final TestCaseLoader testCaseLoader;

  public TestExecutionEngine(
      PrepareDataService prepareDataService,
      ExpectedDataService expectedDataService,
      AssertionService assertionService,
      TestCaseLoader testCaseLoader) {
    this.prepareDataService = prepareDataService;
    this.expectedDataService = expectedDataService;
    this.assertionService = assertionService;
    this.testCaseLoader = testCaseLoader;
  }

  /**
   * Execute a test case.
   *
   * @param testClass the test class (for test data path resolution)
   * @param testBean the test bean instance (containing the method under test)
   * @param serviceName the service name (e.g., "UserService")
   * @param methodName the method name (e.g., "createUser")
   * @param caseId the case ID (e.g., "case01")
   * @param prepareData the prepared test data
   */
  public void runTest(
      Class<?> testClass,
      Object testBean,
      String serviceName,
      String methodName,
      String caseId,
      PrepareData prepareData) {
    TestCaseConfig config = prepareData.getConfig();
    if (config == null) {
      throw new TestifyException("Test case configuration not found for case: " + caseId);
    }

    log.info("Running test case: {}.{}/{} ", serviceName, methodName, caseId);

    if (!config.isEnabled()) {
      log.info("Test case {} is disabled, skipping", caseId);
      return;
    }

    // Check for @TestifyConcurrency annotation on the method
    int threads = 1;
    int iterations = 1;
    long rampUp = 0;

    try {
      // We need to find the method first to check annotations.
      // Note: This logic duplicates finding the method, but we need it for the
      // annotation check before execution loop.
      int expectedArgCount = config.getArgs() != null ? config.getArgs().size() : 0;
      Method method = findMethod(testBean.getClass(), methodName, expectedArgCount);

      com.github.loadup.testify.core.annotation.TestifyConcurrency concurrency =
          method.getAnnotation(com.github.loadup.testify.core.annotation.TestifyConcurrency.class);

      if (concurrency != null) {
        threads = concurrency.threads();
        iterations = concurrency.iterations();
        rampUp = concurrency.rampUp();
        log.info(
            "Concurrency test enabled: threads={}, iterations={}, rampUp={}ms",
            threads,
            iterations,
            rampUp);
      }

      if (threads > 1 || iterations > 1) {
        runConcurrentTest(
            testClass,
            testBean,
            serviceName,
            methodName,
            caseId,
            prepareData,
            config,
            method,
            threads,
            iterations,
            rampUp);
      } else {
        runSingleTest(
            testClass, testBean, serviceName, methodName, caseId, prepareData, config, method);
      }

    } catch (Exception e) {
      log.error(
          "Test case {}.{}/{} failed during setup or execution",
          serviceName,
          methodName,
          caseId,
          e);
      throw new TestifyException(
          "Test case failed: " + serviceName + "." + methodName + "/" + caseId, e);
    }
  }

  private void runConcurrentTest(
      Class<?> testClass,
      Object testBean,
      String serviceName,
      String methodName,
      String caseId,
      PrepareData prepareData,
      TestCaseConfig config,
      Method method,
      int threads,
      int iterations,
      long rampUp) {
    java.util.concurrent.ExecutorService executor =
        java.util.concurrent.Executors.newFixedThreadPool(threads);
    List<java.util.concurrent.CompletableFuture<Void>> futures = new ArrayList<>();
    // Use a concurrent list to store latencies
    List<Long> latencies = new java.util.concurrent.CopyOnWriteArrayList<>();
    java.util.concurrent.atomic.AtomicInteger successCount =
        new java.util.concurrent.atomic.AtomicInteger(0);
    java.util.concurrent.atomic.AtomicInteger failureCount =
        new java.util.concurrent.atomic.AtomicInteger(0);

    long startTime = System.currentTimeMillis();

    for (int i = 0; i < threads; i++) {
      final int threadNum = i;
      futures.add(
          java.util.concurrent.CompletableFuture.runAsync(
              () -> {
                try {
                  if (rampUp > 0 && threadNum > 0) {
                    long sleepTime = (rampUp / threads) * threadNum;
                    Thread.sleep(sleepTime);
                  }

                  for (int j = 0; j < iterations; j++) {
                    long loopStart = System.currentTimeMillis();
                    boolean success = false;
                    try {
                      SharedVariablePool.clear();
                      if (prepareData.getCapturedVariables() != null) {
                        prepareData.getCapturedVariables().forEach(SharedVariablePool::put);
                      }

                      Object[] args = testCaseLoader.convertArgs(prepareData, method, testClass);
                      Object result = invokeMethod(testBean, method, args);

                      assertResponse(result, config);
                      success = true;
                    } catch (Exception e) {
                      log.error("Iteration failed in thread {}", threadNum, e);
                    } finally {
                      long duration = System.currentTimeMillis() - loopStart;
                      latencies.add(duration);
                      if (success) {
                        successCount.incrementAndGet();
                      } else {
                        failureCount.incrementAndGet();
                      }
                      SharedVariablePool.cleanup();
                    }
                  }
                } catch (Exception e) {
                  log.error("Concurrent execution failed in thread {}", threadNum, e);
                  throw new RuntimeException(e);
                }
              },
              executor));
    }

    try {
      java.util.concurrent.CompletableFuture.allOf(
              futures.toArray(new java.util.concurrent.CompletableFuture[0]))
          .join();
      long totalDuration = System.currentTimeMillis() - startTime;
      double tps = (successCount.get()) * 1000.0 / totalDuration;

      // Calculate stats
      long maxLatency = latencies.stream().mapToLong(v -> v).max().orElse(0);
      double avgLatency = latencies.stream().mapToLong(v -> v).average().orElse(0);

      log.info(
          "Concurrent test finished in {} ms. Requests: {}, Success: {}, Failed: {}. TPS: {:.2f}, MaxLatency: {}ms, AvgLatency: {:.2f}ms",
          totalDuration,
          (threads * iterations),
          successCount.get(),
          failureCount.get(),
          tps,
          maxLatency,
          avgLatency);

      // Assertions
      if (failureCount.get() > 0) {
        throw new TestifyException("Concurrent test failed with " + failureCount.get() + " errors");
      }

      if (config.getMaxDuration() != null && totalDuration > config.getMaxDuration()) {
        throw new TestifyException(
            "Performance check failed: Total duration "
                + totalDuration
                + "ms > max "
                + config.getMaxDuration()
                + "ms");
      }

      if (config.getMinTps() != null && tps < config.getMinTps()) {
        throw new TestifyException(
            "Performance check failed: TPS "
                + String.format("%.2f", tps)
                + " < min "
                + config.getMinTps());
      }

    } catch (Exception e) {
      throw new TestifyException("Concurrent test failed", e);
    } finally {
      executor.shutdown();
    }
  }

  private void runSingleTest(
      Class<?> testClass,
      Object testBean,
      String serviceName,
      String methodName,
      String caseId,
      PrepareData prepareData,
      TestCaseConfig config,
      Method method) {
    try {
      // Restore captured variables from PrepareData to SharedVariablePool.
      SharedVariablePool.clear();
      if (prepareData.getCapturedVariables() != null) {
        prepareData.getCapturedVariables().forEach(SharedVariablePool::put);
      }

      // 1. Prepare database data (using new path structure)
      prepareDataService.prepareData(testClass, serviceName, methodName, caseId);

      // 2. Invoke method
      Object[] args = testCaseLoader.convertArgs(prepareData, method, testClass);
      Object result = invokeMethod(testBean, method, args);

      // 3. Assert response
      assertResponse(result, config);

      // 4. Assert database state (using new path structure)
      assertDatabaseState(testClass, serviceName, methodName, caseId, config);

      log.info("Test case {}.{}/{} passed", serviceName, methodName, caseId);

    } catch (Exception e) {
      log.error("Test case {}.{}/{} failed", serviceName, methodName, caseId, e);
      throw new TestifyException(
          "Test case failed: " + serviceName + "." + methodName + "/" + caseId, e);
    } finally {
      // Cleanup
      try {
        if (config.getCleanup() != null && !config.getCleanup().isEmpty()) {
          for (String action : config.getCleanup()) {
            log.debug("Executing cleanup action: {}", action);
          }
        }
      } finally {
        SharedVariablePool.cleanup();
      }
    }
  }

  /**
   * Find a method by name in the test class with matching parameter count. If multiple methods have
   * the same name, prefer the one with matching parameter count.
   */
  private Method findMethod(Class<?> testClass, String methodName, int expectedArgCount) {
    List<Method> matchingMethods = new ArrayList<>();

    for (Method method : testClass.getMethods()) {
      if (method.getName().equals(methodName)) {
        matchingMethods.add(method);
      }
    }

    if (matchingMethods.isEmpty()) {
      throw new TestifyException("Method not found: " + methodName);
    }

    // If only one method, return it
    if (matchingMethods.size() == 1) {
      return matchingMethods.get(0);
    }

    // Try to find method with exact parameter count match
    for (Method method : matchingMethods) {
      if (method.getParameterCount() == expectedArgCount) {
        return method;
      }
    }

    // Fall back to first method and log a warning
    log.warn(
        "Multiple methods found with name '{}', using first match. Consider providing unique method names.",
        methodName);
    return matchingMethods.get(0);
  }

  /** Invoke the target method with the given arguments. */
  private Object invokeMethod(Object instance, Method method, Object[] args) {
    try {
      return method.invoke(instance, args);
    } catch (IllegalAccessException | InvocationTargetException e) {
      Throwable cause = e.getCause() != null ? e.getCause() : e;
      throw new TestifyException("Failed to invoke method: " + method.getName(), cause);
    }
  }

  /** Assert the response matches the expected result. */
  private void assertResponse(Object actual, TestCaseConfig config) {
    Object expected = config.getResult();
    if (expected != null) {
      log.debug("Asserting response: expected={}, actual={}", expected, actual);
      Set<String> ignoreFields =
          config.getIgnoreFields() != null
              ? new HashSet<>(config.getIgnoreFields())
              : Collections.emptySet();
      assertionService.assertResponse(actual, expected, ignoreFields);
    }
  }

  /** Assert the database state matches the expected data. */
  private void assertDatabaseState(
      Class<?> testClass,
      String serviceName,
      String methodName,
      String caseId,
      TestCaseConfig config) {
    Map<String, List<Map<String, String>>> expectedData =
        expectedDataService.loadExpectedData(testClass, serviceName, methodName, caseId);

    if (expectedData.isEmpty()) {
      log.debug(
          "No expected database data found for case: {}.{}/{}", serviceName, methodName, caseId);
      return;
    }

    Set<String> ignoreFields =
        config.getIgnoreFields() != null
            ? new HashSet<>(config.getIgnoreFields())
            : Collections.emptySet();

    for (Map.Entry<String, List<Map<String, String>>> entry : expectedData.entrySet()) {
      String tableName = entry.getKey();
      List<Map<String, String>> expectedRows = entry.getValue();

      List<Map<String, Object>> actualRows =
          expectedDataService.queryActualData(tableName, expectedRows);

      int fieldsPerRow = 0;
      if (!expectedRows.isEmpty() && expectedRows.get(0) != null) {
        fieldsPerRow = expectedRows.get(0).size() - ignoreFields.size();
      }
      log.info(
          "Asserting database table '{}': expected {} rows, actual {} rows, comparing {} fields per row",
          tableName,
          expectedRows.size(),
          actualRows.size(),
          fieldsPerRow);

      assertionService.assertDatabaseRows(actualRows, expectedRows, ignoreFields);
      log.info("Database assertion passed for table '{}'", tableName);
    }
  }
}
