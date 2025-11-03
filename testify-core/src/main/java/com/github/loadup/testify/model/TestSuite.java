package com.github.loadup.testify.model;

import java.util.List;
import java.util.Map;

public class TestSuite {
    private String name;
    private String description;
    private String targetClass;
    private String targetMethod;
    private DatabaseSetup globalSetup;
    private List<MockConfig> globalMocks;
    private Map<String, Object> variables;
    private List<TestCase> testCases;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }

    public String getTargetMethod() {
        return targetMethod;
    }

    public void setTargetMethod(String targetMethod) {
        this.targetMethod = targetMethod;
    }

    public DatabaseSetup getGlobalSetup() {
        return globalSetup;
    }

    public void setGlobalSetup(DatabaseSetup globalSetup) {
        this.globalSetup = globalSetup;
    }

    public List<MockConfig> getGlobalMocks() {
        return globalMocks;
    }

    public void setGlobalMocks(List<MockConfig> globalMocks) {
        this.globalMocks = globalMocks;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }
}


