package com.loadup.testify.report.listener;

import com.loadup.testify.report.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.testng.*;

/**
 * TestNG listener for capturing test execution events
 */
@Slf4j
public class TestifyReportListener implements ITestListener, ISuiteListener {

    private final ReportService reportService = new ReportService();

    @Override
    public void onStart(ISuite suite) {
        log.info("Test suite started: {}", suite.getName());
        reportService.startTest();
    }

    @Override
    public void onFinish(ISuite suite) {
        log.info("Test suite finished: {}", suite.getName());

        // Generate report
        String outputPath = System.getProperty("testify.report.path", "target/testify-reports/report.html");
        reportService.generateReport(outputPath);
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test started: {}.{}", result.getTestClass().getName(), result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test passed: {}.{}", result.getTestClass().getName(), result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test failed: {}.{}", result.getTestClass().getName(), result.getMethod().getMethodName());
        if (result.getThrowable() != null) {
            log.error("Error: ", result.getThrowable());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("Test skipped: {}.{}", result.getTestClass().getName(), result.getMethod().getMethodName());
    }
}

