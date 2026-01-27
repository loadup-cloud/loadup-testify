package io.github.loadup.testify.core.model;

import java.util.List;
import java.util.Map;

/**
 * Mock configuration from YAML. Represents a single mock stub definition.
 */
public record MockConfig(
        String bean, // Bean name to mock
        String method, // Method name to stub
        Map<String, Object> args, // Optional: method argument matchers
        Object thenReturn, // Return value (will be deserialized to target type)
        Map<String, String> thenThrow, // Exception class name to throw
        Long delay // ：延迟毫秒数
) {

}
