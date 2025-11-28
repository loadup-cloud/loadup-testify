package com.loadup.testify.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Utility class for file path operations.
 */
@Slf4j
public final class PathUtils {

    private PathUtils() {
        // Utility class
    }

    /**
     * Get the test data directory path for a test class.
     * The data directory is located in the same package as the test class.
     *
     * @param testClass the test class
     * @return the path to the test data directory
     */
    public static Path getTestDataDirectory(Class<?> testClass) {
        String packagePath = testClass.getPackage().getName().replace('.', '/');
        URL resource = testClass.getClassLoader().getResource(packagePath);
        
        if (resource != null) {
            try {
                // Convert URL to URI first to properly handle URL-encoded characters
                URI uri = resource.toURI();
                Path path = Paths.get(uri);
                log.debug("Test data directory resolved from classpath: {}", path);
                return path;
            } catch (URISyntaxException e) {
                log.warn("Failed to convert URL to URI: {}, falling back to getPath()", resource, e);
                return Paths.get(resource.getPath());
            }
        }
        
        // Fall back to src/test/resources
        Path fallbackPath = Paths.get("src/test/resources", packagePath);
        log.debug("Test data directory fallback: {}", fallbackPath);
        return fallbackPath;
    }

    /**
     * Get the case directory path for a specific test case.
     *
     * @param testClass the test class
     * @param caseId    the case ID
     * @return the path to the case directory
     */
    public static Path getCaseDirectory(Class<?> testClass, String caseId) {
        return getTestDataDirectory(testClass).resolve(caseId);
    }

    /**
     * Get the PrepareData directory path for a specific test case.
     *
     * @param testClass the test class
     * @param caseId    the case ID
     * @return the path to the PrepareData directory
     */
    public static Path getPrepareDataDirectory(Class<?> testClass, String caseId) {
        return getCaseDirectory(testClass, caseId).resolve("PrepareData");
    }

    /**
     * Get the ExpectedData directory path for a specific test case.
     *
     * @param testClass the test class
     * @param caseId    the case ID
     * @return the path to the ExpectedData directory
     */
    public static Path getExpectedDataDirectory(Class<?> testClass, String caseId) {
        return getCaseDirectory(testClass, caseId).resolve("ExpectedData");
    }

    /**
     * Get the test configuration file path for a specific test case.
     *
     * @param testClass the test class
     * @param caseId    the case ID
     * @return the path to the test_config.yaml file
     */
    public static Path getTestConfigPath(Class<?> testClass, String caseId) {
        return getCaseDirectory(testClass, caseId).resolve("test_config.yaml");
    }

    /**
     * Check if a file exists at the given path.
     *
     * @param path the path to check
     * @return true if the file exists
     */
    public static boolean exists(Path path) {
        return Files.exists(path);
    }

    /**
     * Get all CSV files in a directory.
     *
     * @param directory the directory to search
     * @return an array of CSV file paths
     */
    public static Path[] getCsvFiles(Path directory) {
        if (!Files.exists(directory) || !Files.isDirectory(directory)) {
            return new Path[0];
        }

        File dir = directory.toFile();
        File[] csvFiles = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".csv"));
        
        if (csvFiles == null) {
            return new Path[0];
        }

        Path[] result = new Path[csvFiles.length];
        for (int i = 0; i < csvFiles.length; i++) {
            result[i] = csvFiles[i].toPath();
        }
        return result;
    }

    /**
     * Extract the table name from a CSV file path.
     * Assumes the file name follows the pattern: table_name.csv or table_name_suffix.csv
     *
     * @param csvPath the path to the CSV file
     * @return the extracted table name
     */
    public static String extractTableName(Path csvPath) {
        String fileName = csvPath.getFileName().toString();
        // Remove .csv extension
        if (fileName.toLowerCase().endsWith(".csv")) {
            fileName = fileName.substring(0, fileName.length() - 4);
        }
        // Remove table_ prefix if present
        if (fileName.toLowerCase().startsWith("table_")) {
            fileName = fileName.substring(6);
        }
        return fileName;
    }
}
