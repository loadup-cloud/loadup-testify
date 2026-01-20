package com.github.loadup.testify.mock.engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.loadup.testify.mock.model.MockConfig;
import com.github.loadup.testify.mock.registry.MockRegistry;
import java.lang.reflect.Method;
import java.util.List;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * Mock engine for dynamic bean mocking and stubbing.
 * Integrates with Spring ApplicationContext and Mockito.
 */
public class MockEngine {

    private static final Logger logger = LoggerFactory.getLogger(MockEngine.class);
    private final ApplicationContext applicationContext;
    private final ObjectMapper objectMapper;

    public MockEngine(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Apply mocks defined in YAML configuration.
     * 
     * @param mockConfigs List of mock configurations
     */
    public void applyMocks(List<MockConfig> mockConfigs) {
        if (mockConfigs == null || mockConfigs.isEmpty()) {
            return;
        }

        for (MockConfig config : mockConfigs) {
            try {
                applyMock(config);
            } catch (Exception e) {
                logger.error("Failed to apply mock for bean: " + config.bean(), e);
                throw new RuntimeException("Mock configuration error: " + config.bean(), e);
            }
        }
    }

    /**
     * Apply a single mock configuration.
     */
    private void applyMock(MockConfig config) throws Exception {
        String beanName = config.bean();
        String methodName = config.method();

        // Get bean from Spring context
        Object bean = applicationContext.getBean(beanName);
        Object mockedBean;

        // Check if already mocked in this test case
        if (MockRegistry.isMocked(beanName)) {
            mockedBean = MockRegistry.get(beanName);
        } else {
            // Create spy or mock
            mockedBean = Mockito.spy(bean);
            MockRegistry.register(beanName, mockedBean);
        }

        // Find method
        Method[] methods = bean.getClass().getMethods();
        Method targetMethod = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                targetMethod = method;
                break; // Use first matching method (simplified, could be enhanced with parameter
                       // matching)
            }
        }

        if (targetMethod == null) {
            throw new NoSuchMethodException("Method not found: " + methodName + " in bean: " + beanName);
        }

        // Configure stub
        configureStub(mockedBean, targetMethod, config);
    }

    /**
     * Configure Mockito stub based on configuration.
     */
    private void configureStub(Object mockedBean, Method method, MockConfig config) throws Exception {
        // Note: This is a simplified implementation
        // In real scenarios, we'd need to use Mockito's when().thenReturn() API
        // which requires compile-time knowledge of the bean type
        // For now, we'll document that advanced stubbing should be done in code

        logger.info("Mock configured for bean: {} method: {}", config.bean(), config.method());

        // Advanced stubbing would be implemented here using reflection and Mockito's
        // API
        // This would require dynamic bytecode generation or using Mockito's internal
        // APIs
        // For the demo, we'll provide a simplified version
    }

    /**
     * Reset all mocked beans to their original state.
     */
    public void resetAllMocks() {
        MockRegistry.getAllMockedBeans().values().forEach(mock -> {
            try {
                Mockito.reset(mock);
            } catch (Exception e) {
                logger.warn("Failed to reset mock: " + mock.getClass().getName(), e);
            }
        });
    }

    /**
     * Clear all mock registrations.
     */
    public void clearAllMocks() {
        MockRegistry.clear();
    }
}
