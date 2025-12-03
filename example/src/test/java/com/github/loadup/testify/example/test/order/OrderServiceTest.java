package com.github.loadup.testify.example.test.order;

/*-
 * #%L
 * LoadUp Testify - Example
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

import com.github.loadup.testify.core.annotation.TestBean;
import com.github.loadup.testify.core.base.TestifyTestBase;
import com.github.loadup.testify.core.model.PrepareData;
import com.github.loadup.testify.example.config.ExampleApplication;
import com.github.loadup.testify.example.model.Order;
import com.github.loadup.testify.example.service.OrderService;
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
