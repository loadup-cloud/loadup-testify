package com.github.loadup.testify.common.util;

import com.github.loadup.testify.common.exception.DataLoadingException;
import com.github.loadup.testify.common.variable.VariableResolver;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Utility class for CSV file operations.
 */
@Slf4j
public final class CsvUtils {

    private CsvUtils() {
        // Utility class
    }

    /**
     * Read a CSV file and return the data as a list of maps.
     * The first row is treated as headers.
     *
     * @param path             the path to the CSV file
     * @param variableResolver the variable resolver for resolving placeholders
     * @param captureVariables whether to capture Datafaker-generated values to the pool
     * @return a list of maps, where each map represents a row
     */
    public static List<Map<String, String>> readCsv(Path path, VariableResolver variableResolver, boolean captureVariables) {
        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
             CSVReader csvReader = new CSVReaderBuilder(reader).build()) {

            List<String[]> allRows = csvReader.readAll();
            if (allRows.isEmpty()) {
                return Collections.emptyList();
            }

            String[] headers = allRows.get(0);
            List<Map<String, String>> result = new ArrayList<>();

            for (int i = 1; i < allRows.size(); i++) {
                String[] row = allRows.get(i);
                Map<String, String> rowMap = new LinkedHashMap<>();

                for (int j = 0; j < headers.length && j < row.length; j++) {
                    String value = row[j];
                    if (variableResolver != null && value != null) {
                        value = variableResolver.resolve(value, captureVariables);
                    }
                    rowMap.put(headers[j], value);
                }

                result.add(rowMap);
            }

            return result;
        } catch (IOException | CsvException e) {
            throw new DataLoadingException("Failed to read CSV file: " + path, e);
        }
    }

    /**
     * Read a CSV file without variable resolution.
     *
     * @param path the path to the CSV file
     * @return a list of maps, where each map represents a row
     */
    public static List<Map<String, String>> readCsv(Path path) {
        return readCsv(path, null, false);
    }

    /**
     * Get the headers from a CSV file.
     *
     * @param path the path to the CSV file
     * @return an array of header names
     */
    public static String[] getHeaders(Path path) {
        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
             CSVReader csvReader = new CSVReaderBuilder(reader).build()) {

            String[] headers = csvReader.readNext();
            return headers != null ? headers : new String[0];
        } catch (IOException | CsvException e) {
            throw new DataLoadingException("Failed to read CSV headers: " + path, e);
        }
    }

    /**
     * Write data to a CSV file.
     *
     * @param path   the path to the CSV file
     * @param data   the data to write (list of maps)
     * @param headers the column headers (if null, uses keys from the first row)
     */
    public static void writeCsv(Path path, List<Map<String, String>> data, String[] headers) {
        if (data == null || data.isEmpty()) {
            return;
        }

        String[] actualHeaders = headers;
        if (actualHeaders == null || actualHeaders.length == 0) {
            actualHeaders = data.get(0).keySet().toArray(new String[0]);
        }

        try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
             CSVWriter csvWriter = new CSVWriter(writer)) {

            csvWriter.writeNext(actualHeaders);

            for (Map<String, String> row : data) {
                String[] values = new String[actualHeaders.length];
                for (int i = 0; i < actualHeaders.length; i++) {
                    values[i] = row.getOrDefault(actualHeaders[i], "");
                }
                csvWriter.writeNext(values);
            }
        } catch (IOException e) {
            throw new DataLoadingException("Failed to write CSV file: " + path, e);
        }
    }
}
