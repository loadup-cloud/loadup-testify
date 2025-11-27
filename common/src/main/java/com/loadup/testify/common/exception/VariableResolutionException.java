package com.loadup.testify.common.exception;

/**
 * Exception thrown when variable resolution fails.
 */
public class VariableResolutionException extends TestifyException {

    public VariableResolutionException(String message) {
        super(message);
    }

    public VariableResolutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
