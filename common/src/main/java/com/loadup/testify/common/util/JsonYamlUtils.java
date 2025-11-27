package com.loadup.testify.common.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Utility class for JSON and YAML parsing operations.
 */
@Slf4j
public final class JsonYamlUtils {

    private static final ObjectMapper JSON_MAPPER;
    private static final ObjectMapper YAML_MAPPER;

    static {
        JSON_MAPPER = createObjectMapper();
        YAML_MAPPER = new ObjectMapper(new YAMLFactory());
        configureMapper(YAML_MAPPER);
    }

    private JsonYamlUtils() {
        // Utility class
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        configureMapper(mapper);
        return mapper;
    }

    private static void configureMapper(ObjectMapper mapper) {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }

    /**
     * Get the shared JSON ObjectMapper instance.
     *
     * @return the JSON ObjectMapper
     */
    public static ObjectMapper getJsonMapper() {
        return JSON_MAPPER;
    }

    /**
     * Get the shared YAML ObjectMapper instance.
     *
     * @return the YAML ObjectMapper
     */
    public static ObjectMapper getYamlMapper() {
        return YAML_MAPPER;
    }

    /**
     * Parse JSON string to an object of the specified type.
     *
     * @param json  the JSON string
     * @param clazz the target class
     * @param <T>   the target type
     * @return the parsed object
     */
    public static <T> T fromJson(String json, Class<T> clazz) throws IOException {
        return JSON_MAPPER.readValue(json, clazz);
    }

    /**
     * Convert an object to JSON string.
     *
     * @param object the object to convert
     * @return the JSON string
     */
    public static String toJson(Object object) throws IOException {
        return JSON_MAPPER.writeValueAsString(object);
    }

    /**
     * Parse YAML string to an object of the specified type.
     *
     * @param yaml  the YAML string
     * @param clazz the target class
     * @param <T>   the target type
     * @return the parsed object
     */
    public static <T> T fromYaml(String yaml, Class<T> clazz) throws IOException {
        return YAML_MAPPER.readValue(yaml, clazz);
    }

    /**
     * Parse YAML file to an object of the specified type.
     *
     * @param path  the path to the YAML file
     * @param clazz the target class
     * @param <T>   the target type
     * @return the parsed object
     */
    public static <T> T fromYamlFile(Path path, Class<T> clazz) throws IOException {
        try (InputStream is = Files.newInputStream(path)) {
            return YAML_MAPPER.readValue(is, clazz);
        }
    }

    /**
     * Parse YAML from InputStream to an object of the specified type.
     *
     * @param inputStream the input stream
     * @param clazz       the target class
     * @param <T>         the target type
     * @return the parsed object
     */
    public static <T> T fromYaml(InputStream inputStream, Class<T> clazz) throws IOException {
        return YAML_MAPPER.readValue(inputStream, clazz);
    }

    /**
     * Convert an object to YAML string.
     *
     * @param object the object to convert
     * @return the YAML string
     */
    public static String toYaml(Object object) throws IOException {
        return YAML_MAPPER.writeValueAsString(object);
    }

    /**
     * Convert an object to another type using JSON serialization.
     *
     * @param source      the source object
     * @param targetClass the target class
     * @param <T>         the target type
     * @return the converted object
     */
    public static <T> T convert(Object source, Class<T> targetClass) {
        return JSON_MAPPER.convertValue(source, targetClass);
    }
}
