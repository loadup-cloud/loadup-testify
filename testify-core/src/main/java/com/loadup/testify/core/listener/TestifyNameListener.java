package com.loadup.testify.core.listener;

import com.loadup.testify.core.model.TestCase;
import com.loadup.testify.core.model.TestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

/**
 * TestNG Listener to set readable test names for IDE display
 */
public class TestifyNameListener implements IInvokedMethodListener {

    private static final Logger log = LoggerFactory.getLogger(TestifyNameListener.class);

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            // Try to extract TestCase from parameters
            Object[] parameters = testResult.getParameters();
            if (parameters != null && parameters.length >= 2) {
                if (parameters[0] instanceof TestSuite && parameters[1] instanceof TestCase) {
                    TestCase testCase = (TestCase) parameters[1];

                    // Set readable test name
                    String readableName = String.format("[%s] %s", testCase.getId(), testCase.getName());
                    testResult.setTestName(readableName);

                    log.debug("Set test name to: {}", readableName);
                }
            }
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // No action needed after invocation
    }
}

