package com.github.loadup.testify.common.config;

import com.github.loadup.testify.common.variable.VariableResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration for the common module.
 */
@Configuration
public class CommonConfig {

    @Bean
    public VariableResolver variableResolver() {
        return new VariableResolver();
    }
}
