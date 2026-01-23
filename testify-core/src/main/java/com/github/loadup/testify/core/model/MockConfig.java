package com.github.loadup.testify.core.model;

import java.util.List;

/** Mock configuration from YAML. Represents a single mock stub definition. */
public record MockConfig(
    String bean, // Bean name to mock
    String method, // Method name to stub
    List<Object> args, // Optional: method argument matchers
    Object thenReturn, // Return value (will be deserialized to target type)
    String thenThrow, // Exception class name to throw
    Long delay // ：延迟毫秒数
    ) {
  /** Check if this mock should throw an exception. */
  public boolean shouldThrow() {
    return thenThrow != null && !thenThrow.isBlank();
  }

  /** Check if this mock should return a value. */
  public boolean hasReturnValue() {
    return thenReturn != null;
  }
}
