package com.loadup.testify.example.config;

import com.loadup.testify.starter.autoconfigure.TestifyAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Spring Boot application configuration for the example module.
 */
@SpringBootApplication(scanBasePackages = "com.loadup.testify.example")
@Import(TestifyAutoConfiguration.class)
public class ExampleApplication {
}
