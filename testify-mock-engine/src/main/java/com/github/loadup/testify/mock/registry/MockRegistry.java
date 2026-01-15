package com.github.loadup.testify.mock.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Thread-local registry for tracking mocked beans during test execution.
 * Ensures mocks are properly isolated between test cases.
 */
public class MockRegistry {

    private static final ThreadLocal<Map<String, Object>> MOCKED_BEANS = ThreadLocal.withInitial(HashMap::new);

    /**
     * Register a mocked bean for the current thread.
     * 
     * @param beanName   Bean name
     * @param mockedBean Mocked bean instance (spy or mock)
     */
    public static void register(String beanName, Object mockedBean) {
        MOCKED_BEANS.get().put(beanName, mockedBean);
    }

    /**
     * Get a registered mocked bean.
     */
    public static Object get(String beanName) {
        return MOCKED_BEANS.get().get(beanName);
    }

    /**
     * Get all registered mock bean names for the current thread.
     */
    public static Set<String> getAllMockedBeanNames() {
        return MOCKED_BEANS.get().keySet();
    }

    /**
     * Check if a bean is mocked.
     */
    public static boolean isMocked(String beanName) {
        return MOCKED_BEANS.get().containsKey(beanName);
    }

    /**
     * Clear all mocks for the current thread.
     * Should be called in @AfterMethod to prevent cross-test pollution.
     */
    public static void clear() {
        MOCKED_BEANS.remove();
    }

    /**
     * Get all mocked beans (for resetting).
     */
    public static Map<String, Object> getAllMockedBeans() {
        return new HashMap<>(MOCKED_BEANS.get());
    }
}
