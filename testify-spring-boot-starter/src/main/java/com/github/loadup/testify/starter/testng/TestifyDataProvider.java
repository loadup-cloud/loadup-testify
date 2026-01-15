package com.github.loadup.testify.starter.testng;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.loadup.testify.core.model.TestContext;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * TestNG Data Provider for Testify framework.
 * Automatically converts YAML input data to test method parameters.
 * 
 * Usage:
 * 
 * @Test(dataProvider = "testifyData", dataProviderClass =
 *                    TestifyDataProvider.class)
 *                    public void testMethod(String param1, int param2) { ... }
 */
public class TestifyDataProvider {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Data provider that extracts input parameters from loaded TestContext.
     */
    @DataProvider(name = "testifyData")
    public static Iterator<Object[]> provideTestData(ITestContext context, Method method) {
        List<Object[]> data = new ArrayList<>();

        try {
            // Get TestContext loaded by TestifyListener
            // Note: In real implementation, this would need to be retrieved from test
            // result
            // For now, return empty data - full implementation requires TestNG integration
            // patterns

            // Placeholder: Convert YAML input to method parameters
            // This requires access to the loaded TestContext from listener

        } catch (Exception e) {
            throw new RuntimeException("Failed to provide test data", e);
        }

        return data.iterator();
    }

    /**
     * Convert JSON input array to method parameters.
     * 
     * @param inputNode      JsonNode array from YAML
     * @param parameterTypes Method parameter types
     * @return Object array for test method
     */
    private static Object[] convertInput(JsonNode inputNode, Class<?>[] parameterTypes) throws Exception {
        if (inputNode == null || !inputNode.isArray()) {
            return new Object[0];
        }

        int paramCount = parameterTypes.length;
        Object[] params = new Object[paramCount];

        for (int i = 0; i < paramCount && i < inputNode.size(); i++) {
            JsonNode valueNode = inputNode.get(i);
            Class<?> targetType = parameterTypes[i];

            // Convert using Jackson
            params[i] = objectMapper.convertValue(valueNode, targetType);
        }

        return params;
    }
}
