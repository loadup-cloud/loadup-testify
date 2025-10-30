package com.loadup.testify.core.model;

import java.util.List;

public class ExpectedResult {
    private Object value;
    private String exception;
    private List<FieldAssertion> assertions;
    private String customAssertion;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public List<FieldAssertion> getAssertions() {
        return assertions;
    }

    public void setAssertions(List<FieldAssertion> assertions) {
        this.assertions = assertions;
    }

    public String getCustomAssertion() {
        return customAssertion;
    }

    public void setCustomAssertion(String customAssertion) {
        this.customAssertion = customAssertion;
    }
}

