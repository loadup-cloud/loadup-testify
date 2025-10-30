package com.loadup.testify.core.model;

public class ColumnVerification {
    private String name;
    private VerificationRule rule;
    private Object value;
    private Integer toleranceSeconds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VerificationRule getRule() {
        return rule;
    }

    public void setRule(VerificationRule rule) {
        this.rule = rule;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Integer getToleranceSeconds() {
        return toleranceSeconds;
    }

    public void setToleranceSeconds(Integer toleranceSeconds) {
        this.toleranceSeconds = toleranceSeconds;
    }
}

