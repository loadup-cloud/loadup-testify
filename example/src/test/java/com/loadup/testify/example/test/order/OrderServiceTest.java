package com.loadup.testify.example.test.order;

import com.loadup.testify.core.annotation.TestBean;
import com.loadup.testify.core.base.TestifyTestBase;
import com.loadup.testify.core.model.PrepareData;
import com.loadup.testify.example.config.ExampleApplication;
import com.loadup.testify.example.model.Order;
import com.loadup.testify.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testng.annotations.Test;

import java.math.BigDecimal;

/**
 * Example test class demonstrating Testify data-driven testing for OrderService.
 * 
 * <p>This test class demonstrates:</p>
 * <ul>
 *   <li><b>JSON field comparison</b> - The extraInfo field contains JSON data that is
 *       compared semantically (ignoring key order and whitespace)</li>
 *   <li><b>DateTime tolerance</b> - The createdAt/updatedAt fields support tolerance
 *       comparison for time differences</li>
 *   <li><b>Complex parameter handling</b> - Multiple parameters with different types</li>
 * </ul>
 * 
 * <p>Directory structure:</p>
 * <pre>
 * com/loadup/testify/example/test/order/
 * ├── OrderServiceTest.java                          (this test class)
 * ├── OrderService.createOrder/                      (method directory)
 * │   └── case01/                                    (case directory)
 * │       ├── test_config.yaml
 * │       ├── PrepareData/
 * │       │   └── table_orders.csv
 * │       └── ExpectedData/
 * │           └── table_orders.csv
 * └── OrderService.createOrderWithExtraInfo/
 *     └── case01/
 *         ├── test_config.yaml
 *         ├── PrepareData/
 *         │   └── table_orders.csv
 *         └── ExpectedData/
 *             └── table_orders.csv
 * </pre>
 * 
 * <p>JSON Field Comparison Example:</p>
 * <pre>
 * # In test_config.yaml expected section:
 * expected:
 *   - field: "response.extraInfo"
 *     operator: "JSON_EQUALS"
 *     expectedValue: '{"shippingAddress": "123 Main St", "notes": "Leave at door"}'
 * </pre>
 * 
 * <p>DateTime Tolerance Example:</p>
 * <pre>
 * # In test_config.yaml expected section:
 * expected:
 *   - field: "response.createdAt"
 *     operator: "DATETIME_TOLERANCE"
 *     dateTolerance: 5000  # Allow 5 seconds difference
 * </pre>
 */
@SpringBootTest(classes = ExampleApplication.class)
@ActiveProfiles("test")
public class OrderServiceTest extends TestifyTestBase {

    /**
     * The service under test, marked with @TestBean annotation.
     * The service name "OrderService" is derived from this field's type.
     */
    @TestBean
    @Autowired
    private OrderService orderService;

    /**
     * Test creating a basic order with data-driven test cases.
     * 
     * <p>Method name "testCreateOrder" → "createOrder"</p>
     * <p>Test data is loaded from: OrderService.createOrder/case01/</p>
     */
    @Test(dataProvider = "TestifyProvider")
    public void testCreateOrder(String caseId, PrepareData prepareData) {
        runTest(caseId, prepareData);
    }

    /**
     * Test creating an order with extra info JSON field.
     * Demonstrates JSON String field handling for large objects.
     * 
     * <p>Method name "testCreateOrderWithExtraInfo" → "createOrderWithExtraInfo"</p>
     * <p>Test data is loaded from: OrderService.createOrderWithExtraInfo/case01/</p>
     */
    @Test(dataProvider = "TestifyProvider")
    public void testCreateOrderWithExtraInfo(String caseId, PrepareData prepareData) {
        runTest(caseId, prepareData);
    }

    /**
     * Create a new order - method invoked by the test execution engine.
     */
    public Order createOrder(Order order) {
        return orderService.createOrder(order);
    }

    /**
     * Create an order with extra info - demonstrates JSON field and multiple parameters.
     * 
     * @param userId the user ID
     * @param orderNumber the order number
     * @param totalAmount the total amount
     * @param extraInfo the extra info as JSON string
     * @return the created order
     */
    public Order createOrderWithExtraInfo(Long userId, String orderNumber, BigDecimal totalAmount, String extraInfo) {
        return orderService.createOrderWithExtraInfo(userId, orderNumber, totalAmount, extraInfo);
    }

    /**
     * Find an order by order number.
     */
    public Order findByOrderNumber(String orderNumber) {
        return orderService.findByOrderNumber(orderNumber).orElse(null);
    }
}
