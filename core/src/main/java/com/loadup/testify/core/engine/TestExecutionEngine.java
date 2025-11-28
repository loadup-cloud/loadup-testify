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
     * @param methodName   the name of the method to invoke (fallback if not specified in config)
     */
    public void runTest(Object testInstance, String caseId, PrepareData prepareData, String methodName) {
        TestCaseConfig config = prepareData.getConfig();
        if (config == null) {
            throw new TestifyException("Test case configuration not found for case: " + caseId);
        }

        // Use method from config if specified, otherwise use provided methodName
        String targetMethod = config.getMethod() != null ? config.getMethod() : methodName;
        log.info("Running test case: {} for method: {}", caseId, targetMethod);

        if (!config.isEnabled()) {
            log.info("Test case {} is disabled, skipping", caseId);
            return;
        }

        try {
            // Note: Do NOT clear SharedVariablePool here as variables were captured during test case loading

            // 1. Prepare database data
            prepareDataService.prepareData(testInstance.getClass(), caseId);

            // 2. Find and invoke the test method with matching parameter count
            int expectedArgCount = config.getArgs() != null ? config.getArgs().size() : 0;
            Method method = findMethod(testInstance.getClass(), targetMethod, expectedArgCount);
            Object[] args = testCaseLoader.convertArgs(config, method);
            Object result = invokeMethod(testInstance, method, args);

            // 3. Assert response
            assertResponse(result, config);

            // 4. Assert database state
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
     * Find a method by name in the test class with matching parameter count.
     * If multiple methods have the same name, prefer the one with matching parameter count.
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
        log.warn("Multiple methods found with name '{}', using first match. Consider providing unique method names.", methodName);
        return matchingMethods.get(0);
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
