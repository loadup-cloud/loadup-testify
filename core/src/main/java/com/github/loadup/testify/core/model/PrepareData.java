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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Model class representing prepared test data for a test case.
 * 
 * <p>Directory structure convention:</p>
 * <pre>
 * {ServiceName}.{methodName}/{caseId}/
 * </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrepareData {

    /**
     * The case ID (e.g., "case01").
     */
    private String caseId;

    /**
     * The service name (e.g., "UserService").
     */
    private String serviceName;

    /**
     * The method name (e.g., "createUser").
     */
    private String methodName;

    /**
     * The test case configuration.
     */
    private TestCaseConfig config;

    /**
     * Whether the prepare data has been loaded successfully.
     */
    @Builder.Default
    private boolean loaded = false;

    /**
     * Error message if loading failed.
     */
    private String errorMessage;

    /**
     * Captured variables for this test case.
     * Used to reference Datafaker-generated values across PrepareData and ExpectedData.
     */
    @Builder.Default
    private Map<String, Object> capturedVariables = new ConcurrentHashMap<>();
}
