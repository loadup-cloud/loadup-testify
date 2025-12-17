package com.github.loadup.testify.assertions.rule;

/*-
 * #%L
 * LoadUp Testify - Assertions
 * %%
 * Copyright (C) 2026 LoadUp Cloud
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

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
