# Testify API Reference

Complete reference for Testify YAML configuration schema.

## Test Suite Configuration

```yaml
name: string                    # Test suite name (required)
description: string             # Test suite description (optional)
targetClass: string             # Fully qualified class name (required)
targetMethod: string            # Method name to test (required)
globalSetup: DatabaseSetup      # Global database setup for all tests (optional)
globalMocks: MockConfig[]       # Global mocks for all tests (optional)
variables: map                  # Common variables (optional)
testCases: TestCase[]           # List of test cases (required)
```

## Test Case

```yaml
id: string                      # Unique test case ID (required)
name: string                    # Test case name (required)
description: string             # Test case description (optional)
enabled: boolean                # Whether test is enabled (default: true)
tags: string[]                  # Tags for categorization (optional)
databaseSetup: DatabaseSetup    # Database setup (optional)
mocks: MockConfig[]             # Mock configurations (optional)
inputs: map                     # Input parameters (required)
expected: ExpectedResult        # Expected results (required)
databaseVerification: DatabaseVerification  # Database state verification (optional)
```

## Database Setup

```yaml
cleanBefore: boolean            # Clean database before test (default: false)
cleanAfter: boolean             # Clean database after test (default: false)
truncateTables: string[]        # Tables to truncate (optional)
data: map<string, TableData[]>  # Data to insert (optional)
```

### Table Data

```yaml
refId: string                   # Reference ID for generated key (optional)
columns: map<string, any>       # Column name -> value mapping (required)
```

**Example:**
```yaml
databaseSetup:
  cleanBefore: true
  cleanAfter: true
  truncateTables:
    - users
    - orders
  data:
    users:
      - refId: user1
        columns:
          username: "john"
          email: "john@example.com"
          age: 30
          created_at: "2024-01-01T10:00:00"
```

## Mock Configuration

```yaml
target: string                  # Bean name or class name (required)
method: string                  # Method name to mock (required)
parameters: map                 # Method parameters for matching (optional)
behavior: MockBehavior          # Mock behavior type (required)
returnValue: any                # Return value for RETURN behavior (optional)
throwException: string          # Exception class for THROW behavior (optional)
exceptionMessage: string        # Exception message (optional)
```

### Mock Behavior

- `RETURN` - Return specified value
- `THROW` - Throw specified exception
- `DO_NOTHING` - Do nothing (for void methods)
- `SPY` - Use spy (partial mock)

**Examples:**
```yaml
# Return value
mocks:
  - target: priceService
    method: calculatePrice
    behavior: RETURN
    returnValue: 99.99

# Throw exception
mocks:
  - target: paymentService
    method: processPayment
    behavior: THROW
    throwException: com.example.PaymentException
    exceptionMessage: "Payment failed"

# Do nothing
mocks:
  - target: logService
    method: log
    behavior: DO_NOTHING
```

## Expected Result

```yaml
value: any                      # Expected return value (optional)
exception: string               # Expected exception class (optional)
assertions: FieldAssertion[]    # Field-level assertions (optional)
customAssertion: string         # Custom assertion script (optional)
```

### Field Assertion

```yaml
field: string                   # Field path (required)
type: AssertionType             # Assertion type (required)
value: any                      # Expected value (optional, depends on type)
message: string                 # Custom error message (optional)
```

### Assertion Types

| Type | Description | Requires Value |
|------|-------------|----------------|
| `EQUALS` | Exact equality | Yes |
| `NOT_EQUALS` | Not equal | Yes |
| `NOT_NULL` | Value is not null | No |
| `NULL` | Value is null | No |
| `CONTAINS` | String/collection contains | Yes |
| `NOT_CONTAINS` | Does not contain | Yes |
| `STARTS_WITH` | String starts with | Yes |
| `ENDS_WITH` | String ends with | Yes |
| `GREATER_THAN` | Numeric > | Yes |
| `LESS_THAN` | Numeric < | Yes |
| `GREATER_THAN_OR_EQUALS` | Numeric >= | Yes |
| `LESS_THAN_OR_EQUALS` | Numeric <= | Yes |
| `IN_RANGE` | Value in range | Yes (array [min, max]) |
| `MATCHES_REGEX` | Matches regex pattern | Yes |
| `HAS_SIZE` | Collection/string size | Yes (integer) |
| `IS_EMPTY` | Empty check | No |
| `IS_NOT_EMPTY` | Not empty check | No |
| `INSTANCE_OF` | Type check | Yes (class name) |
| `COLLECTION_CONTAINS` | Collection contains item | Yes |
| `COLLECTION_NOT_CONTAINS` | Collection doesn't contain | Yes |

