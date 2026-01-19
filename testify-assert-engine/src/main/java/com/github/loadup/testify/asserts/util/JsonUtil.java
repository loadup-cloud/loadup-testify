package com.github.loadup.testify.asserts.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.loadup.testify.asserts.model.MatchResult;
import lombok.extern.slf4j.Slf4j;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.List;
import java.util.Map;

@Slf4j
public class JsonUtil {

  private static final ObjectMapper mapper = new ObjectMapper();

  static {
    mapper.registerModule(new JavaTimeModule());
    // 允许未知的属性，防止反序列化失败
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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

  /**
   * 使用 JSONAssert 进行深度比对
   *
   * @param actual 实际对象（String, Map 或 JsonNode）
   * @param expected 期望对象（String, Map 或 JsonNode）
   * @param strictMode 是否使用严格模式
   */
  public static MatchResult compare(Object actual, Object expected, boolean strictMode) {
    try {
      String actStr = actual instanceof String ? (String) actual : toJson(actual);
      String expStr = expected instanceof String ? (String) expected : toJson(expected);

      // NON_EXTENSIBLE: 期望中有的，实际必须有且相等；实际多出的字段会被忽略（Lenient核心）
      // STRICT: 必须完全一致，字段数量、顺序（如果是数组）都要一致
      JSONCompareMode mode = strictMode ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT;

      JSONAssert.assertEquals(expStr, actStr, mode);
      return MatchResult.pass();
    } catch (AssertionError e) {
      // JSONAssert 报错时会提供非常详细的 Diff 信息
      return MatchResult.fail(actual, expected, e.getMessage());
    } catch (Exception e) {
      return MatchResult.fail(actual, expected, "JSON comparison error: " + e.getMessage());
    }
  }
}
