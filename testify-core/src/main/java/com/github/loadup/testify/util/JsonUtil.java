/* Copyright (C) LoadUp Cloud 2022-2025 */
package com.github.loadup.testify.util;

/*-
 * #%L
 * loadup-components-test
 * %%
 * Copyright (C) 2022 - 2025 loadup_cloud
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.github.loadup.testify.log.TestifyLogUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 用于处理Json
 *
 *
 *
 */
@Slf4j
public class JsonUtil {

    private static final ObjectMapper objectMapper = initObjectMapper();

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    private static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static ObjectMapper initObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // 忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 忽略空Bean转json的错误
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 取消默认转换timestamps形式
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 对象的所有字段全部列入
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        // 所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
        mapper.setDateFormat(new SimpleDateFormat(DEFAULT_DATE_FORMAT));
        // mapper.activateDefaultTypingAsProperty(mapper.getPolymorphicTypeValidator(),
        // ObjectMapper.DefaultTyping.NON_FINAL, "@type");

        // 注册 JavaTimeModule 处理 java.time 包的类
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(
                LocalDate.class,
                new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
        javaTimeModule.addDeserializer(
                LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
        javaTimeModule.addSerializer(
                LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)));
        javaTimeModule.addDeserializer(
                LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)));
        mapper.registerModule(javaTimeModule);

        SimpleModule module = new SimpleModule();
        mapper.registerModule(module);
        return mapper;
    }

    /**
     * 将对象转换为JSON字符串
     */
    public static String toJSONString(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return object instanceof String ? (String) object : objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> String toJSONStringPretty(T obj) {

        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String
                    ? (String) obj
                    : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将JSON字符串转换为指定类型的对象
     */
    public static <T> T parseObject(String jsonString, Class<T> valueType) {
        if (StringUtils.isEmpty(jsonString) || valueType == null) {
            return null;
        }
        try {
            return objectMapper.readValue(jsonString, valueType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T parseObject(String str, TypeReference<T> typeReference) {

        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }

        try {
            return (T)
                    (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T parseObject(String str, Class<?> collectionClass, Class<?>... elementClasses) {

        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);

        try {
            return objectMapper.readValue(str, javaType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Json string to map
     *
     * @param jsonString
     * @return
     */
    public static Map toMap(String jsonString) {
        return parseObject(jsonString, Map.class);
    }

    /**
     * parse map to object
     *
     * @param map
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T parseObject(Map map, Class<T> valueType) {
        if (MapUtils.isEmpty(map) || valueType == null) {
            return null;
        }
        String jsonString = toJSONString(map);
        return parseObject(jsonString, valueType);
    }

    public static <T> T parseObject(Map map, Class<?> collectionClass, Class<?>... elementClasses) {
        if (MapUtils.isEmpty(map) || collectionClass == null) {
            return null;
        }
        String jsonString = toJSONString(map);
        return parseObject(jsonString, collectionClass, elementClasses);
    }

    public static Object parseObject(Map<String, String> value, JavaType javaType) {
        String jsonString = toJSONString(value);
        try {
            return objectMapper.readValue(jsonString, javaType);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    /**
     * 从JSON字符串中获取指定路径下的子节点
     */
    public static JsonNode getSubNode(String jsonString, String path) {
        try {
            JsonNode rootNode = objectMapper.readTree(jsonString);
            return rootNode.at(path);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 创建一个空的JSON对象节点
     */
    public static ObjectNode createEmptyJsonObject() {
        return objectMapper.createObjectNode();
    }

    /**
     * 创建一个空的JSON数组节点
     */
    public static ArrayNode createEmptyJsonArray() {
        return objectMapper.createArrayNode();
    }

    /**
     * 向JSON数组节点中添加元素
     */
    public static void addElementToJsonArray(ArrayNode arrayNode, Object element) {
        try {
            JsonNode jsonNode = objectMapper.valueToTree(element);
            arrayNode.add(jsonNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将JSON数组节点转换为指定类型的对象列表
     */
    public static <T> List<T> parseObject(ArrayNode arrayNode, Class<T> valueType) {
        List<T> objectList = new ArrayList<>();
        if (arrayNode != null) {
            for (JsonNode jsonNode : arrayNode) {
                T object = parseObject(jsonNode.toString(), valueType);
                if (object != null) {
                    objectList.add(object);
                }
            }
        }
        return objectList;
    }

    public static JsonNode toJsonNodeTree(String jsonString) {
        try {
            return objectMapper.readTree(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, Object> toJsonMap(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return new HashMap<>();
        }
        try {
            return objectMapper.readValue(jsonString, new TypeReference<>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    public static Map<String, String> jsonToMap(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return new HashMap<>();
        }
        try {
            return objectMapper.readValue(jsonString, new TypeReference<>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    /**
     * 将对象转化为json字符串，并转换为prettyFormat类型便于阅读, 并解决异常
     *
     * @param object
     * @return
     */
    public static String toPrettyString(Object object) {
        try {
            return JSON.toJSONString(object);
        } catch (Exception e) {
            TestifyLogUtil.warn(log, "对象无法转换为json");
            TestifyLogUtil.debug(log, LogUtil.getErrorMessage(e));
            return "{}";
        }
    }

    /**
     * 将对象打印到json文件
     */
    public static void writeObjectToFile(File file, Object object) {
        String jsString = JSON.toJSONString(object);
        FileUtil.writeFile(file, jsString, -1);
    }

    /**
     * 基于json生成对象，主要用于生成请求
     *
     * @param jsonRelativePath
     * @param clazz
     * @return
     */
    public static <T> T genObjectFromJsonFile(String jsonRelativePath, Class<T> clazz) {
        String jsonFullPath = FileUtil.getRelativePath(jsonRelativePath, null);
        String jsonString = FileUtil.readFile(jsonFullPath);
        T testObject = JSON.parseObject(jsonString, clazz);
        return testObject;
    }

    /**
     * 基于json字符串生成对象
     *
     * @param jsonString
     * @param clazz
     * @return
     */
    public static <T> T genObjectFromJsonString(String jsonString, Class<T> clazz) {
        return JSON.parseObject(jsonString, clazz);
    }


    public static <T> T stringToObject(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            return null;
        }

        return JSON.parseObject(json, clazz);
    }

    /**
     * 将对象变成字符串
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return JSONObject.toJSONString(object);
        } catch (Error error) {
            // in case of unsupported methods by lower version of fastjson
            return JSONObject.toJSONString(object);
        }
    }

    /**
     * 解析成json数组
     *
     * @param json
     * @return
     */
    public static JSONArray toJsonArray(String json) {
        if (json == null) {
            return null;
        }
        return JSONArray.parseArray(json);
    }

    /**
     * 将json字符串转换成jsonlist
     *
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String str, Class<T> cls) {
        if (StringUtils.isBlank(str)) {
            return new ArrayList<T>();
        }
        return JSONArray.parseArray(str, cls);
    }


}
