package com.github.loadup.testify.assertions.rule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Defines an assertion rule for comparing expected and actual data.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssertionRule {

    /**
     * The field or path to compare (e.g., "response.user.name", "database.users.email").
     */
    private String field;

    /**
     * The comparison operator to use.
     */
    @Builder.Default
    private CompareOperator operator = CompareOperator.EQUALS;

    /**
     * The expected value for comparison.
     * Can be a literal value, a variable reference (${=variable}), or a Datafaker expression.
     */
    private Object expectedValue;

    /**
     * List of fields to ignore when using IGNORE_FIELDS operator.
     */
    private List<String> ignoreFields;

    /**
     * Date/time format pattern for date comparison tolerance.
     */
    private String dateFormat;

    /**
     * Tolerance in milliseconds for date/time comparisons.
     */
    private Long dateTolerance;

    /**
     * Numeric tolerance for floating-point comparisons.
     */
    private Double numericTolerance;

    /**
     * Optional custom error message.
     */
    private String message;

    /**
     * Create a simple equality rule.
     */
    public static AssertionRule equals(String field, Object expectedValue) {
        return AssertionRule.builder()
                .field(field)
                .operator(CompareOperator.EQUALS)
                .expectedValue(expectedValue)
                .build();
    }

    /**
     * Create a not-null rule.
     */
    public static AssertionRule notNull(String field) {
        return AssertionRule.builder()
                .field(field)
                .operator(CompareOperator.NOT_NULL)
                .build();
    }

    /**
     * Create an ignore-order collection comparison rule.
     */
    public static AssertionRule ignoreOrder(String field) {
        return AssertionRule.builder()
                .field(field)
                .operator(CompareOperator.IGNORE_ORDER)
                .build();
    }
}
