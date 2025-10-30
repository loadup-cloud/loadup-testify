package com.loadup.testify.core.provider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to specify custom test configuration path
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestConfig {
    /**
     * Path to YAML configuration file (classpath resource)
     */
    String value();
}

