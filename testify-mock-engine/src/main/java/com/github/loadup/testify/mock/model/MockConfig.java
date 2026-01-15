package com.github.loadup.testify.mock.model;

import java.util.List;
import java.util.Map;

/**
 * Mock configuration from YAML.
 * Represents a single mock stub definition.
 */
public record MockConfig(
        String bean, // Bean name to mock
        String method, // Method name to stub
        List<Object> args, // Optional: method argument matchers
        Object returnValue, // Return value (will be deserialized to target type)
        String throwException // Exception class name to throw
) {
    /**
     * Check if this mock should throw an exception.
     */
    public boolean shouldThrow() {
        return throwException != null && !throwException.isBlank();
    }

    /**
     * Check if this mock should return a value.
     */
    public boolean hasReturnValue() {
        return returnValue != null;
    }
}
