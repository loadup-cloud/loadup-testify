package com.loadup.testify.core.executor;

import com.loadup.testify.core.context.TestContext;
import com.loadup.testify.core.model.TestCase;
import com.loadup.testify.core.model.TestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Executes test cases by invoking target methods with configured inputs
 */
public class TestExecutor {

    private static final Logger log = LoggerFactory.getLogger(TestExecutor.class);

    /**
     * Execute test case
     */
    public Object execute(TestSuite testSuite, TestCase testCase, Object targetInstance) throws Exception {
        log.info("Executing test case: {} - {}", testCase.getId(), testCase.getName());

        // Get target method
        Method targetMethod = findTargetMethod(testSuite, targetInstance.getClass());

        // Prepare method parameters
        Object[] parameters = prepareParameters(testCase, targetMethod);

        // Invoke method
        return targetMethod.invoke(targetInstance, parameters);
    }

    /**
     * Find target method in class
     */
    private Method findTargetMethod(TestSuite testSuite, Class<?> targetClass) throws NoSuchMethodException {
        String methodName = testSuite.getTargetMethod();

        // Find method by name (will need to match parameter types)
        for (Method method : targetClass.getDeclaredMethods()) {
            if (method.getName().equals(methodName)) {
                method.setAccessible(true);
                return method;
            }
        }

        throw new NoSuchMethodException("Method not found: " + methodName + " in class: " + targetClass.getName());
    }

    /**
     * Prepare method parameters from test case inputs
     */
    private Object[] prepareParameters(TestCase testCase, Method method) {
        Map<String, Object> inputs = testCase.getInputs();
        Class<?>[] parameterTypes = method.getParameterTypes();

        if (parameterTypes.length == 0) {
            return new Object[0];
        }

        List<Object> parameters = new ArrayList<>();
        TestContext context = TestContext.current();

        // Match inputs by order or by name
        for (int i = 0; i < parameterTypes.length; i++) {
            String paramName = "param" + i; // Default parameter name

            Object value = inputs.get(paramName);
            if (value == null && inputs.size() == 1) {
                // If single input, use it directly
                value = inputs.values().iterator().next();
            }

            // Resolve references
            value = context.resolveReference(value);

            // TODO: Type conversion if needed
            parameters.add(value);
        }

        return parameters.toArray();
    }
}

