package com.loadup.testify.core.provider;

import com.loadup.testify.core.context.TestContext;
import com.loadup.testify.core.model.TestCase;
import com.loadup.testify.core.model.TestSuite;
import com.loadup.testify.core.parser.YamlTestConfigParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Custom TestNG DataProvider for driving test cases from YAML configuration
 */
public class TestifyDataProvider {

    private static final Logger log = LoggerFactory.getLogger(TestifyDataProvider.class);

    private static final YamlTestConfigParser parser = new YamlTestConfigParser();

    /**
     * Main data provider method
     * Usage: @Test(dataProvider = "testifyProvider", dataProviderClass = TestifyDataProvider.class)
     */
    @DataProvider(name = "testifyProvider", parallel = false)
    public static Iterator<Object[]> testifyProvider(Method method) {
        // Get test configuration file path from method annotation or convention
        String configPath = getConfigPath(method);

        log.info("Loading test configuration for method: {} from: {}", method.getName(), configPath);

        TestSuite testSuite = parser.parseFromClasspath(configPath);

        List<Object[]> testData = new ArrayList<>();

        if (testSuite.getTestCases() != null) {
            for (TestCase testCase : testSuite.getTestCases()) {
                if (testCase.isEnabled()) {
                    // Each test case becomes a row in the data provider
                    testData.add(new Object[]{testSuite, testCase});
                }
            }
        }

        log.info("Loaded {} test cases", testData.size());

        return testData.iterator();
    }

    /**
     * Get configuration file path
     * Convention: test-configs/{ClassName}/{methodName}.yaml
     */
    private static String getConfigPath(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName();
        return String.format("test-configs/%s/%s.yaml", className, methodName);
    }

    /**
     * Custom data provider with explicit configuration path
     */
    @DataProvider(name = "testifyProviderWithPath")
    public static Iterator<Object[]> testifyProviderWithPath(Method method) {
        // Look for @TestConfig annotation to get custom path
        TestConfig annotation = method.getAnnotation(TestConfig.class);
        if (annotation == null) {
            throw new IllegalStateException("@TestConfig annotation is required when using testifyProviderWithPath");
        }

        String configPath = annotation.value();
        log.info("Loading test configuration from: {}", configPath);

        TestSuite testSuite = parser.parseFromClasspath(configPath);

        List<Object[]> testData = new ArrayList<>();

        if (testSuite.getTestCases() != null) {
            for (TestCase testCase : testSuite.getTestCases()) {
                if (testCase.isEnabled()) {
                    testData.add(new Object[]{testSuite, testCase});
                }
            }
        }

        return testData.iterator();
    }
}

