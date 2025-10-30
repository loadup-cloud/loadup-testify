package com.loadup.testify.example.test;

import com.loadup.testify.assertions.service.AssertionService;
import com.loadup.testify.core.context.TestContext;
import com.loadup.testify.core.model.TestCase;
import com.loadup.testify.core.model.TestSuite;
import com.loadup.testify.core.provider.TestConfig;
import com.loadup.testify.core.provider.TestifyDataProvider;
import com.loadup.testify.data.service.DatabaseInitService;
import com.loadup.testify.data.service.DatabaseVerificationService;
import com.loadup.testify.example.ExampleApplication;
import com.loadup.testify.example.dto.CreateOrderRequest;
import com.loadup.testify.example.entity.Order;
import com.loadup.testify.example.service.NotificationService;
import com.loadup.testify.example.service.OrderService;
import com.loadup.testify.mock.service.MockConfigService;
import com.loadup.testify.report.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Example test for OrderService using Testify framework
 */
@SpringBootTest(classes = ExampleApplication.class)
public class OrderServiceTest extends AbstractTestNGSpringContextTests {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceTest.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private DatabaseInitService databaseInitService;

    @Autowired
    private DatabaseVerificationService databaseVerificationService;

    @Autowired
    private MockConfigService mockConfigService;

    @Autowired
    private AssertionService assertionService;

    private ReportService reportService = new ReportService();

    @BeforeClass
    public void setup() {
        reportService.startTest();
    }

    @Test(dataProvider = "testifyProviderWithPath", dataProviderClass = TestifyDataProvider.class)
    @TestConfig("test-configs/OrderServiceTest/testCreateOrder.yaml")
    public void testCreateOrder(TestSuite testSuite, TestCase testCase) {
        executeTestCase(testSuite, testCase);
    }

    /**
     * Generic test case executor
     */
    private void executeTestCase(TestSuite testSuite, TestCase testCase) {
        long startTime = System.currentTimeMillis();
        TestContext context = TestContext.current();
        context.setCurrentTestCaseId(testCase.getId());

        try {
            log.info("Executing test case: {} - {}", testCase.getId(), testCase.getName());

            // Setup database
            if (testCase.getDatabaseSetup() != null) {
                databaseInitService.setup(testCase.getDatabaseSetup());
            }

            // Setup mocks
            Map<String, Object> beanMap = new HashMap<>();
            beanMap.put("notificationService", notificationService);

            if (testCase.getMocks() != null) {
                mockConfigService.setupMocks(testCase.getMocks(), beanMap);
            }

            // Execute test method
            Object result = null;
            Exception exception = null;

            try {
                // Parse inputs
                Map<String, Object> inputs = testCase.getInputs();
                CreateOrderRequest request = new CreateOrderRequest();

                Object userIdObj = context.resolveReference(inputs.get("userId"));
                request.setUserId(userIdObj instanceof Number ? ((Number) userIdObj).longValue() : Long.parseLong(userIdObj.toString()));

                Object amountObj = context.resolveReference(inputs.get("amount"));
                request.setAmount(new BigDecimal(amountObj.toString()));

                // Call service method
                result = orderService.createOrder(request);

            } catch (Exception e) {
                exception = e;
            }

            // Verify results
            if (exception != null) {
                assertionService.verifyException(exception, testCase.getExpected());
            } else {
                assertionService.verify(result, testCase.getExpected());
            }

            // Verify database
            if (testCase.getDatabaseVerification() != null) {
                databaseVerificationService.verify(testCase.getDatabaseVerification());
            }

            long executionTime = System.currentTimeMillis() - startTime;
            reportService.recordTestResult(testSuite, testCase, true, null, executionTime);

            log.info("Test case passed: {}", testCase.getId());

        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - startTime;
            reportService.recordTestResult(testSuite, testCase, false, e.getMessage(), executionTime);
            log.error("Test case failed: {}", testCase.getId(), e);
            throw e;

        } finally {
            // Cleanup database
            if (testCase.getDatabaseSetup() != null) {
                databaseInitService.cleanup(testCase.getDatabaseSetup());
            }

            // Clear context
            TestContext.clear();
        }
    }

    @AfterMethod
    public void cleanup() {
        mockConfigService.resetMocks();
    }
}

