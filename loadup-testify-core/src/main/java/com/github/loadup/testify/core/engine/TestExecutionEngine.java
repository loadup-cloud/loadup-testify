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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Core test execution engine that orchestrates the data-driven test lifecycle.
 * 
 * <p>Directory structure convention:</p>
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
     * @param testClass    the test class (for test data path resolution)
     * @param testBean     the test bean instance (containing the method under test)
     * @param serviceName  the service name (e.g., "UserService")
     * @param methodName   the method name (e.g., "createUser")
     * @param caseId       the case ID (e.g., "case01")
     * @param prepareData  the prepared test data
     */
    public void runTest(Class<?> testClass, Object testBean, String serviceName, String methodName, 
                        String caseId, PrepareData prepareData) {
        TestCaseConfig config = prepareData.getConfig();
        if (config == null) {
            throw new TestifyException("Test case configuration not found for case: " + caseId);
        }

        log.info("Running test case: {}.{}/{} ", serviceName, methodName, caseId);

        if (!config.isEnabled()) {
            log.info("Test case {} is disabled, skipping", caseId);
            return;
        }

        try {
            // Restore captured variables from PrepareData to SharedVariablePool.
            SharedVariablePool.clear();
            if (prepareData.getCapturedVariables() != null) {
                prepareData.getCapturedVariables().forEach(SharedVariablePool::put);
            }

            // 1. Prepare database data (using new path structure)
            prepareDataService.prepareData(testClass, serviceName, methodName, caseId);

            // 2. Find and invoke the test method with matching parameter count
            int expectedArgCount = config.getArgs() != null ? config.getArgs().size() : 0;
            Method method = findMethod(testBean.getClass(), methodName, expectedArgCount);
            Object[] args = testCaseLoader.convertArgs(config, method);
            Object result = invokeMethod(testBean, method, args);

            // 3. Assert response
            assertResponse(result, config);

            // 4. Assert database state (using new path structure)
            assertDatabaseState(testClass, serviceName, methodName, caseId, config);

            log.info("Test case {}.{}/{} passed", serviceName, methodName, caseId);

        } catch (Exception e) {
            log.error("Test case {}.{}/{} failed", serviceName, methodName, caseId, e);
            throw new TestifyException("Test case failed: " + serviceName + "." + methodName + "/" + caseId, e);
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
            Set<String> ignoreFields = config.getIgnoreFields() != null
                    ? new HashSet<>(config.getIgnoreFields())
                    : Collections.emptySet();
            assertionService.assertResponse(actual, expected, ignoreFields);
        }
    }

    /**
     * Assert the database state matches the expected data.
     */
    private void assertDatabaseState(Class<?> testClass, String serviceName, String methodName, 
                                     String caseId, TestCaseConfig config) {
        Map<String, List<Map<String, String>>> expectedData =
                expectedDataService.loadExpectedData(testClass, serviceName, methodName, caseId);

        if (expectedData.isEmpty()) {
            log.debug("No expected database data found for case: {}.{}/{}", serviceName, methodName, caseId);
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

            int fieldsPerRow = 0;
            if (!expectedRows.isEmpty() && expectedRows.get(0) != null) {
                fieldsPerRow = expectedRows.get(0).size() - ignoreFields.size();
            }
            log.info("Asserting database table '{}': expected {} rows, actual {} rows, comparing {} fields per row",
                    tableName, expectedRows.size(), actualRows.size(), fieldsPerRow);

            assertionService.assertDatabaseRows(actualRows, expectedRows, ignoreFields);
            log.info("Database assertion passed for table '{}'", tableName);
        }
    }
}
