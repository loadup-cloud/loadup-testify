package com.github.loadup.testify.common.util;

/*-
 * #%L
 * LoadUp Testify - Common
 * %%
 * Copyright (C) 2026 LoadUp Cloud
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Utility class for file path operations.
 * 
 * <p>Directory structure convention:</p>
 * <pre>
 * |- UserServiceTest.java           (test class)
 * ├── UserService.createUser/       (method directory: ServiceName.methodName)
 * │   ├── case01/                   (case directory)
 * │   │   ├── test_config.yaml      (test configuration)
 * │   │   ├── PrepareData/          (CSV files for database setup)
 * │   │   └── ExpectedData/         (CSV files for database assertions)
 * │   └── case02/
 * ├── UserService.createUserWithRole/
 * │   ├── case01/
 * │   └── case02/
 * </pre>
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
                    // Reconstruct the path under src/test/java using Path.resolve() for platform independence
                    Path srcTestJava = projectRoot.resolve("src").resolve("test").resolve("java");
                    // Split package name and resolve each component separately
                    Path packageDir = srcTestJava;
                    for (String component : testClass.getPackage().getName().split("\\.")) {
                        packageDir = packageDir.resolve(component);
                    }
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
     * Get the method directory path for a specific service and method.
     * Format: {serviceName}.{methodName}/
     *
     * @param testClass   the test class
     * @param serviceName the service name (e.g., "UserService")
     * @param methodName  the method name (e.g., "createUser")
     * @return the path to the method directory
     */
    public static Path getMethodDirectory(Class<?> testClass, String serviceName, String methodName) {
        String methodDirName = serviceName + "." + methodName;
        return getTestDataDirectory(testClass).resolve(methodDirName);
    }

    /**
     * Get the case directory path for a specific test case.
     * Format: {serviceName}.{methodName}/{caseId}/
     *
     * @param testClass   the test class
     * @param serviceName the service name (e.g., "UserService")
     * @param methodName  the method name (e.g., "createUser")
     * @param caseId      the case ID (e.g., "case01")
     * @return the path to the case directory
     */
    public static Path getCaseDirectory(Class<?> testClass, String serviceName, String methodName, String caseId) {
        return getMethodDirectory(testClass, serviceName, methodName).resolve(caseId);
    }

    /**
     * Get the PrepareData directory path for a specific test case.
     *
     * @param testClass   the test class
     * @param serviceName the service name
     * @param methodName  the method name
     * @param caseId      the case ID
     * @return the path to the PrepareData directory
     */
    public static Path getPrepareDataDirectory(Class<?> testClass, String serviceName, String methodName, String caseId) {
        return getCaseDirectory(testClass, serviceName, methodName, caseId).resolve("PrepareData");
    }

    /**
     * Get the ExpectedData directory path for a specific test case.
     *
     * @param testClass   the test class
     * @param serviceName the service name
     * @param methodName  the method name
     * @param caseId      the case ID
     * @return the path to the ExpectedData directory
     */
    public static Path getExpectedDataDirectory(Class<?> testClass, String serviceName, String methodName, String caseId) {
        return getCaseDirectory(testClass, serviceName, methodName, caseId).resolve("ExpectedData");
    }

    /**
     * Get the test configuration file path for a specific test case.
     *
     * @param testClass   the test class
     * @param serviceName the service name
     * @param methodName  the method name
     * @param caseId      the case ID
     * @return the path to the test_config.yaml file
     */
    public static Path getTestConfigPath(Class<?> testClass, String serviceName, String methodName, String caseId) {
        return getCaseDirectory(testClass, serviceName, methodName, caseId).resolve("test_config.yaml");
    }

    /**
     * List all method directories in the test data directory.
     * Method directories follow the pattern: {ServiceName}.{methodName}
     *
     * @param testClass the test class
     * @return list of method directory names (e.g., ["UserService.createUser", "UserService.createUserWithRole"])
     */
    public static List<String> listMethodDirectories(Class<?> testClass) {
        Path dataDir = getTestDataDirectory(testClass);
        List<String> methodDirs = new ArrayList<>();
        
        if (!Files.exists(dataDir)) {
            return methodDirs;
        }
        
        try (Stream<Path> paths = Files.list(dataDir)) {
            paths.filter(Files::isDirectory)
                 .filter(p -> {
                     String name = p.getFileName().toString();
                     // Must contain a dot to be a method directory (ServiceName.methodName)
                     return name.contains(".");
                 })
                 .forEach(p -> methodDirs.add(p.getFileName().toString()));
        } catch (IOException e) {
            log.warn("Failed to list method directories in: {}", dataDir, e);
        }
        
        return methodDirs;
    }

    /**
     * List all case directories for a specific method.
     *
     * @param testClass   the test class
     * @param serviceName the service name
     * @param methodName  the method name
     * @return list of case IDs (e.g., ["case01", "case02"])
     */
    public static List<String> listCaseDirectories(Class<?> testClass, String serviceName, String methodName) {
        Path methodDir = getMethodDirectory(testClass, serviceName, methodName);
        List<String> caseIds = new ArrayList<>();
        
        if (!Files.exists(methodDir)) {
            return caseIds;
        }
        
        try (Stream<Path> paths = Files.list(methodDir)) {
            paths.filter(Files::isDirectory)
                 .forEach(p -> caseIds.add(p.getFileName().toString()));
        } catch (IOException e) {
            log.warn("Failed to list case directories in: {}", methodDir, e);
        }
        
        return caseIds;
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
