package com.loadup.testify.core.loader;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loadup.testify.common.exception.DataLoadingException;
import com.loadup.testify.common.util.JsonYamlUtils;
import com.loadup.testify.common.util.PathUtils;
import com.loadup.testify.common.variable.SharedVariablePool;
import com.loadup.testify.common.variable.VariableResolver;
import com.loadup.testify.core.model.PrepareData;
import com.loadup.testify.core.model.TestCaseConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Loader for test case configurations and data.
 * 
 * <p>Supports the following directory structure:</p>
 * <pre>
 * |- UserServiceTest.java
 * ├── UserService.createUser/
 * │   ├── case01/
 * │   │   ├── test_config.yaml
 * │   │   ├── PrepareData/
 * │   │   │   ├── table_users.csv
 * │   │   │   └── table_roles.csv
 * │   │   └── ExpectedData/
 * │   │       ├── table_users.csv
 * │   │       └── table_roles.csv
 * │   └── case02/
 * ├── UserService.createUserWithRole/
 * │   └── case01/
 * </pre>
 */
@Slf4j
@Component
public class TestCaseLoader {

    private final VariableResolver variableResolver;
    private final ObjectMapper yamlMapper;
    private final ObjectMapper jsonMapper;

    public TestCaseLoader(VariableResolver variableResolver) {
        this.variableResolver = variableResolver;
        this.yamlMapper = JsonYamlUtils.getYamlMapper();
        this.jsonMapper = JsonYamlUtils.getJsonMapper();
    }

    /**
     * Load all test case configurations for a test class.
     * Scans all method directories (format: ServiceName.methodName) and their case subdirectories.
     *
     * @param testClass the test class
     * @return a list of PrepareData objects, one for each test case
     */
    public List<PrepareData> loadTestCases(Class<?> testClass) {
        List<PrepareData> testCases = new ArrayList<>();
        
        // Get all method directories
        List<String> methodDirs = PathUtils.listMethodDirectories(testClass);
        
        for (String methodDirName : methodDirs) {
            // Parse service name and method name from directory name
            int dotIndex = methodDirName.lastIndexOf('.');
            if (dotIndex <= 0) {
                continue;
            }
            String serviceName = methodDirName.substring(0, dotIndex);
            String methodName = methodDirName.substring(dotIndex + 1);
            
            // Get all case directories for this method
            List<String> caseIds = PathUtils.listCaseDirectories(testClass, serviceName, methodName);
            
            for (String caseId : caseIds) {
                PrepareData prepareData = loadTestCase(testClass, serviceName, methodName, caseId);
                if (prepareData != null) {
                    testCases.add(prepareData);
                }
            }
        }

        return testCases;
    }

    /**
     * Load test cases for a specific method.
     *
     * @param testClass   the test class
     * @param serviceName the service name (e.g., "UserService")
     * @param methodName  the method name (e.g., "createUser")
     * @return a list of PrepareData objects for the method
     */
    public List<PrepareData> loadTestCasesForMethod(Class<?> testClass, String serviceName, String methodName) {
        List<PrepareData> testCases = new ArrayList<>();
        List<String> caseIds = PathUtils.listCaseDirectories(testClass, serviceName, methodName);
        
        for (String caseId : caseIds) {
            PrepareData prepareData = loadTestCase(testClass, serviceName, methodName, caseId);
            if (prepareData != null) {
                testCases.add(prepareData);
            }
        }
        
        return testCases;
    }

