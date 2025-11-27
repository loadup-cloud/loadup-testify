package com.loadup.testify.common.variable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Thread-safe shared variable pool for storing and retrieving variables across test cases.
 * Supports automatic capture of Datafaker-generated values and cross-file references.
 */
public class SharedVariablePool {

    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL_POOL = 
            ThreadLocal.withInitial(ConcurrentHashMap::new);

    private SharedVariablePool() {
        // Utility class
    }

    /**
     * Store a variable in the pool.
     *
     * @param name  the variable name
     * @param value the variable value
     */
    public static void put(String name, Object value) {
        THREAD_LOCAL_POOL.get().put(name, value);
    }

    /**
     * Retrieve a variable from the pool.
     *
     * @param name the variable name
     * @return the variable value, or null if not found
     */
    public static Object get(String name) {
        return THREAD_LOCAL_POOL.get().get(name);
    }

    /**
     * Retrieve a variable from the pool with a default value.
     *
     * @param name         the variable name
     * @param defaultValue the default value if not found
     * @return the variable value, or the default value if not found
     */
    public static Object getOrDefault(String name, Object defaultValue) {
        return THREAD_LOCAL_POOL.get().getOrDefault(name, defaultValue);
    }

    /**
     * Check if a variable exists in the pool.
     *
     * @param name the variable name
     * @return true if the variable exists
     */
    public static boolean contains(String name) {
        return THREAD_LOCAL_POOL.get().containsKey(name);
    }

    /**
     * Remove a variable from the pool.
     *
     * @param name the variable name
     * @return the removed value, or null if not found
     */
    public static Object remove(String name) {
        return THREAD_LOCAL_POOL.get().remove(name);
    }

    /**
     * Get all variables in the pool.
     *
     * @return a copy of the current variable pool
     */
    public static Map<String, Object> getAll() {
        return new ConcurrentHashMap<>(THREAD_LOCAL_POOL.get());
    }

    /**
     * Clear all variables from the pool for the current thread.
     */
    public static void clear() {
        THREAD_LOCAL_POOL.get().clear();
    }

    /**
     * Clean up the thread-local storage to prevent memory leaks.
     */
    public static void cleanup() {
        THREAD_LOCAL_POOL.remove();
    }
}
