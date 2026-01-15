package com.github.loadup.testify.data.engine;

import java.util.HashMap;
import java.util.Map;

/**
 * Thread-local context holder for resolved variables within a test case.
 * This ensures variable isolation between concurrent test executions.
 */
public class VariableContext {

    private static final ThreadLocal<Map<String, Object>> CONTEXT = ThreadLocal.withInitial(HashMap::new);

    /**
     * Set the variable context for the current thread.
     */
    public static void set(Map<String, Object> variables) {
        CONTEXT.set(new HashMap<>(variables));
    }

    /**
     * Get the variable context for the current thread.
     */
    public static Map<String, Object> get() {
        return new HashMap<>(CONTEXT.get());
    }

    /**
     * Get a specific variable value by key.
     */
    public static Object getVariable(String key) {
        return CONTEXT.get().get(key);
    }

    /**
     * Put a variable into the current context.
     */
    public static void putVariable(String key, Object value) {
        CONTEXT.get().put(key, value);
    }

    /**
     * Clear the variable context for the current thread.
     * Should be called after each test case to prevent memory leaks.
     */
    public static void clear() {
        CONTEXT.remove();
    }

    /**
     * Check if a variable exists in the context.
     */
    public static boolean hasVariable(String key) {
        return CONTEXT.get().containsKey(key);
    }
}
