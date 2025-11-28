package com.github.loadup.testify.common.exception;

/**
 * Exception thrown when test data loading fails.
 */
public class DataLoadingException extends TestifyException {

    public DataLoadingException(String message) {
        super(message);
    }

    public DataLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
