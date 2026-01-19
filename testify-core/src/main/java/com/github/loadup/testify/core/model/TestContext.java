package com.github.loadup.testify.core.model;

import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author lise
 * @version TestContext.java, v 0.1 2026年01月13日 10:34 lise
 */
// 对应 YAML 的根结构
public record TestContext(
    String testName,
    String yamlPath,
    Map<String, Object> variables, // 变量定义块
    JsonNode input, // 支持 List 或 单一对象，使用 JsonNode 延迟解析
    JsonNode mocks, // Mock 配置
    JsonNode setup, // 包含 clean_sql 等
    JsonNode expect // 期望值
    ) {}
