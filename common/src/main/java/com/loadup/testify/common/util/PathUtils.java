package com.loadup.testify.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
     * The data directory is located in the same package as the test class,
     * in src/test/java (alongside the test class file itself).
     *
     * @param testClass the test class
     * @return the path to the test data directory
     */
    public static Path getTestDataDirectory(Class<?> testClass) {
        String packagePathStr = testClass.getPackage().getName().replace('.', '/');
        
        // First, try to find the source file location by using the class location
        URL classResource = testClass.getResource(testClass.getSimpleName() + ".class");
        if (classResource != null) {
            try {
                URI uri = classResource.toURI();
                Path classPath = Paths.get(uri);
                
                // Navigate from target/test-classes/... back to src/test/java/...
                // Use Path-based manipulation for cross-platform compatibility
                Path current = classPath.getParent(); // Remove the .class file
                Path projectRoot = null;
                
                // Walk up to find target/test-classes and get the parent
                while (current != null) {
                    if (current.getFileName() != null && 
                        "test-classes".equals(current.getFileName().toString())) {
                        Path targetDir = current.getParent();
                        if (targetDir != null && targetDir.getFileName() != null &&
                            "target".equals(targetDir.getFileName().toString())) {
                            projectRoot = targetDir.getParent();
                            break;
                        }
                    }
                    current = current.getParent();
                }
                
                if (projectRoot != null) {
                    // Reconstruct the path under src/test/java
                    Path srcTestJava = projectRoot.resolve("src").resolve("test").resolve("java");
                    Path packageDir = srcTestJava.resolve(
                            testClass.getPackage().getName().replace('.', File.separatorChar));
                    if (Files.exists(packageDir)) {
                        log.debug("Test data directory resolved from source: {}", packageDir);
                        return packageDir;
                    }
                }
            } catch (URISyntaxException e) {
                log.warn("Failed to convert URL to URI: {}", classResource, e);
            }
        }
        
        // Fall back to src/test/java
        Path fallbackPath = Paths.get("src/test/java", packagePathStr);
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
