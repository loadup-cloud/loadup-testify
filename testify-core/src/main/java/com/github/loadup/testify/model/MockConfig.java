package com.github.loadup.testify.model;

import java.util.Map;

public class MockConfig {
    private String target;
    private String method;
    private Map<String, Object> parameters;
    private MockBehavior behavior;
    private Object returnValue;
    private String throwException;
    private String exceptionMessage;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public MockBehavior getBehavior() {
        return behavior;
    }

    public void setBehavior(MockBehavior behavior) {
        this.behavior = behavior;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }

    public String getThrowException() {
        return throwException;
    }

    public void setThrowException(String throwException) {
        this.throwException = throwException;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}

