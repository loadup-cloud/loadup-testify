package io.github.loadup.testify.core.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @author lise
 * @version TestContext.java, v 0.1 2026年01月13日 10:34 lise
 */
// 对应 YAML 的根结构
@Getter
@Setter
public final class TestContext {
    private String testName;
    private String yamlPath;
    private Map<String, Object> variables;
    private JsonNode input;
    private List<MockConfig> mocks;
    private JsonNode setup;
    private JsonNode expect;

    public String testName() {
        return testName;
    }

    public String yamlPath() {
        return yamlPath;
    }

    public Map<String, Object> variables() {
        return variables;
    }

    public JsonNode input() {
        return input;
    }

    public List<MockConfig> mocks() {
        return mocks;
    }

    public JsonNode setup() {
        return setup;
    }

    public JsonNode expect() {
        return expect;
    }
}
