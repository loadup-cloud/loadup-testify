package com.loadup.testify.starter.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for LoadUp Testify.
 * These properties can be set in application.properties or application.yml.
 *
 * Example:
 * <pre>
 * loadup.testify.enabled=true
 * loadup.testify.date-tolerance=5000
 * loadup.testify.clear-tables-before-test=true
 * </pre>
 */
@Data
@ConfigurationProperties(prefix = "loadup.testify")
public class TestifyProperties {

    /**
     * Enable or disable LoadUp Testify auto-configuration.
     */
    private boolean enabled = true;

    /**
     * Default tolerance for datetime comparisons in milliseconds.
     */
    private long dateTolerance = 5000L;

    /**
     * Whether to clear database tables before inserting PrepareData.
     */
    private boolean clearTablesBeforeTest = true;

    /**
     * Whether to enable detailed logging for assertions.
     */
    private boolean verboseLogging = true;

    /**
     * Base path for test data files (relative to classpath).
     * If not set, uses the test class package path.
     */
    private String testDataBasePath;
}
