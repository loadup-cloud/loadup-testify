package com.github.loadup.testify.core.model;

/*-
 * #%L
 * LoadUp Testify - Core
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
