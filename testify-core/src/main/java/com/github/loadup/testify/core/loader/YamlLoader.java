package com.github.loadup.testify.core.loader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.loadup.testify.data.engine.VariableEngine;
import com.github.loadup.testify.core.model.TestContext;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.Map;

public class YamlLoader {

    private static final ObjectMapper yamlMapper;
    private static final ObjectMapper jsonMapper; // 用于转换 JsonNode
    private final VariableEngine variableEngine = new VariableEngine();

    static {
        yamlMapper = new ObjectMapper(new YAMLFactory());
        yamlMapper.registerModule(new JavaTimeModule());
        yamlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JavaTimeModule());
    }

    @SneakyThrows
    public TestContext load(String path) {
        // 1. 读取原始文件流
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
        if (is == null) {
            throw new IllegalArgumentException("Test case file not found: " + path);
        }

        // 2. 第一次解析：只解析 variables 部分，为了拿到变量定义
        // (我们先读成 Map，避免过早反序列化其他部分导致报错)
        Map<String, Object> rawMap = yamlMapper.readValue(is, Map.class);
        Map<String, String> rawVars = (Map<String, String>) rawMap.get("variables");

        // 3. 计算变量上下文
        Map<String, Object> evaluatedVars = variableEngine.resolveVariables(rawVars);

        // 4. 全局替换：将整个 YAML 文件内容当作字符串进行占位符替换
        // 注意：需要重新读流或者更高效的方式。这里为了演示，简单粗暴地将 Map 转回 String 再替换
        String jsonString = jsonMapper.writeValueAsString(rawMap);
        String processedJson = variableEngine.replacePlaceholders(jsonString, evaluatedVars);

        // 5. 最终反序列化为 TestContext 对象
        return jsonMapper.readValue(processedJson, TestContext.class);
    }

    // 供 DataProvider 转换 Input 参数使用
    public <T> T convertValue(Object fromValue, Class<T> toValueType) {
        return jsonMapper.convertValue(fromValue, toValueType);
    }
}