    /**
     * Load a single test case configuration.
     *
     * @param testClass   the test class
     * @param serviceName the service name
     * @param methodName  the method name
     * @param caseId      the case ID
     * @return the PrepareData object
     */
    public PrepareData loadTestCase(Class<?> testClass, String serviceName, String methodName, String caseId) {
        Path configPath = PathUtils.getTestConfigPath(testClass, serviceName, methodName, caseId);

        if (!Files.exists(configPath)) {
            log.debug("No test_config.yaml found for {}.{}/{}", serviceName, methodName, caseId);
            return PrepareData.builder()
                    .caseId(caseId)
                    .serviceName(serviceName)
                    .methodName(methodName)
                    .loaded(false)
                    .errorMessage("Configuration file not found")
                    .build();
        }

        try (InputStream is = Files.newInputStream(configPath)) {
            TestCaseConfig config = yamlMapper.readValue(is, TestCaseConfig.class);
            config.setCaseId(caseId);
            
            // Set method name from directory structure (overrides any config value)
            config.setMethod(methodName);

            // Clear the global pool and capture fresh variables for this test case.
            SharedVariablePool.clear();
            
            // Resolve variables in the configuration (this captures to SharedVariablePool)
            resolveVariablesInConfig(config);
            
            // Copy captured variables to PrepareData for per-test-case scoping
            Map<String, Object> capturedVariables = SharedVariablePool.getAll();

            return PrepareData.builder()
                    .caseId(caseId)
                    .serviceName(serviceName)
                    .methodName(methodName)
                    .config(config)
                    .loaded(true)
                    .capturedVariables(capturedVariables)
                    .build();
        } catch (IOException e) {
            log.error("Failed to load test case config for {}.{}/{}", serviceName, methodName, caseId, e);
            return PrepareData.builder()
                    .caseId(caseId)
                    .serviceName(serviceName)
                    .methodName(methodName)
                    .loaded(false)
                    .errorMessage(e.getMessage())
                    .build();
        }
    }

    /**
     * Convert test case arguments to method parameter types.
     *
     * @param config the test case configuration
     * @param method the target method
     * @return an array of converted arguments
     */
    public Object[] convertArgs(TestCaseConfig config, Method method) {
        List<Object> args = config.getArgs();
        if (args == null || args.isEmpty()) {
            return new Object[0];
        }

        Parameter[] parameters = method.getParameters();
        Type[] genericTypes = method.getGenericParameterTypes();
        Object[] result = new Object[parameters.length];

        for (int i = 0; i < parameters.length && i < args.size(); i++) {
            Object arg = args.get(i);
            Type targetType = genericTypes[i];

            result[i] = convertToType(arg, targetType);
        }

        return result;
    }

    /**
     * Convert an object to a specific type using Jackson.
     *
     * @param value      the value to convert
     * @param targetType the target type
     * @return the converted object
     */
    public Object convertToType(Object value, Type targetType) {
        if (value == null) {
            return null;
        }

        try {
            JavaType javaType = jsonMapper.getTypeFactory().constructType(targetType);

            // If it's already the correct type, return it
            if (javaType.getRawClass().isInstance(value)) {
                return value;
            }

            // Convert through JSON serialization
            String json = jsonMapper.writeValueAsString(value);
            return jsonMapper.readValue(json, javaType);
        } catch (IOException e) {
            throw new DataLoadingException("Failed to convert argument to type: " + targetType, e);
        }
    }

    /**
     * Resolve variable expressions in the test case configuration.
     */
    private void resolveVariablesInConfig(TestCaseConfig config) {
        if (config.getArgs() != null) {
            List<Object> resolvedArgs = new ArrayList<>();
            for (Object arg : config.getArgs()) {
                resolvedArgs.add(resolveVariablesInObject(arg, true));
            }
            config.setArgs(resolvedArgs);
        }

        if (config.getResult() != null) {
            config.setResult(resolveVariablesInObject(config.getResult(), false));
        }
    }

    /**
     * Recursively resolve variables in an object (map, list, or string).
     */
    @SuppressWarnings("unchecked")
    private Object resolveVariablesInObject(Object obj, boolean captureToPool) {
        if (obj == null) {
            return null;
        }

        if (obj instanceof String) {
            return variableResolver.resolve((String) obj, captureToPool);
        }

        if (obj instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) obj;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                entry.setValue(resolveVariablesInObject(entry.getValue(), captureToPool));
            }
            return map;
        }

        if (obj instanceof List) {
            List<Object> list = (List<Object>) obj;
            List<Object> resolvedList = new ArrayList<>();
            for (Object item : list) {
                resolvedList.add(resolveVariablesInObject(item, captureToPool));
            }
            return resolvedList;
        }

        return obj;
    }
}
