package com.github.loadup.testify.core.base;

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

import com.github.loadup.testify.core.annotation.TestBean;
import com.github.loadup.testify.core.engine.TestExecutionEngine;
import com.github.loadup.testify.core.loader.TestCaseLoader;
import com.github.loadup.testify.core.model.PrepareData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

/**
 * Base class for data-driven tests using Testify.
 * Extends AbstractTestNGSpringContextTests for Spring Boot integration with TestNG.
 * 
 * <p>Directory structure convention:</p>
 * <pre>
 * |- UserServiceTest.java                 (test class)
 * ├── UserService.createUser/             (method directory: ServiceName.methodName)
 * │   ├── case01/                         (case directory)
 * │   │   ├── test_config.yaml            (test configuration)
 * │   │   ├── PrepareData/                (CSV files for database setup)
 * │   │   └── ExpectedData/               (CSV files for database assertions)
 * │   └── case02/
 * ├── UserService.createUserWithRole/
 * │   └── case01/
 * </pre>
 * 
 * <p>Use the {@link TestBean} annotation to mark the service/bean under test:</p>
 * <pre>
 * &#64;TestBean
 * &#64;Autowired
 * private UserService userService;
 * </pre>
 */
public abstract class TestifyTestBase extends AbstractTestNGSpringContextTests {

    @Autowired
    protected TestExecutionEngine testExecutionEngine;

    @Autowired
    protected TestCaseLoader testCaseLoader;

    private Object cachedTestBean;
    private String cachedServiceName;

    /**
     * DataProvider that automatically loads test cases from the test data directory.
     * Test cases are organized by method: {ServiceName}.{methodName}/{caseId}/
     * 
     * <p>The test method name is used to locate the corresponding method directory:</p>
     * <ul>
     *   <li>testCreateUser → looks for {ServiceName}.createUser/</li>
     *   <li>createUser → looks for {ServiceName}.createUser/</li>
     * </ul>
     *
     * @param method the test method
     * @return an Iterator of Object arrays containing [caseId, PrepareData]
     */
    @DataProvider(name = "TestifyProvider")
    public Iterator<Object[]> testifyProvider(Method method) {
        String normalizedMethodName = stripTestPrefix(method.getName());
        String serviceName = getServiceName();
        
        List<PrepareData> testCases = testCaseLoader.loadTestCasesForMethod(getClass(), serviceName, normalizedMethodName);
        
        return testCases.stream()
                .filter(PrepareData::isLoaded)
                .filter(pd -> pd.getConfig() == null || pd.getConfig().isEnabled())
                .map(pd -> new Object[]{pd.getCaseId(), pd})
                .iterator();
    }

    /**
     * Run a test case.
     * This method should be called from within the test method.
     *
     * @param caseId      the case ID
     * @param prepareData the prepared test data
     */
    protected void runTest(String caseId, PrepareData prepareData) {
        String methodName = prepareData.getMethodName();
        String serviceName = prepareData.getServiceName();
        
        // Pass all information needed for path resolution and method invocation
        testExecutionEngine.runTest(getClass(), getTestBean(), serviceName, methodName, caseId, prepareData);
    }

    /**
     * Run a test case with a specific method name.
     *
     * @param caseId      the case ID
     * @param prepareData the prepared test data
     * @param methodName  the target method name to invoke
     */
    protected void runTest(String caseId, PrepareData prepareData, String methodName) {
        String serviceName = prepareData.getServiceName();
        testExecutionEngine.runTest(getClass(), getTestBean(), serviceName, methodName, caseId, prepareData);
    }

    /**
     * Get the service name from the @TestBean annotated field.
     * The service name is derived from the field type's simple name.
     *
     * @return the service name (e.g., "UserService")
     */
    protected String getServiceName() {
        if (cachedServiceName != null) {
            return cachedServiceName;
        }
        
        // Look for field annotated with @TestBean
        for (Field field : getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(TestBean.class)) {
                cachedServiceName = field.getType().getSimpleName();
                return cachedServiceName;
            }
        }
        
        // Also check parent classes
        Class<?> superClass = getClass().getSuperclass();
        while (superClass != null && superClass != TestifyTestBase.class) {
            for (Field field : superClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(TestBean.class)) {
                    cachedServiceName = field.getType().getSimpleName();
                    return cachedServiceName;
                }
            }
            superClass = superClass.getSuperclass();
        }
        
        // Fall back to test class name without "Test" suffix
        String className = getClass().getSimpleName();
        if (className.endsWith("Test")) {
            cachedServiceName = className.substring(0, className.length() - 4);
        } else {
            cachedServiceName = className;
        }
        return cachedServiceName;
    }

    /**
     * Get the test bean instance.
     * First checks for a field annotated with @TestBean, then falls back to
     * overridden getTestBean() method.
     *
     * @return the test bean instance
     */
    protected Object getTestBean() {
        if (cachedTestBean != null) {
            return cachedTestBean;
        }
        
        // Look for field annotated with @TestBean
        for (Field field : getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(TestBean.class)) {
                try {
                    field.setAccessible(true);
                    cachedTestBean = field.get(this);
                    return cachedTestBean;
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to access @TestBean field: " + field.getName(), e);
                }
            }
        }
        
        // Also check parent classes
        Class<?> superClass = getClass().getSuperclass();
        while (superClass != null && superClass != TestifyTestBase.class) {
            for (Field field : superClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(TestBean.class)) {
                    try {
                        field.setAccessible(true);
                        cachedTestBean = field.get(this);
                        return cachedTestBean;
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Failed to access @TestBean field: " + field.getName(), e);
                    }
                }
            }
            superClass = superClass.getSuperclass();
        }
        
        // No @TestBean annotation found, return this (the test class itself)
        cachedTestBean = this;
        return cachedTestBean;
    }

    /**
     * Hook for setup actions before each test method.
     */
    @BeforeMethod
    protected void beforeTestMethod() {
        // Override in subclass if needed
    }

    /**
     * Hook for cleanup actions after each test method.
     */
    @AfterMethod
    protected void afterTestMethod() {
        // Override in subclass if needed
    }

    /**
     * Strip "test" prefix from method name if present.
     * 
     * @param methodName the original method name
     * @return the method name with "test" prefix removed
     */
    private String stripTestPrefix(String methodName) {
        if (methodName != null && 
            methodName.length() > 4 && 
            methodName.toLowerCase().startsWith("test")) {
            String withoutPrefix = methodName.substring(4);
            if (!withoutPrefix.isEmpty()) {
                return Character.toLowerCase(withoutPrefix.charAt(0)) + 
                       (withoutPrefix.length() > 1 ? withoutPrefix.substring(1) : "");
            }
        }
        return methodName;
    }
}