**Examples:**
```yaml
expected:
  assertions:
    # Simple equality
    - field: status
      type: EQUALS
      value: "SUCCESS"
    
    # Null checks
    - field: id
      type: NOT_NULL
    
    # String operations
    - field: email
      type: CONTAINS
      value: "@example.com"
    
    - field: orderNumber
      type: STARTS_WITH
      value: "ORD-"
    
    # Numeric comparisons
    - field: age
      type: GREATER_THAN
      value: 18
    
    - field: price
      type: IN_RANGE
      value: [0, 1000]
    
    # Regex
    - field: phone
      type: MATCHES_REGEX
      value: "^\\+?[1-9]\\d{1,14}$"
    
    # Collection
    - field: items
      type: HAS_SIZE
      value: 3
    
    - field: roles
      type: COLLECTION_CONTAINS
      value: "ADMIN"
    
    # Nested fields
    - field: user.address.city
      type: EQUALS
      value: "New York"
```

## Database Verification

```yaml
tables: TableVerification[]     # List of table verifications (required)
```

### Table Verification

```yaml
table: string                   # Table name (required)
where: map<string, any>         # WHERE conditions (optional)
expectedCount: integer          # Expected row count (optional)
columns: ColumnVerification[]   # Column verifications (optional)
```

### Column Verification

```yaml
name: string                    # Column name (required)
rule: VerificationRule          # Verification rule (required)
value: any                      # Expected value (optional, depends on rule)
toleranceSeconds: integer       # Tolerance for time comparisons (optional)
```

### Verification Rules

| Rule | Description | Requires Value |
|------|-------------|----------------|
| `EQUALS` | Exact match | Yes |
| `NOT_EQUALS` | Not equal | Yes |
| `NOT_NULL` | Value exists | No |
| `NULL` | Value is null | No |
| `GREATER_THAN` | Numeric > | Yes |
| `LESS_THAN` | Numeric < | Yes |
| `GREATER_THAN_OR_EQUALS` | Numeric >= | Yes |
| `LESS_THAN_OR_EQUALS` | Numeric <= | Yes |
| `CONTAINS` | String contains | Yes |
| `STARTS_WITH` | String starts with | Yes |
| `ENDS_WITH` | String ends with | Yes |
| `IN_LIST` | Value in list | Yes (array) |
| `NOT_IN_LIST` | Value not in list | Yes (array) |
| `TIME_CLOSE_TO` | Timestamp within tolerance | Yes + toleranceSeconds |
| `DATE_EQUALS` | Date equality (ignores time) | Yes |
| `REGEX_MATCH` | Pattern match | Yes |

**Examples:**
```yaml
databaseVerification:
  tables:
    # Simple count verification
    - table: users
      expectedCount: 1
    
    # With WHERE clause
    - table: orders
      where:
        user_id: ${testUser}
        status: "PENDING"
      expectedCount: 2
    
    # Column verifications
    - table: users
      where:
        username: "john"
      columns:
        # Exact match
        - name: email
          rule: EQUALS
          value: "john@example.com"
        
        # Not null
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
        
        # Timestamp with tolerance
        - name: last_login
          rule: TIME_CLOSE_TO
          value: "now"
          toleranceSeconds: 60
        
        # In list
        - name: status
          rule: IN_LIST
          value: ["ACTIVE", "PENDING"]
        
        # Regex
        - name: phone
          rule: REGEX_MATCH
          value: "^\\+?[1-9]\\d{1,14}$"
```

## Reference System

Use `${refId}` syntax to reference generated database IDs or variables.

### Defining References

```yaml
databaseSetup:
  data:
    users:
      - refId: user1          # Define reference
        columns:
          username: "john"
      
      - refId: adminUser      # Another reference
        columns:
          username: "admin"
```

### Using References

```yaml
# In other table inserts
data:
  orders:
    - columns:
        user_id: ${user1}     # Use reference

# In test inputs
inputs:
  userId: ${user1}

# In expected assertions
expected:
  assertions:
    - field: userId
      type: EQUALS
      value: ${user1}

# In database verification
databaseVerification:
  tables:
    - table: orders
      where:
        user_id: ${user1}
      columns:
        - name: user_id
          rule: EQUALS
          value: ${user1}
```

## Complete Example

