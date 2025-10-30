
package com.loadup.testify.report.service;

import com.loadup.testify.core.model.TestCase;
import com.loadup.testify.core.model.TestSuite;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for generating test execution reports
 */
@Slf4j
@Service
public class ReportService {

    private final List<TestResult> testResults = new ArrayList<>();
    private LocalDateTime testStartTime;

    /**
     * Start test execution
     */
    public void startTest() {
        testStartTime = LocalDateTime.now();
        testResults.clear();
    }

    /**
     * Record test result
     */
    public void recordTestResult(TestSuite testSuite, TestCase testCase,
                                 boolean passed, String errorMessage, long executionTime) {
        TestResult result = new TestResult();
        result.setSuiteName(testSuite.getName());
        result.setTestCaseId(testCase.getId());
        result.setTestCaseName(testCase.getName());
        result.setPassed(passed);
        result.setErrorMessage(errorMessage);
        result.setExecutionTime(executionTime);
        result.setTimestamp(LocalDateTime.now());

        testResults.add(result);

        log.info("Test case {} - {}: {}", testCase.getId(), testCase.getName(),
                passed ? "PASSED" : "FAILED");

        if (!passed) {
            log.error("Error: {}", errorMessage);
        }
    }

    /**
     * Generate HTML report
     */
    public void generateReport(String outputPath) {
        log.info("Generating test report to: {}", outputPath);

        int passed = (int) testResults.stream().filter(TestResult::isPassed).count();
        int failed = testResults.size() - passed;
        double passRate = testResults.isEmpty() ? 0 : (passed * 100.0 / testResults.size());

        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n");
        html.append("<html>\n<head>\n");
        html.append("<meta charset='UTF-8'>\n");
        html.append("<title>Testify Test Report</title>\n");
        html.append("<style>\n");
        html.append("body { font-family: Arial, sans-serif; margin: 20px; }\n");
        html.append("h1 { color: #333; }\n");
        html.append(".summary { background: #f5f5f5; padding: 15px; border-radius: 5px; margin-bottom: 20px; }\n");
        html.append(".passed { color: green; font-weight: bold; }\n");
        html.append(".failed { color: red; font-weight: bold; }\n");
        html.append("table { border-collapse: collapse; width: 100%; }\n");
        html.append("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }\n");
        html.append("th { background-color: #4CAF50; color: white; }\n");
        html.append("tr:nth-child(even) { background-color: #f2f2f2; }\n");
        html.append(".error-message { color: red; font-size: 0.9em; }\n");
        html.append("</style>\n");
        html.append("</head>\n<body>\n");

        html.append("<h1>Testify Test Execution Report</h1>\n");

        // Summary
        html.append("<div class='summary'>\n");
        html.append("<h2>Summary</h2>\n");
        html.append("<p>Start Time: ").append(testStartTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)).append("</p>\n");
        html.append("<p>End Time: ").append(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)).append("</p>\n");
        html.append("<p>Total Tests: ").append(testResults.size()).append("</p>\n");
        html.append("<p class='passed'>Passed: ").append(passed).append("</p>\n");
        html.append("<p class='failed'>Failed: ").append(failed).append("</p>\n");
        html.append("<p>Pass Rate: ").append(String.format("%.2f", passRate)).append("%</p>\n");
        html.append("</div>\n");

        // Test results table
        html.append("<h2>Test Results</h2>\n");
        html.append("<table>\n");
        html.append("<tr><th>Test Suite</th><th>Test Case ID</th><th>Test Case Name</th>");
        html.append("<th>Status</th><th>Execution Time (ms)</th><th>Error Message</th></tr>\n");

        for (TestResult result : testResults) {
            html.append("<tr>\n");
            html.append("<td>").append(escapeHtml(result.getSuiteName())).append("</td>\n");
            html.append("<td>").append(escapeHtml(result.getTestCaseId())).append("</td>\n");
            html.append("<td>").append(escapeHtml(result.getTestCaseName())).append("</td>\n");
            html.append("<td class='").append(result.isPassed() ? "passed" : "failed").append("'>")
                    .append(result.isPassed() ? "PASSED" : "FAILED").append("</td>\n");
            html.append("<td>").append(result.getExecutionTime()).append("</td>\n");
            html.append("<td class='error-message'>")
                    .append(result.getErrorMessage() != null ? escapeHtml(result.getErrorMessage()) : "")
                    .append("</td>\n");
            html.append("</tr>\n");
        }

        html.append("</table>\n");
        html.append("</body>\n</html>");

        // Write to file
        try {
            File file = new File(outputPath);
            file.getParentFile().mkdirs();

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(html.toString());
            }

            log.info("Test report generated successfully");
        } catch (IOException e) {
            log.error("Failed to generate test report", e);
        }
    }

    /**
     * Escape HTML special characters
     */
    private String escapeHtml(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    /**
     * Get test results
     */
    public List<TestResult> getTestResults() {
        return new ArrayList<>(testResults);
    }

    /**
     * Test result data class
     */
    @Data
    public static class TestResult {
        private String suiteName;
        private String testCaseId;
        private String testCaseName;
        private boolean passed;
        private String errorMessage;
        private long executionTime;
        private LocalDateTime timestamp;
    }
}