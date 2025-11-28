package com.loadup.testify.assertions.rule;

/**
 * Comparison operators for assertions.
 */
public enum CompareOperator {
    /**
     * Exact equality comparison.
     */
    EQUALS,

    /**
     * Not equal comparison.
     */
    NOT_EQUALS,

    /**
     * Value is not null.
     */
    NOT_NULL,

    /**
     * Value is null.
     */
    IS_NULL,

    /**
     * Greater than comparison.
     */
    GT,

    /**
     * Greater than or equal comparison.
     */
    GTE,

    /**
     * Less than comparison.
     */
    LT,

    /**
     * Less than or equal comparison.
     */
    LTE,

    /**
     * String contains comparison.
     */
    CONTAINS,

    /**
     * String starts with comparison.
     */
    STARTS_WITH,

    /**
     * String ends with comparison.
     */
    ENDS_WITH,

    /**
     * Regular expression match.
     */
    REGEX,

    /**
     * Ignore specific fields during comparison.
     */
    IGNORE_FIELDS,

    /**
     * Ignore order when comparing collections.
     */
    IGNORE_ORDER,

    /**
     * Skip this field entirely.
     */
    SKIP,

    /**
     * Date/time comparison with tolerance (uses dateTolerance in milliseconds).
     * Example: Allow 5 seconds difference means dateTolerance = 5000.
     */
    DATETIME_TOLERANCE,

    /**
     * JSON content comparison - parses both values as JSON and compares semantically.
     * Handles key ordering differences and whitespace variations.
     */
    JSON_EQUALS
}
