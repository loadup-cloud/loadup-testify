package com.github.loadup.testify.starter.testng;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.loadup.testify.core.loader.YamlLoader;
import com.github.loadup.testify.core.model.TestContext;
import com.github.loadup.testify.data.engine.VariableContext;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * TestNG listener for Testify framework.
 * Automatically loads YAML test cases before test execution and cleans up
 * after.
 * 
 * Usage: Add @Listeners(TestifyListener.class) to your test class
 * or configure in testng.xml
 */
public class TestifyListener implements ITestListener {

    private static final String TESTCASES_DIR = "testcases";
    private final YamlLoader yamlLoader = new YamlLoader();

    @Override
    public void onTestStart(ITestResult result) {
        try {
            // Load YAML test case
            TestContext testContext = loadTestCase(result);

            // Store in test result for data provider access
            if (testContext != null) {
                result.setAttribute("testContext", testContext);
            }
        } catch (Exception e) {
            result.setThrowable(new RuntimeException("Failed to load test case YAML", e));
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        cleanup();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        cleanup();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        cleanup();
    }

    /**
     * Load YAML test case for the current test method.
     * Convention: src/test/resources/testcases/[ClassName]/[methodName].yaml
     */
    private TestContext loadTestCase(ITestResult result) {
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        Class<?> testClass = result.getTestClass().getRealClass();

        // Build YAML file path following convention
        String className = testClass.getSimpleName();
        String methodName = method.getName();

        Path yamlPath = Paths.get(TESTCASES_DIR, className, methodName + ".yaml");

        try {
            // Load YAML using YamlLoader
            TestContext context = yamlLoader.load(yamlPath.toString());

            // Variables are already resolved by YamlLoader and stored in VariableContext
            // No additional processing needed here

            return context;
        } catch (Exception e) {
            // YAML file not found or loading failed - this is OK, test may not use YAML
            return null;
        }
    }

    /**
     * Cleanup after test execution.
     * Clears variable context and mock registry.
     */
    private void cleanup() {
        // Clear variable context for this thread
        VariableContext.clear();

        // Clear mock registry (if mocks were used)
        try {
            Class<?> mockRegistryClass = Class.forName("com.github.loadup.testify.mock.registry.MockRegistry");
            mockRegistryClass.getMethod("clear").invoke(null);
        } catch (Exception e) {
            // MockRegistry not available or already cleared - ignore
        }
    }
}
