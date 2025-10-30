# Testify Framework - Complete Usage Guide

## Table of Contents
1. [Introduction](#introduction)
2. [Installation](#installation)
3. [Core Concepts](#core-concepts)
4. [Writing Your First Test](#writing-your-first-test)
5. [Database Management](#database-management)
6. [Mock Configuration](#mock-configuration)
7. [Assertions](#assertions)
8. [Database Verification](#database-verification)
9. [Advanced Patterns](#advanced-patterns)
10. [Troubleshooting](#troubleshooting)

---

## Introduction

Testify is a configuration-driven testing framework that allows you to write comprehensive integration tests using YAML configuration files instead of writing verbose test code. This guide will walk you through all features and best practices.

## Installation

### Maven Configuration

Add the following dependencies to your `pom.xml`:

```xml
<dependencies>
    <!-- Testify Core -->
    <dependency>
         <groupId>com.github.loadup</groupId>
        <artifactId>testify-core</artifactId>
        <version>1.0.0-SNAPSHOT</version>
        <scope>test</scope>
    </dependency>
    
    <!-- Testify Data (for database operations) -->
    <dependency>
         <groupId>com.github.loadup</groupId>
        <artifactId>testify-data</artifactId>
        <version>1.0.0-SNAPSHOT</version>
        <scope>test</scope>
    </dependency>
    
    <!-- Testify Mock (for mocking) -->
    <dependency>
         <groupId>com.github.loadup</groupId>
        <artifactId>testify-mock</artifactId>
        <version>1.0.0-SNAPSHOT</version>
        <scope>test</scope>
    </dependency>
    
    <!-- Testify Assertions -->
    <dependency>
         <groupId>com.github.loadup</groupId>
        <artifactId>testify-assertions</artifactId>
        <version>1.0.0-SNAPSHOT</version>
        <scope>test</scope>
    </dependency>
    
    <!-- Testify Report -->
    <dependency>
         <groupId>com.github.loadup</groupId>
        <artifactId>testify-report</artifactId>
        <version>1.0.0-SNAPSHOT</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### Project Setup

1. Create the directory structure for test configurations:
```bash
mkdir -p src/test/resources/test-configs
```

2. Create a TestNG configuration file at `src/test/resources/testng.xml`:
```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="My Test Suite">
    <listeners>
        <listener class-name="com.loadup.testify.report.listener.TestifyReportListener"/>
    </listeners>
    <test name="My Tests">
        <classes>
            <class name="com.example.MyServiceTest"/>
        </classes>
    </test>
</suite>
```

## Core Concepts

### 1. Test Suite
A test suite represents a collection of test cases for a specific service method. Each YAML file typically contains one test suite.

### 2. Test Case
A test case is a single test scenario with its own setup, inputs, and expected results.

### 3. Reference System
The reference system (`${refId}`) allows you to capture auto-generated IDs from database inserts and use them in other parts of your test.

### 4. Test Context
Thread-local storage that maintains references and variables throughout test execution.

## Writing Your First Test

### Step 1: Create Service to Test

```java
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User createUser(String username, String email) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        return userRepository.save(user);
    }
}
```

### Step 2: Create YAML Configuration

Create `src/test/resources/test-configs/UserServiceTest/testCreateUser.yaml`:

```yaml
name: User Creation Tests
targetClass: com.example.service.UserService
targetMethod: createUser

testCases:
  - id: TC001
    name: Create user with valid data
    
    databaseSetup:
      cleanBefore: true
      cleanAfter: true
      truncateTables:
        - users
    
    inputs:
      username: "john_doe"
      email: "john@example.com"
    
    expected:
      assertions:
        - field: username
          type: EQUALS
          value: "john_doe"
        - field: id
          type: NOT_NULL
```

### Step 3: Create Test Class

```java
@SpringBootTest
public class UserServiceTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private DatabaseInitService databaseInitService;
    
    @Autowired
    private AssertionService assertionService;
    
    @Test(dataProvider = "testifyProviderWithPath", 
          dataProviderClass = TestifyDataProvider.class)
    @TestConfig("test-configs/UserServiceTest/testCreateUser.yaml")
    public void testCreateUser(TestSuite testSuite, TestCase testCase) {
        TestContext context = TestContext.current();
        
        try {
            // Setup database
            databaseInitService.setup(testCase.getDatabaseSetup());
            
            // Execute test
            Map<String, Object> inputs = testCase.getInputs();
            User result = userService.createUser(
                (String) inputs.get("username"),
                (String) inputs.get("email")
            );
            
            // Verify results
            assertionService.verify(result, testCase.getExpected());
            
        } finally {
            databaseInitService.cleanup(testCase.getDatabaseSetup());
            TestContext.clear();
        }
    }
}
```

## Database Management

### Cleaning Tables

Clean tables before running tests:

```yaml
databaseSetup:
  cleanBefore: true
  cleanAfter: true
  truncateTables:
    - users
    - orders
    - products
```

### Inserting Test Data

Insert data with reference IDs:

```yaml
databaseSetup:
  data:
    users:
      - refId: adminUser
        columns:
          username: "admin"
          email: "admin@example.com"
          role: "ADMIN"
          created_at: "2024-01-01T10:00:00"
      
      - refId: regularUser
        columns:
          username: "john"
          email: "john@example.com"
          role: "USER"
```

### Using References

Use generated IDs in other tables:

```yaml
databaseSetup:
  data:
    users:
      - refId: user1
        columns:
          username: "john"
    
    orders:
      - refId: order1
        columns:
          user_id: ${user1}      # References user1's generated ID
          amount: 99.99
          status: "PENDING"
      
      - refId: order2
        columns:
          user_id: ${user1}      # Same user
          amount: 149.99
          status: "SHIPPED"
```

### Complex Data Setup

Set up related data across multiple tables:

```yaml
databaseSetup:
  cleanBefore: true
  truncateTables:
    - users
    - addresses
    - orders
    - order_items
  
  data:
    users:
      - refId: customer1
        columns:
          username: "customer1"
          email: "customer1@example.com"
    
    addresses:
      - refId: addr1
        columns:
          user_id: ${customer1}
          street: "123 Main St"
          city: "New York"
          country: "USA"
    
    orders:
      - refId: order1
        columns:
          user_id: ${customer1}
          shipping_address_id: ${addr1}
          total_amount: 299.99
    
    order_items:
      - columns:
          order_id: ${order1}
          product_id: 1001
          quantity: 2
          price: 149.99
```

## Mock Configuration

### Basic Mocking

Mock external service to return specific value:

```yaml
mocks:
  - target: emailService
    method: sendEmail
    behavior: RETURN
    returnValue: true
```

### Mock to Throw Exception

```yaml
mocks:
  - target: paymentGateway
    method: processPayment
    behavior: THROW
    throwException: com.example.PaymentException
    exceptionMessage: "Payment processing failed"
```

### Mock Void Methods

```yaml
mocks:
  - target: logService
    method: logActivity
    behavior: DO_NOTHING
```

### Global Mocks

Apply mocks to all test cases:

```yaml
globalMocks:
  - target: notificationService
    method: sendNotification
    behavior: DO_NOTHING
  
  - target: auditService
    method: logAudit
    behavior: DO_NOTHING

testCases:
  - id: TC001
    # Global mocks are automatically applied
```

### Per-Test Mocks

Override or add mocks for specific test cases:

```yaml
testCases:
  - id: TC001
    name: Test with specific mock
    
    mocks:
      - target: priceCalculator
        method: calculateDiscount
        behavior: RETURN
        returnValue: 0.15  # 15% discount
```

## Assertions

### Field-Level Assertions

Verify specific fields in result object:

```yaml
expected:
  assertions:
    - field: id
      type: NOT_NULL
    
    - field: username
      type: EQUALS
      value: "john_doe"
    
    - field: email
      type: CONTAINS
      value: "@example.com"
    
    - field: age
      type: GREATER_THAN
      value: 18
```

### Nested Field Access

Access nested object properties:

```yaml
expected:
  assertions:
    - field: user.address.city
      type: EQUALS
      value: "New York"
    
    - field: order.items.0.productName
      type: STARTS_WITH
      value: "Premium"
```

### Collection Assertions

```yaml
expected:
  assertions:
    - field: tags
      type: HAS_SIZE
      value: 3
    
    - field: roles
      type: COLLECTION_CONTAINS
      value: "ADMIN"
    
    - field: items
      type: IS_NOT_EMPTY
```

### String Assertions

```yaml
expected:
  assertions:
    - field: orderNumber
      type: STARTS_WITH
      value: "ORD-"
    
    - field: description
      type: CONTAINS
      value: "premium"
    
    - field: email
      type: MATCHES_REGEX
      value: "^[A-Za-z0-9+_.-]+@(.+)$"
```

### Numeric Assertions

```yaml
expected:
  assertions:
    - field: price
      type: GREATER_THAN_OR_EQUALS
      value: 0
    
    - field: discount
      type: LESS_THAN
      value: 1.0
    
    - field: quantity
      type: NOT_EQUALS
      value: 0
```

### Exception Assertions

Verify that expected exception is thrown:

```yaml
testCases:
  - id: TC_ERROR_001
    name: Should throw exception for invalid input
    
    inputs:
      userId: -1
    
    expected:
      exception: IllegalArgumentException
```

## Database Verification

### Verify Row Count

```yaml
databaseVerification:
  tables:
    - table: orders
      expectedCount: 3
```

### Verify with WHERE Clause

```yaml
databaseVerification:
  tables:
    - table: orders
      where:
        user_id: ${testUser}
        status: "PENDING"
      expectedCount: 2
```

### Column Verification Rules

```yaml
databaseVerification:
  tables:
    - table: users
      where:
        username: "john_doe"
      columns:
        # Exact match
        - name: email
          rule: EQUALS
          value: "john@example.com"
        
        # Not null check
        - name: created_at
          rule: NOT_NULL
        
        # Numeric comparison
        - name: age
          rule: GREATER_THAN
          value: 18
        
        # String operations
        - name: full_name
          rule: CONTAINS
          value: "John"
        
        # Timestamp verification with tolerance
        - name: last_login
          rule: TIME_CLOSE_TO
          value: "now"
          toleranceSeconds: 10
        
        # Pattern matching
        - name: phone
          rule: REGEX_MATCH
          value: "^\\+?[1-9]\\d{1,14}$"
```

### Multiple Table Verification

```yaml
databaseVerification:
  tables:
    - table: users
      expectedCount: 1
      where:
        username: "john"
      columns:
        - name: email
          rule: EQUALS
          value: "john@example.com"
    
    - table: orders
      expectedCount: 2
      where:
        user_id: ${testUser}
      columns:
        - name: status
          rule: IN_LIST
          value: ["PENDING", "CONFIRMED"]
```

## Advanced Patterns

### Pattern 1: Multi-Step Test Flow

Test a complex workflow with multiple database states:

```yaml
testCases:
  - id: TC_WORKFLOW_001
    name: Complete order workflow
    
    databaseSetup:
      cleanBefore: true
      data:
        users:
          - refId: buyer
            columns:
              username: "buyer1"
              balance: 1000.00
        
        products:
          - refId: product1
            columns:
              name: "Laptop"
              price: 899.99
              stock: 10
    
    inputs:
      userId: ${buyer}
      productId: ${product1}
      quantity: 1
    
    expected:
      assertions:
        - field: status
          type: EQUALS
          value: "CONFIRMED"
    
    databaseVerification:
      tables:
        # Verify user balance deducted
        - table: users
          where:
            id: ${buyer}
          columns:
            - name: balance
              rule: LESS_THAN
              value: 1000.00
        
        # Verify product stock reduced
        - table: products
          where:
            id: ${product1}
          columns:
            - name: stock
              rule: EQUALS
              value: 9
        
        # Verify order created
        - table: orders
          expectedCount: 1
          where:
            user_id: ${buyer}
```

### Pattern 2: Testing with Multiple Inputs

Test service methods with multiple parameters:

```yaml
testCases:
  - id: TC_MULTI_PARAM_001
    name: Process payment with multiple parameters
    
    inputs:
      param0:  # First parameter
        orderId: ${order1}
        amount: 99.99
        currency: "USD"
      param1:  # Second parameter
        cardNumber: "4111111111111111"
        cvv: "123"
      param2: true  # Third parameter (boolean)
```

### Pattern 3: Conditional Test Execution

Use tags to group and filter tests:

```yaml
testCases:
  - id: TC_SMOKE_001
    name: Quick smoke test
    tags:
      - smoke
      - critical
    
  - id: TC_INTEGRATION_001
    name: Full integration test
    tags:
      - integration
      - slow
```

### Pattern 4: Data-Driven Testing

Create multiple test cases with similar structure:

```yaml
testCases:
  - id: TC_VALIDATION_001
    name: Validate email format - valid
    inputs:
      email: "valid@example.com"
    expected:
      assertions:
        - field: valid
          type: EQUALS
          value: true
  
  - id: TC_VALIDATION_002
    name: Validate email format - invalid
    inputs:
      email: "invalid-email"
    expected:
      exception: ValidationException
  
  - id: TC_VALIDATION_003
    name: Validate email format - null
    inputs:
      email: null
    expected:
      exception: NullPointerException
```

## Troubleshooting

### Common Issues

#### 1. Reference Not Found

**Error**: `Reference ${userId} not found`

**Solution**: Ensure the refId is defined before being used:
```yaml
data:
  users:
    - refId: userId  # Define here first
      columns:
        username: "john"

# Then use it
inputs:
  userId: ${userId}  # Use it here
```

#### 2. Type Conversion Error

**Error**: `Cannot convert String to Long`

**Solution**: Use appropriate type casting in your test code or ensure YAML values match expected types:
```yaml
inputs:
  userId: 123  # Number, not string
  amount: 99.99
```

#### 3. Database Verification Fails

**Error**: `Expected 1 row, found 2`

**Solution**: Check your WHERE conditions and ensure test isolation:
```yaml
databaseSetup:
  cleanBefore: true  # Clean before each test
  cleanAfter: true   # Clean after each test
```

#### 4. Mock Not Applied

**Error**: Real service called instead of mock

**Solution**: Ensure mock target matches bean name:
```yaml
mocks:
  - target: notificationService  # Must match Spring bean name
    method: sendEmail
    behavior: DO_NOTHING
```

### Best Practices

1. **Always Clean Database**: Use `cleanBefore: true` to ensure test isolation

2. **Use Meaningful IDs**: Make refIds and test case IDs descriptive
   ```yaml
   - refId: adminUser  # Good
   - refId: user1      # Less descriptive
   ```

3. **Verify Critical Fields**: Don't just check the happy path
   ```yaml
   expected:
     assertions:
       - field: id
         type: NOT_NULL
       - field: status
         type: EQUALS
         value: "SUCCESS"
       - field: createdAt
         type: NOT_NULL
   ```

4. **Use Database Verification**: Always verify database state after operations
   ```yaml
   databaseVerification:
     tables:
       - table: users
         expectedCount: 1
   ```

5. **Organize Test Configs**: Use directory structure matching test classes
   ```
   test-configs/
   ├── UserServiceTest/
   │   ├── testCreate.yaml
   │   ├── testUpdate.yaml
   │   └── testDelete.yaml
   └── OrderServiceTest/
       └── testCreateOrder.yaml
   ```

## Running Tests

### Command Line

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=UserServiceTest

# Run with specific TestNG XML
mvn test -DsuiteXmlFile=testng.xml
```

### IDE Integration

Most IDEs support TestNG directly:
- **IntelliJ IDEA**: Right-click on test class → Run
- **Eclipse**: Right-click → Run As → TestNG Test

### View Reports

After test execution, view the HTML report:
```
target/testify-reports/report.html
```

---

For more examples, see the `testify-example` module in the project repository.

