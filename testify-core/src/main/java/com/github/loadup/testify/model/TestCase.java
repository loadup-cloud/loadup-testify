package com.github.loadup.testify.model;

import java.util.List;
import java.util.Map;

public class TestCase {
    private String id;
    private String name;
    private String description;
    private boolean enabled = true;
    private DatabaseSetup databaseSetup;
    private List<MockConfig> mocks;
    private Map<String, Object> inputs;
    private ExpectedResult expected;
    private DatabaseVerification databaseVerification;
    private List<String> tags;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public DatabaseSetup getDatabaseSetup() {
        return databaseSetup;
    }

    public void setDatabaseSetup(DatabaseSetup databaseSetup) {
        this.databaseSetup = databaseSetup;
    }

    public List<MockConfig> getMocks() {
        return mocks;
    }

    public void setMocks(List<MockConfig> mocks) {
        this.mocks = mocks;
    }

    public Map<String, Object> getInputs() {
        return inputs;
    }

    public void setInputs(Map<String, Object> inputs) {
        this.inputs = inputs;
    }

    public ExpectedResult getExpected() {
        return expected;
    }

    public void setExpected(ExpectedResult expected) {
        this.expected = expected;
    }

    public DatabaseVerification getDatabaseVerification() {
        return databaseVerification;
    }

    public void setDatabaseVerification(DatabaseVerification databaseVerification) {
        this.databaseVerification = databaseVerification;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}

