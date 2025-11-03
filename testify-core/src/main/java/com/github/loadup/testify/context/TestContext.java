package com.github.loadup.testify.context;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Context for storing test execution data
 * Stores database reference IDs and their generated primary keys
 */
@Data
public class TestContext {

    private static final ThreadLocal<TestContext> CONTEXT = ThreadLocal.withInitial(TestContext::new);

    /**
     * Stores refId -> generated primary key mapping
     */
    private Map<String, Object> databaseReferences = new ConcurrentHashMap<>();

    /**
     * Stores custom variables
     */
    private Map<String, Object> variables = new HashMap<>();

    /**
     * Current test case ID
     */
    private String currentTestCaseId;

    /**
     * Get current thread's context
     */
    public static TestContext current() {
        return CONTEXT.get();
    }

    /**
     * Clear context
     */
    public static void clear() {
        CONTEXT.remove();
    }

    /**
     * Store database reference
     */
    public void putDatabaseReference(String refId, Object primaryKey) {
        databaseReferences.put(refId, primaryKey);
    }

    /**
     * Get database reference
     */
    public Object getDatabaseReference(String refId) {
        return databaseReferences.get(refId);
    }

    /**
     * Put variable
     */
    public void putVariable(String key, Object value) {
        variables.put(key, value);
    }

    /**
     * Get variable
     */
    public Object getVariable(String key) {
        return variables.get(key);
    }

    /**
     * Resolve reference value
     * Supports ${refId} syntax
     */
    public Object resolveReference(Object value) {
        if (value instanceof String) {
            String strValue = (String) value;
            if (strValue.startsWith("${") && strValue.endsWith("}")) {
                String refKey = strValue.substring(2, strValue.length() - 1);
                Object resolved = getDatabaseReference(refKey);
                if (resolved == null) {
                    resolved = getVariable(refKey);
                }
                return resolved != null ? resolved : value;
            }
        }
        return value;
    }
}

