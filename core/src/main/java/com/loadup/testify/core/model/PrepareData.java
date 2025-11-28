package com.loadup.testify.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Model class representing prepared test data for a test case.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrepareData {

    /**
     * The case ID.
     */
    private String caseId;

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
