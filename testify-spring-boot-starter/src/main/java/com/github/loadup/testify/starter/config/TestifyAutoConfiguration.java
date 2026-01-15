package com.github.loadup.testify.starter.config;

import com.github.loadup.testify.starter.container.TestifyContainerManager;
import com.github.loadup.testify.starter.db.DbConnectionProvider;
import com.github.loadup.testify.starter.db.PhysicalDbConnectionProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Auto-configuration for Testify framework.
 * Configures Testcontainers or physical database based on properties.
 */
@Configuration
@EnableConfigurationProperties(TestifyProperties.class)
public class TestifyAutoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "testify.containers", name = "enabled", havingValue = "true")

    public TestifyContainerManager containerManager() {
        // Initialize Testcontainers
        return new TestifyContainerManager();
    }

    @Bean
    @ConditionalOnMissingBean
    public DbConnectionProvider physicalDbProvider() {
        // Default: use physical database from properties
        return new PhysicalDbConnectionProvider();
    }
}