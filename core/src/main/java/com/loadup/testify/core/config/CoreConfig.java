package com.loadup.testify.core.config;

import com.loadup.testify.common.config.CommonConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring configuration for the core module.
 */
@Configuration
@Import(CommonConfig.class)
@ComponentScan(basePackages = {
        "com.loadup.testify.core",
        "com.loadup.testify.data",
        "com.loadup.testify.assertions"
})
public class CoreConfig {
}
