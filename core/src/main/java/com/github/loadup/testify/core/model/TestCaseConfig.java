package com.github.loadup.testify.core.model;

import com.github.loadup.testify.assertions.rule.AssertionRule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Model class representing a test case configuration loaded from test_config.yaml.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseConfig {

    /**
     * The case ID (derived from the folder name).
     */
    private String caseId;

    /**
     * Description of the test case.
     */
    private String description;

    /**
     * The method name to invoke on the test bean.
     */
    private String method;

    /**
     * Arguments to pass to the test method.
     * Can be a list of objects that will be mapped to method parameters.
     */
    private List<Object> args;

    /**
     * Expected result from the test method execution.
     */
    private Object result;

    /**
     * Expected database state assertions.
     * Map of table name to expected rows.
     */
    private Map<String, List<Map<String, Object>>> expectedDatabase;

    /**
     * Custom assertion rules for response and database.
     */
    private List<AssertionRule> expected;

    /**
     * Fields to ignore during comparison.
     */
    private List<String> ignoreFields;

    /**
     * Whether to ignore order when comparing collections.
     */
    @Builder.Default
    private boolean ignoreOrder = false;

    /**
     * Setup actions to perform before the test.
     */
    private List<String> setup;

    /**
     * Cleanup actions to perform after the test.
     */
    private List<String> cleanup;

    /**
     * Whether this test case is enabled.
     */
    @Builder.Default
    private boolean enabled = true;
}
