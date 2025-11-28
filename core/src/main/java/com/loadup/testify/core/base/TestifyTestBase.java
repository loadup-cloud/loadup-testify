package com.loadup.testify.core.base;

import com.loadup.testify.core.annotation.TestBean;
import com.loadup.testify.core.engine.TestExecutionEngine;
import com.loadup.testify.core.loader.TestCaseLoader;
import com.loadup.testify.core.model.PrepareData;
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

    /**
     * DataProvider that automatically loads test cases from the test data directory.
     * Test cases are organized in folders named by caseId, each containing:
     * - test_config.yaml: Test configuration and expected results
     * - PrepareData/: CSV files for database setup
     * - ExpectedData/: CSV files for database assertions
     * 
     * Test cases are filtered based on the test method name:
     * - If the config specifies a method, only cases matching that method are returned
     * - If no method is specified, cases matching the normalized test method name are returned
     *
     * @param method the test method
     * @return an Iterator of Object arrays containing [caseId, PrepareData]
     */
    @DataProvider(name = "TestifyProvider")
    public Iterator<Object[]> testifyProvider(Method method) {
        List<PrepareData> testCases = testCaseLoader.loadTestCases(getClass());
        String normalizedMethodName = normalizeTestMethodName(method.getName());
        
        return testCases.stream()
                .filter(PrepareData::isLoaded)
                .filter(pd -> pd.getConfig() == null || pd.getConfig().isEnabled())
                .filter(pd -> matchesTestMethod(pd, normalizedMethodName))
                .map(pd -> new Object[]{pd.getCaseId(), pd})
                .iterator();
    }

    /**
     * Check if a PrepareData matches the test method.
     */
    private boolean matchesTestMethod(PrepareData pd, String normalizedMethodName) {
        if (pd.getConfig() == null) {
            return true;
        }
        String configMethod = pd.getConfig().getMethod();
        if (configMethod != null) {
            // If config specifies a method, it must match
            return configMethod.equals(normalizedMethodName);
        }
        // If no method specified, treat as matching any test
        return true;
    }

    /**
     * Normalize a test method name by removing "test" prefix.
     */
    private String normalizeTestMethodName(String methodName) {
        if (methodName.toLowerCase().startsWith("test") && methodName.length() > 4) {
            String withoutPrefix = methodName.substring(4);
            return Character.toLowerCase(withoutPrefix.charAt(0)) + withoutPrefix.substring(1);
        }
        return methodName;
    }

    /**
     * Run a test case.
     * This method should be called from within the test method.
     *
     * @param caseId      the case ID
     * @param prepareData the prepared test data
     */
    protected void runTest(String caseId, PrepareData prepareData) {
        // Get the calling method name
        String methodName = getCallingMethodName();
        // Normalize method name - remove "test" prefix if present
        String targetMethodName = normalizeMethodName(methodName, prepareData);
        testExecutionEngine.runTest(getTestBean(), caseId, prepareData, targetMethodName);
    }

    /**
     * Run a test case with a specific method name.
     *
     * @param caseId      the case ID
     * @param prepareData the prepared test data
     * @param methodName  the target method name to invoke
     */
    protected void runTest(String caseId, PrepareData prepareData, String methodName) {
        testExecutionEngine.runTest(getTestBean(), caseId, prepareData, methodName);
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
     * Normalize the method name by removing "test" prefix if present.
     * Also checks if method is specified in config.
     * 
     * @param methodName the original method name
     * @param prepareData the prepare data containing config
     * @return the normalized method name
     */
    private String normalizeMethodName(String methodName, PrepareData prepareData) {
        // If config specifies a method, use that
        if (prepareData.getConfig() != null && prepareData.getConfig().getMethod() != null) {
            return prepareData.getConfig().getMethod();
        }
        
        // Remove "test" prefix if present (case-insensitive)
        if (methodName.toLowerCase().startsWith("test") && methodName.length() > 4) {
            // Convert first char after "test" to lowercase
            String withoutPrefix = methodName.substring(4);
            return Character.toLowerCase(withoutPrefix.charAt(0)) + withoutPrefix.substring(1);
        }
        
        return methodName;
    }

    /**
     * Get the name of the calling method (the test method).
     */
    private String getCallingMethodName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 2; i < stackTrace.length; i++) {
            String className = stackTrace[i].getClassName();
            if (!className.equals(TestifyTestBase.class.getName()) &&
                !className.startsWith("java.") &&
                !className.startsWith("org.testng.")) {
                return stackTrace[i].getMethodName();
            }
        }
        return "unknown";
    }
}
