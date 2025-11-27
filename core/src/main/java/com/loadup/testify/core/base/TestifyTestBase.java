package com.loadup.testify.core.base;

import com.loadup.testify.core.engine.TestExecutionEngine;
import com.loadup.testify.core.loader.TestCaseLoader;
import com.loadup.testify.core.model.PrepareData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

/**
 * Base class for data-driven tests using Testify.
 * Extends AbstractTestNGSpringContextTests for Spring Boot integration with TestNG.
 */
public abstract class TestifyTestBase extends AbstractTestNGSpringContextTests {

    @Autowired
    protected TestExecutionEngine testExecutionEngine;

    @Autowired
    protected TestCaseLoader testCaseLoader;

    /**
     * DataProvider that automatically loads test cases from the test data directory.
     * Test cases are organized in folders named by caseId, each containing:
     * - test_config.yaml: Test configuration and expected results
     * - PrepareData/: CSV files for database setup
     * - ExpectedData/: CSV files for database assertions
     *
     * @param method the test method
     * @return an Iterator of Object arrays containing [caseId, PrepareData]
     */
    @DataProvider(name = "TestifyProvider")
    public Iterator<Object[]> testifyProvider(Method method) {
        List<PrepareData> testCases = testCaseLoader.loadTestCases(getClass());
        
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
        // Get the calling method name
        String methodName = getCallingMethodName();
        testExecutionEngine.runTest(getTestBean(), caseId, prepareData, methodName);
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
     * Override this method to return the actual service/bean under test.
     *
     * @return the test bean instance
     */
    protected abstract Object getTestBean();

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
