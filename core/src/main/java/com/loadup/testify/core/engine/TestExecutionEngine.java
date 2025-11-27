package com.loadup.testify.core.engine;

import com.loadup.testify.assertions.service.AssertionService;
import com.loadup.testify.common.exception.TestifyException;
import com.loadup.testify.common.variable.SharedVariablePool;
import com.loadup.testify.core.loader.TestCaseLoader;
import com.loadup.testify.core.model.PrepareData;
import com.loadup.testify.core.model.TestCaseConfig;
import com.loadup.testify.data.service.ExpectedDataService;
import com.loadup.testify.data.service.PrepareDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Core test execution engine that orchestrates the data-driven test lifecycle.
 */
@Slf4j
@Component
public class TestExecutionEngine {

    private final PrepareDataService prepareDataService;
    private final ExpectedDataService expectedDataService;
    private final AssertionService assertionService;
    private final TestCaseLoader testCaseLoader;

    public TestExecutionEngine(PrepareDataService prepareDataService,
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
     * @param testInstance the test instance (containing the method under test)
     * @param caseId       the case ID
     * @param prepareData  the prepared test data
     * @param methodName   the name of the method to invoke
     */
    public void runTest(Object testInstance, String caseId, PrepareData prepareData, String methodName) {
        log.info("Running test case: {} for method: {}", caseId, methodName);

        TestCaseConfig config = prepareData.getConfig();
        if (config == null) {
            throw new TestifyException("Test case configuration not found for case: " + caseId);
        }

        if (!config.isEnabled()) {
            log.info("Test case {} is disabled, skipping", caseId);
            return;
        }

        try {
            // 1. Clear shared variable pool for this test
            SharedVariablePool.clear();

            // 2. Prepare database data
            prepareDataService.prepareData(testInstance.getClass(), caseId);

            // 3. Find and invoke the test method
            Method method = findMethod(testInstance.getClass(), methodName);
            Object[] args = testCaseLoader.convertArgs(config, method);
            Object result = invokeMethod(testInstance, method, args);

            // 4. Assert response
            assertResponse(result, config);

            // 5. Assert database state
            assertDatabaseState(testInstance.getClass(), caseId, config);

            log.info("Test case {} passed", caseId);

        } catch (Exception e) {
            log.error("Test case {} failed", caseId, e);
            throw new TestifyException("Test case failed: " + caseId, e);
        } finally {
            // Cleanup
            try {
                if (config.getCleanup() != null && !config.getCleanup().isEmpty()) {
                    // Execute cleanup actions
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
     * Find a method by name in the test class.
     */
    private Method findMethod(Class<?> testClass, String methodName) {
        for (Method method : testClass.getMethods()) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        throw new TestifyException("Method not found: " + methodName);
    }

    /**
     * Invoke the target method with the given arguments.
     */
    private Object invokeMethod(Object instance, Method method, Object[] args) {
        try {
            return method.invoke(instance, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            Throwable cause = e.getCause() != null ? e.getCause() : e;
            throw new TestifyException("Failed to invoke method: " + method.getName(), cause);
        }
    }

    /**
     * Assert the response matches the expected result.
     */
    private void assertResponse(Object actual, TestCaseConfig config) {
        Object expected = config.getResult();
        if (expected != null) {
            log.debug("Asserting response: expected={}, actual={}", expected, actual);
            assertionService.assertResponse(actual, expected);
        }
    }

    /**
     * Assert the database state matches the expected data.
     */
    private void assertDatabaseState(Class<?> testClass, String caseId, TestCaseConfig config) {
        Map<String, List<Map<String, String>>> expectedData =
                expectedDataService.loadExpectedData(testClass, caseId);

        if (expectedData.isEmpty()) {
            log.debug("No expected database data found for case: {}", caseId);
            return;
        }

        Set<String> ignoreFields = config.getIgnoreFields() != null
                ? new HashSet<>(config.getIgnoreFields())
                : Collections.emptySet();

        for (Map.Entry<String, List<Map<String, String>>> entry : expectedData.entrySet()) {
            String tableName = entry.getKey();
            List<Map<String, String>> expectedRows = entry.getValue();

            List<Map<String, Object>> actualRows =
                    expectedDataService.queryActualData(tableName, expectedRows);

            log.debug("Asserting database table {}: expected {} rows, actual {} rows",
                    tableName, expectedRows.size(), actualRows.size());

            assertionService.assertDatabaseRows(actualRows, expectedRows, ignoreFields);
        }
    }
}
