# LoadUp Testify

A comprehensive testing framework for Java applications that enables configuration-driven test case development through YAML files. Simplify your test case writing with pre-configured data initialization, mock setup, and rich assertions.

## 📋 Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Quick Start](#quick-start)
- [Module Overview](#module-overview)
- [YAML Configuration Guide](#yaml-configuration-guide)
- [Usage Examples](#usage-examples)
- [Advanced Features](#advanced-features)
- [Best Practices](#best-practices)
- [Contributing](#contributing)

## ✨ Features

- **Configuration-Driven Testing**: Define test cases in YAML files with zero code
- **Database Management**: Automatic database initialization, cleanup, and verification
- **Reference System**: Use generated primary keys across test cases with `${refId}` syntax
- **Mock Integration**: Seamless Mockito integration for external services
- **Rich Assertions**: 20+ assertion types including field-level validations
- **Database Verification**: Verify database state with flexible rules (equals, not null, greater than, time comparisons, etc.)
- **Test Reports**: Automatic HTML report generation with detailed execution results
- **TestNG DataProvider**: Custom data provider for test case parameterization
- **Spring Boot Integration**: Full support for Spring Boot 3.x applications

## 🏗️ Architecture

Testify is organized as a multi-module Maven project:

```
loadup-testify/
├── testify-core/          # Core framework and YAML parsing
├── testify-data/          # Database operations and verification
├── testify-mock/          # Mockito integration
├── testify-assertions/    # Assertion engine
├── testify-report/        # Report generation
└── testify-example/       # Usage examples
```

## 🚀 Quick Start

### 1. Add Dependency

Add Testify to your project's `pom.xml`:

```xml
<dependency>
     <groupId>com.github.loadup</groupId>
    <artifactId>testify-core</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <scope>test</scope>
</dependency>
<dependency>
     <groupId>com.github.loadup</groupId>
    <artifactId>testify-data</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <scope>test</scope>
</dependency>
<dependency>
     <groupId>com.github.loadup</groupId>
    <artifactId>testify-mock</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <scope>test</scope>
</dependency>
<dependency>
     <groupId>com.github.loadup</groupId>
    <artifactId>testify-assertions</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <scope>test</scope>
</dependency>
```

### 2. Create Test Configuration

Create a YAML file at `src/test/resources/test-configs/UserServiceTest/testCreateUser.yaml`:

```yaml
name: User Service Test Suite
targetClass: com.example.service.UserService
targetMethod: createUser

testCases:
  - id: TC001
    name: Create user successfully
    
    databaseSetup:
      cleanBefore: true
      cleanAfter: true
      truncateTables:
        - users
    
    inputs:
      username: "john_doe"
      email: "john@example.com"
      password: "Pass123"
    
    expected:
      assertions:
        - field: username
          type: EQUALS
          value: "john_doe"
        - field: id
          type: NOT_NULL
```

### 3. Write Test Class

```java
@SpringBootTest
public class UserServiceTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private UserService userService;
    
    @Test(dataProvider = "testifyProviderWithPath", 
          dataProviderClass = TestifyDataProvider.class)
    @TestConfig("test-configs/UserServiceTest/testCreateUser.yaml")
    public void testCreateUser(TestSuite testSuite, TestCase testCase) {
        // Framework handles execution automatically
    }
}
```

## 📦 Module Overview

### testify-core

Core framework providing:
- YAML configuration parsing
- Test context management
- Reference resolution system (`${refId}`)
- TestNG DataProvider integration

**Key Classes:**
- `YamlTestConfigParser`: Parses YAML test configurations
- `TestContext`: Thread-local context for storing references
- `TestifyDataProvider`: Custom TestNG DataProvider

### testify-data

Database operations:
- Automatic table truncation
- Data insertion with reference tracking
- Database state verification
- Support for H2, MySQL, PostgreSQL

**Key Classes:**
- `DatabaseInitService`: Initializes database for tests
- `DatabaseVerificationService`: Verifies database state after test execution

### testify-mock

Mockito integration:
- Bean mocking and spying
- Method behavior configuration
- Support for return values, exceptions, and void methods

**Key Classes:**
- `MockConfigService`: Configures mocks from YAML

### testify-assertions

Rich assertion engine:
- Field-level assertions with path navigation
- 20+ assertion types
- Custom error messages

**Key Classes:**
- `AssertionService`: Performs assertions on test results

### testify-report

Report generation:
- HTML report generation
- Test execution metrics
- Pass/fail statistics

**Key Classes:**
- `ReportService`: Generates test reports

## 📝 YAML Configuration Guide

### Test Suite Structure

```yaml
name: Test Suite Name
description: Suite description
targetClass: com.example.service.MyService
targetMethod: methodToTest

# Global configuration applied to all test cases
globalMocks:
  - target: externalService
    method: callApi
    behavior: RETURN
    returnValue: "mocked response"

globalSetup:
  truncateTables:
    - table1
    - table2

# Individual test cases
testCases:
  - id: TC001
    name: Test case name
    # ... test case configuration
```

### Database Setup

```yaml
databaseSetup:
  cleanBefore: true              # Truncate tables before test
  cleanAfter: true               # Clean up after test
  truncateTables:
    - users
    - orders
  
  data:
    users:
      - refId: user1             # Reference ID for later use
        columns:
          username: "john_doe"
          email: "john@example.com"
          password: "pass123"
      
      - refId: user2
        columns:
          username: "jane_doe"
          email: "jane@example.com"
    
    orders:
      - refId: order1
        columns:
          user_id: ${user1}      # Reference to user1's generated ID
          amount: 99.99
          status: "PENDING"
```

### Mock Configuration

```yaml
mocks:
  # Return specific value
  - target: notificationService
    method: sendEmail
    behavior: RETURN
    returnValue: true
  
  # Throw exception
  - target: paymentService
    method: processPayment
    behavior: THROW
    throwException: java.lang.RuntimeException
    exceptionMessage: "Payment failed"
  
  # Do nothing (for void methods)
  - target: loggerService
    method: log
    behavior: DO_NOTHING
```

### Test Inputs

```yaml
inputs:
  # Simple values
  username: "john_doe"
  age: 25
  active: true
  
  # Reference to database-generated ID
  userId: ${user1}
  
  # Reference to variable
  email: ${testEmail}
```

### Expected Results

```yaml
expected:
  # Simple value comparison
  value: "expected result"
  
  # Or expect exception
  exception: IllegalArgumentException
  
  # Field-level assertions
  assertions:
    - field: username
      type: EQUALS
      value: "john_doe"
    
    - field: age
      type: GREATER_THAN
      value: 18
    
    - field: email
      type: NOT_NULL
    
    - field: address.city
      type: STARTS_WITH
      value: "New"
    
    - field: tags
      type: HAS_SIZE
      value: 3
```

### Database Verification

```yaml
databaseVerification:
  tables:
    - table: users
      expectedCount: 2          # Verify row count
      where:                    # WHERE conditions
        username: "john_doe"
      columns:
        - name: email
          rule: EQUALS
          value: "john@example.com"
        
        - name: created_at
          rule: NOT_NULL
        
        - name: age
          rule: GREATER_THAN
          value: 18
        
        - name: updated_at
          rule: TIME_CLOSE_TO
          value: "now"
          toleranceSeconds: 5
```

## 💡 Usage Examples

### Example 1: Simple Service Test

```yaml
name: User Creation Test
targetClass: com.example.UserService
targetMethod: createUser

testCases:
  - id: TC001
    name: Create valid user
    
    inputs:
      username: "testuser"
      email: "test@example.com"
    
    expected:
      assertions:
        - field: id
          type: NOT_NULL
        - field: username
          type: EQUALS
          value: "testuser"
```

### Example 2: Test with Database Setup

```yaml
testCases:
  - id: TC002
    name: Create order for existing user
    
    databaseSetup:
      cleanBefore: true
      data:
        users:
          - refId: testUser
            columns:
              username: "john"
              email: "john@example.com"
    
    inputs:
      userId: ${testUser}
      amount: 99.99
    
    databaseVerification:
      tables:
        - table: orders
          expectedCount: 1
          columns:
            - name: user_id
              rule: EQUALS
              value: ${testUser}
            - name: status
              rule: EQUALS
              value: "PENDING"
```

### Example 3: Test with Mocks

```yaml
testCases:
  - id: TC003
    name: Create user with email notification
    
    mocks:
      - target: emailService
        method: sendWelcomeEmail
        behavior: DO_NOTHING
    
    inputs:
      username: "newuser"
      email: "new@example.com"
    
    expected:
      assertions:
        - field: emailSent
          type: EQUALS
          value: true
```

### Example 4: Exception Testing

```yaml
testCases:
  - id: TC004
    name: Create duplicate user should fail
    
    databaseSetup:
      data:
        users:
          - columns:
              username: "existing"
              email: "existing@example.com"
    
    inputs:
      username: "existing"
      email: "new@example.com"
    
    expected:
      exception: IllegalArgumentException
```

## 🔥 Advanced Features

### Reference System

The reference system allows you to use generated database IDs in your tests:

1. **Define a reference** when inserting data:
```yaml
databaseSetup:
  data:
    users:
      - refId: myUser      # Define reference
        columns:
          username: "john"
```

2. **Use the reference** in inputs or verifications:
```yaml
inputs:
  userId: ${myUser}      # Use reference

databaseVerification:
  tables:
    - table: orders
      columns:
        - name: user_id
          rule: EQUALS
          value: ${myUser}   # Use in verification
```

### Assertion Types

| Type | Description | Example |
|------|-------------|---------|
| `EQUALS` | Exact match | `value: "expected"` |
| `NOT_EQUALS` | Not equal | `value: "unwanted"` |
| `NOT_NULL` | Value is not null | - |
| `NULL` | Value is null | - |
| `CONTAINS` | String contains | `value: "substring"` |
| `STARTS_WITH` | String starts with | `value: "prefix"` |
| `ENDS_WITH` | String ends with | `value: "suffix"` |
| `GREATER_THAN` | Numeric comparison | `value: 10` |
| `LESS_THAN` | Numeric comparison | `value: 100` |
| `MATCHES_REGEX` | Regex pattern | `value: "^[A-Z]+$"` |
| `HAS_SIZE` | Collection/String size | `value: 5` |
| `IS_EMPTY` | Empty check | - |
| `IS_NOT_EMPTY` | Not empty check | - |
| `COLLECTION_CONTAINS` | Collection contains item | `value: "item"` |

### Database Verification Rules

| Rule | Description | Usage |
|------|-------------|-------|
| `EQUALS` | Exact match | Compare values |
| `NOT_NULL` | Value exists | Check required fields |
| `GREATER_THAN` | Numeric > | Validate calculations |
| `TIME_CLOSE_TO` | Timestamp within tolerance | Verify timestamps |
| `CONTAINS` | String contains | Partial match |
| `REGEX_MATCH` | Pattern match | Complex validation |

## 🎯 Best Practices

1. **Organize Test Configs**: Use directory structure matching your test classes
   ```
   test-configs/
   ├── UserServiceTest/
   │   ├── testCreateUser.yaml
   │   └── testUpdateUser.yaml
   └── OrderServiceTest/
       └── testCreateOrder.yaml
   ```

2. **Use Descriptive IDs**: Make test case IDs meaningful
   ```yaml
   - id: TC_USER_CREATE_SUCCESS
     name: Successfully create a new user
   ```

3. **Clean Database**: Always clean database before tests
   ```yaml
   databaseSetup:
     cleanBefore: true
     cleanAfter: true
   ```

4. **Use References**: Leverage reference system for related data
   ```yaml
   data:
     users:
       - refId: mainUser
     orders:
       - columns:
           user_id: ${mainUser}
   ```

5. **Comprehensive Assertions**: Verify all important fields
   ```yaml
   expected:
     assertions:
       - field: id
         type: NOT_NULL
       - field: status
         type: EQUALS
         value: "SUCCESS"
       - field: createdAt
         type: TIME_CLOSE_TO
         value: "now"
   ```

## 📊 Report Generation

Tests automatically generate HTML reports showing:
- Total tests run
- Pass/fail count
- Execution time per test
- Detailed error messages
- Pass rate percentage

Reports are generated at: `target/testify-reports/report.html`

## 🔧 Configuration

Configure Testify using system properties or Spring configuration:

```properties
# Report output path
testify.report.path=target/testify-reports/report.html

# Database configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
```

## 📚 API Documentation

### Core Classes

#### TestContext
```java
// Get current context
TestContext context = TestContext.current();

// Store reference
context.putDatabaseReference("userId", 123L);

// Retrieve reference
Object userId = context.getDatabaseReference("userId");

// Resolve ${ref} syntax
Object resolved = context.resolveReference("${userId}");
```

#### YamlTestConfigParser
```java
YamlTestConfigParser parser = new YamlTestConfigParser();

// Parse from classpath
TestSuite suite = parser.parseFromClasspath("test-configs/MyTest.yaml");

// Parse from file
TestSuite suite = parser.parseFromFile("/path/to/config.yaml");
```

## 🤝 Contributing

Contributions are welcome! Please follow these guidelines:

1. Fork the repository
2. Create a feature branch
3. Write tests for new functionality
4. Ensure all tests pass
5. Submit a pull request

## 📄 License

This project is licensed under the Apache License 2.0.

## 🙏 Acknowledgments

Built with:
- Spring Boot 3.2.5
- TestNG 7.8.0
- Mockito 5.5.0
- Jackson 2.15.3
- Lombok 1.18.30

---

For more information and updates, visit [GitHub Repository](https://github.com/loadup/loadup-testify)

