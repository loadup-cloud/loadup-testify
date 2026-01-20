package com.github.loadup.testify.core.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil {

  private static final ObjectMapper mapper = new ObjectMapper();

  static {
    mapper.registerModule(new JavaTimeModule());
    // 允许未知的属性，防止反序列化失败
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  /** 将对象转为 JSON 字符串 */
  public static String toJson(Object obj) {
    try {
      return mapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException("Json serialization failed", e);
    }
  }

  /** 将 JsonNode 转换为特定的 List<Map>，用于 DbAssertEngine */
  public static List<Map<String, Object>> toListMap(JsonNode node) {
    return mapper.convertValue(node, new TypeReference<>() {});
  }

  public static JsonNode valueToTree(Object obj) {
    return mapper.valueToTree(obj);
  }

  public static Map<String, Object> convertValue(
      JsonNode exp, TypeReference<Map<String, Object>> typeReference) {
    return mapper.convertValue(exp, typeReference);
  }
}
