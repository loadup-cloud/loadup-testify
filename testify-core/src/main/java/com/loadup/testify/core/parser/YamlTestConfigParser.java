package com.loadup.testify.core.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.loadup.testify.core.model.TestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Parser for YAML test configuration files
 */
public class YamlTestConfigParser {

    private static final Logger log = LoggerFactory.getLogger(YamlTestConfigParser.class);
    private final ObjectMapper objectMapper;

    public YamlTestConfigParser() {
        this.objectMapper = new ObjectMapper(new YAMLFactory());
        this.objectMapper.findAndRegisterModules();
    }

    /**
     * Parse YAML file from classpath
     */
    public TestSuite parseFromClasspath(String resourcePath) {
        log.info("Parsing test configuration from classpath: {}", resourcePath);
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IllegalArgumentException("Resource not found: " + resourcePath);
            }
            return objectMapper.readValue(is, TestSuite.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse YAML configuration: " + resourcePath, e);
        }
    }

    /**
     * Parse YAML file from file system
     */
    public TestSuite parseFromFile(String filePath) {
        log.info("Parsing test configuration from file: {}", filePath);
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new IllegalArgumentException("File not found: " + filePath);
            }
            return objectMapper.readValue(file, TestSuite.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse YAML configuration: " + filePath, e);
        }
    }

    /**
     * Parse YAML string
     */
    public TestSuite parseFromString(String yamlContent) {
        log.info("Parsing test configuration from string");
        try {
            return objectMapper.readValue(yamlContent, TestSuite.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse YAML configuration", e);
        }
    }
}

