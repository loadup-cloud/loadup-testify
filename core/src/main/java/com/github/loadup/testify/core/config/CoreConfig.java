package com.github.loadup.testify.core.config;

import com.github.loadup.testify.common.config.CommonConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring configuration for the core module.
 */
@Configuration
@Import(CommonConfig.class)
@ComponentScan(basePackages = {
        "com.github.loadup.testify.core",
        "com.github.loadup.testify.data",
        "com.github.loadup.testify.assertions"
})
public class CoreConfig {
}
