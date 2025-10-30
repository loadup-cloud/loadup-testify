package com.loadup.testify.core.model;

public class FieldAssertion {
    private String field;
    private AssertionType type;
    private Object value;
    private String message;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public AssertionType getType() {
        return type;
    }

    public void setType(AssertionType type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

