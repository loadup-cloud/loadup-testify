package com.github.loadup.testify.starter.autoconfigure;

import com.github.loadup.testify.common.config.CommonConfig;
import com.github.loadup.testify.core.config.CoreConfig;
import com.github.loadup.testify.data.config.DataConfig;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * Spring Boot Auto-Configuration for LoadUp Testify.
 * This class automatically configures all necessary beans for data-driven testing
 * when the starter is included in a project.
 *
 * <p>Usage: Add the starter dependency to your pom.xml:
 * <pre>
 * &lt;dependency&gt;
 *     &lt;groupId&gt;com.loadup&lt;/groupId&gt;
 *     &lt;artifactId&gt;loadup-testify-spring-boot-starter&lt;/artifactId&gt;
 *     &lt;version&gt;1.0.0-SNAPSHOT&lt;/version&gt;
 *     &lt;scope&gt;test&lt;/scope&gt;
 * &lt;/dependency&gt;
 * </pre>
 *
 * <p>The auto-configuration can be disabled by setting:
 * <pre>
 * loadup.testify.enabled=false
 * </pre>
 */
@AutoConfiguration
@EnableConfigurationProperties(TestifyProperties.class)
@ConditionalOnProperty(prefix = "loadup.testify", name = "enabled", havingValue = "true", matchIfMissing = true)
@Import({CommonConfig.class, DataConfig.class, CoreConfig.class})
public class TestifyAutoConfiguration {

    private final TestifyProperties properties;

    public TestifyAutoConfiguration(TestifyProperties properties) {
        this.properties = properties;
    }

    /**
     * Returns the configured default date tolerance for datetime comparisons.
     */
    public long getDefaultDateTolerance() {
        return properties.getDateTolerance();
    }

    /**
     * Returns whether verbose logging is enabled.
     */
    public boolean isVerboseLogging() {
        return properties.isVerboseLogging();
    }
}