```yaml
name: User Order Management Test Suite
description: Tests for user order creation and management
targetClass: com.example.service.OrderService
targetMethod: createOrder

# Global configuration
globalMocks:
  - target: notificationService
    method: sendNotification
    behavior: DO_NOTHING

globalSetup:
  truncateTables:
    - users
    - orders

# Test cases
testCases:
  # Test Case 1: Success scenario
  - id: TC_ORDER_001
    name: Create order successfully
    description: Create order for existing user with sufficient balance
    enabled: true
    tags:
      - smoke
      - critical
    
    databaseSetup:
      cleanBefore: true
      cleanAfter: true
      data:
        users:
          - refId: buyer
            columns:
              username: "buyer1"
              email: "buyer@example.com"
              balance: 1000.00
              is_active: true
        
        products:
          - refId: product1
            columns:
              name: "Laptop"
              price: 899.99
              stock: 10
    
    mocks:
      - target: paymentGateway
        method: processPayment
        behavior: RETURN
        returnValue: true
    
    inputs:
      userId: ${buyer}
      productId: ${product1}
      quantity: 1
    
    expected:
      assertions:
        - field: id
          type: NOT_NULL
        - field: userId
          type: EQUALS
          value: ${buyer}
        - field: status
          type: EQUALS
          value: "CONFIRMED"
        - field: totalAmount
          type: EQUALS
          value: 899.99
        - field: orderNumber
          type: STARTS_WITH
          value: "ORD-"
    
    databaseVerification:
      tables:
        # Verify order created
        - table: orders
          expectedCount: 1
          where:
            user_id: ${buyer}
          columns:
            - name: status
              rule: EQUALS
              value: "CONFIRMED"
            - name: created_at
              rule: NOT_NULL
            - name: created_at
              rule: TIME_CLOSE_TO
              value: "now"
              toleranceSeconds: 5
        
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
  
  # Test Case 2: Failure scenario
  - id: TC_ORDER_002
    name: Create order fails for insufficient balance
    description: Should throw exception when user has insufficient balance
    enabled: true
    tags:
      - negative
    
    databaseSetup:
      cleanBefore: true
      cleanAfter: true
      data:
        users:
          - refId: poorBuyer
            columns:
              username: "poor_buyer"
              email: "poor@example.com"
              balance: 10.00
        
        products:
          - refId: expensiveProduct
            columns:
              name: "Expensive Item"
              price: 999.99
    
    inputs:
      userId: ${poorBuyer}
      productId: ${expensiveProduct}
      quantity: 1
    
    expected:
      exception: InsufficientBalanceException
    
    databaseVerification:
      tables:
        # Verify no order created
        - table: orders
          where:
            user_id: ${poorBuyer}
          expectedCount: 0
```

## Java API

### TestContext

```java
// Get current context
TestContext context = TestContext.current();

// Store/retrieve database reference
context.putDatabaseReference("userId", 123L);
Object userId = context.getDatabaseReference("userId");

// Store/retrieve variable
context.putVariable("testEmail", "test@example.com");
Object email = context.getVariable("testEmail");

// Resolve reference (${refId})
Object resolved = context.resolveReference("${userId}");
Object resolved = context.resolveReference("direct value");

// Clear context (important!)
TestContext.clear();
```

### YamlTestConfigParser

```java
YamlTestConfigParser parser = new YamlTestConfigParser();

// Parse from classpath
TestSuite suite = parser.parseFromClasspath("test-configs/MyTest.yaml");

// Parse from file
TestSuite suite = parser.parseFromFile("/absolute/path/to/config.yaml");

// Parse from string
String yaml = "...";
TestSuite suite = parser.parseFromString(yaml);
```

### DatabaseInitService

```java
@Autowired
DatabaseInitService databaseInitService;

// Setup database
databaseInitService.setup(testCase.getDatabaseSetup());

// Cleanup database
databaseInitService.cleanup(testCase.getDatabaseSetup());
```

### DatabaseVerificationService

```java
@Autowired
DatabaseVerificationService verificationService;

// Verify database state
verificationService.verify(testCase.getDatabaseVerification());
```

### MockConfigService

```java
@Autowired
MockConfigService mockConfigService;

Map<String, Object> beanMap = new HashMap<>();
beanMap.put("myService", myService);

// Setup mocks
mockConfigService.setupMocks(testCase.getMocks(), beanMap);

// Reset mocks
mockConfigService.resetMocks();

// Clear mock registry
mockConfigService.clearMocks();
```

### AssertionService

```java
@Autowired
AssertionService assertionService;

// Verify result matches expected
assertionService.verify(actualResult, testCase.getExpected());

// Verify exception
try {
    // ... code that throws
} catch (Exception e) {
    assertionService.verifyException(e, testCase.getExpected());
}
```

### ReportService

```java
ReportService reportService = new ReportService();

// Start test execution
reportService.startTest();

// Record test result
reportService.recordTestResult(testSuite, testCase, passed, errorMessage, executionTime);

// Generate HTML report
reportService.generateReport("target/testify-reports/report.html");
```

## TestNG Integration

### DataProvider Usage

```java
@Test(dataProvider = "testifyProviderWithPath", 
      dataProviderClass = TestifyDataProvider.class)
@TestConfig("test-configs/MyTest/testMethod.yaml")
public void testMethod(TestSuite testSuite, TestCase testCase) {
    // Test implementation
}
```

### Listener Configuration

In `testng.xml`:

```xml
<suite name="My Test Suite">
    <listeners>
        <listener class-name="com.loadup.testify.report.listener.TestifyReportListener"/>
    </listeners>
    <test name="My Tests">
        <classes>
            <class name="com.example.MyTest"/>
        </classes>
    </test>
</suite>
```

---

For more examples and usage patterns, see [USAGE_GUIDE.md](USAGE_GUIDE.md) and [QUICK_START.md](QUICK_START.md).

