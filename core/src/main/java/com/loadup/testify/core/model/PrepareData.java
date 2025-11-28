package com.loadup.testify.core.model;

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
