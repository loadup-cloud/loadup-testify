package com.loadup.testify.common.exception;

/**
 * Base exception for all Testify related errors.
 */
public class TestifyException extends RuntimeException {

    public TestifyException(String message) {
        super(message);
    }

    public TestifyException(String message, Throwable cause) {
        super(message, cause);
    }
}
