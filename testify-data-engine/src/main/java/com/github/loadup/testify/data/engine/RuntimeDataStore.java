package com.github.loadup.testify.data.engine;

import com.github.loadup.testify.data.engine.variable.VariableContext;
import java.util.HashMap;
import java.util.Map;

public class RuntimeDataStore {
  private static final ThreadLocal<Map<String, Object>> RUNTIME_VARS =
      ThreadLocal.withInitial(HashMap::new);

  public static void store(String key, Object value) {
    RUNTIME_VARS.get().put(key, value);
  }

  public static Map<String, Object> getAll() {
    Map<String, Object> combined = new HashMap<>(VariableContext.get());
    combined.putAll(RUNTIME_VARS.get());
    return combined;
  }
}
