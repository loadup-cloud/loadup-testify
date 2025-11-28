package com.github.loadup.testify.example.config;

import com.github.loadup.testify.starter.autoconfigure.TestifyAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Spring Boot application configuration for the example module.
 */
@SpringBootApplication(scanBasePackages = "com.github.loadup.testify.example")
@Import(TestifyAutoConfiguration.class)
public class ExampleApplication {
}
