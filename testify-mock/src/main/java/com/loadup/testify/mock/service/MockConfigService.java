package com.loadup.testify.mock.service;

import com.github.loadup.testify.context.TestContext;
import com.github.loadup.testify.model.MockBehavior;
import com.github.loadup.testify.model.MockConfig;
import lombok.extern.slf4j.Slf4j;
import org.mockito.Mockito;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Service for configuring mocks based on YAML configuration
 */
@Slf4j
@Service
public class MockConfigService {

    private final Map<String, Object> mockRegistry = new HashMap<>();

    /**
     * Setup mocks according to configuration
     */
    public void setupMocks(List<MockConfig> mockConfigs, Map<String, Object> beanMap) {
        if (mockConfigs == null || mockConfigs.isEmpty()) {
            return;
        }

        for (MockConfig config : mockConfigs) {
            setupMock(config, beanMap);
        }
    }

    /**
     * Setup single mock
     */
    private void setupMock(MockConfig config, Map<String, Object> beanMap) {
        log.info("Setting up mock for: {} method: {}", config.getTarget(), config.getMethod());

        // Get or create mock
        Object mock = getOrCreateMock(config.getTarget(), beanMap);

        if (mock == null) {
            log.warn("Failed to create mock for: {}", config.getTarget());
            return;
        }

        // Configure mock behavior
        configureMockBehavior(mock, config);
    }

    /**
     * Get existing mock or create new one
     */
    private Object getOrCreateMock(String target, Map<String, Object> beanMap) {
        // Check if already mocked
        if (mockRegistry.containsKey(target)) {
            return mockRegistry.get(target);
        }

        // Try to get bean from Spring context
        Object bean = beanMap.get(target);

        if (bean != null) {
            // Check if already a mock
            if (Mockito.mockingDetails(bean).isMock()) {
                mockRegistry.put(target, bean);
                return bean;
            }

            // Create spy of existing bean
            Object spy = Mockito.spy(bean);
            mockRegistry.put(target, spy);
            beanMap.put(target, spy); // Replace in bean map
            return spy;
        }

        // Try to load class and create mock
        try {
            Class<?> clazz = Class.forName(target);
            Object mock = Mockito.mock(clazz);
            mockRegistry.put(target, mock);
            beanMap.put(target, mock);
            return mock;
        } catch (ClassNotFoundException e) {
            log.error("Class not found: {}", target, e);
            return null;
        }
    }

    /**
     * Configure mock behavior
     */
    private void configureMockBehavior(Object mock, MockConfig config) {
        String methodName = config.getMethod();
        MockBehavior behavior = config.getBehavior();

        TestContext context = TestContext.current();

        try {
            // Find method
            Method[] methods = mock.getClass().getMethods();
            Method targetMethod = null;

            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    targetMethod = method;
                    break;
                }
            }

            if (targetMethod == null) {
                log.warn("Method not found: {} in class: {}", methodName, mock.getClass());
                return;
            }

            // Configure based on behavior
            switch (behavior) {
                case RETURN:
                    Object returnValue = context.resolveReference(config.getReturnValue());
                    configureReturn(mock, targetMethod, returnValue);
                    break;

                case THROW:
                    configureThrow(mock, targetMethod, config.getThrowException(), config.getExceptionMessage());
                    break;

                case DO_NOTHING:
                    configureDoNothing(mock, targetMethod);
                    break;

                case SPY:
                    // Already handled by spy creation
                    log.info("Using spy for: {}", config.getTarget());
                    break;

                default:
                    log.warn("Unsupported mock behavior: {}", behavior);
            }

        } catch (Exception e) {
            log.error("Failed to configure mock behavior", e);
        }
    }

    /**
     * Configure return value
     */
    private void configureReturn(Object mock, Method method, Object returnValue) {
        Class<?>[] paramTypes = method.getParameterTypes();

        try {
            if (paramTypes.length == 0) {
                when(method.invoke(mock)).thenReturn(returnValue);
            } else {
                // Use any() matchers for parameters
                Object[] matchers = new Object[paramTypes.length];
                for (int i = 0; i < paramTypes.length; i++) {
                    matchers[i] = any();
                }
                when(method.invoke(mock, matchers)).thenReturn(returnValue);
            }
        } catch (Exception e) {
            log.error("Failed to configure return value for method: {}", method.getName(), e);
        }
    }

    /**
     * Configure throw exception
     */
    private void configureThrow(Object mock, Method method, String exceptionClass, String message) {
        try {
            Class<?> excClass = Class.forName(exceptionClass);
            Exception exception;

            if (message != null) {
                exception = (Exception) excClass.getConstructor(String.class).newInstance(message);
            } else {
                exception = (Exception) excClass.newInstance();
            }

            Class<?>[] paramTypes = method.getParameterTypes();
            if (paramTypes.length == 0) {
                when(method.invoke(mock)).thenThrow(exception);
            } else {
                Object[] matchers = new Object[paramTypes.length];
                for (int i = 0; i < paramTypes.length; i++) {
                    matchers[i] = any();
                }
                when(method.invoke(mock, matchers)).thenThrow(exception);
            }

        } catch (Exception e) {
            log.error("Failed to configure exception for method: {}", method.getName(), e);
        }
    }

    /**
     * Configure do nothing (for void methods)
     */
    private void configureDoNothing(Object mock, Method method) {
        try {
            Class<?>[] paramTypes = method.getParameterTypes();
            if (paramTypes.length == 0) {
                doNothing().when(mock);
                method.invoke(mock);
            } else {
                Object[] matchers = new Object[paramTypes.length];
                for (int i = 0; i < paramTypes.length; i++) {
                    matchers[i] = any();
                }
                doNothing().when(mock);
                method.invoke(mock, matchers);
            }
        } catch (Exception e) {
            log.error("Failed to configure do nothing for method: {}", method.getName(), e);
        }
    }

    /**
     * Reset all mocks
     */
    public void resetMocks() {
        for (Object mock : mockRegistry.values()) {
            Mockito.reset(mock);
        }
    }

    /**
     * Clear mock registry
     */
    public void clearMocks() {
        mockRegistry.clear();
    }
}

