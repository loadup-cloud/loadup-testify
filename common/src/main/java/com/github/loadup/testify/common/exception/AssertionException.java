package com.github.loadup.testify.common.exception;

/**
 * Exception thrown when assertion fails.
 */
public class AssertionException extends TestifyException {

    public AssertionException(String message) {
        super(message);
    }

    public AssertionException(String message, Throwable cause) {
        super(message, cause);
    }
}
