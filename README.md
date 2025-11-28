# LoadUp Testify

A Spring Boot based data-driven test SDK/library for simplified test case writing, data preparation, and assertions.

## Overview

LoadUp Testify is a Maven multi-module project designed to be integrated into other projects as a second-party library for data-driven testing. It simplifies:

- Test case data preparation from CSV files
- Database state assertions
- Variable generation with Datafaker
- Cross-file variable sharing and references
- Complex object mapping with Jackson
- JSON field semantic comparison
- DateTime tolerance comparison

## Project Structure

| Module | Description | Dependencies |
|--------|-------------|--------------|
| `common` | Global configuration, exceptions, variable resolution, and Datafaker integration | None |
| `data` | Database interaction with Spring Data JDBC for PrepareData and ExpectedData | `common` |
| `core` | Core test execution engine with TestifyTestBase and TestifyProvider | `common`, `data` |
| `assertions` | Configurable data comparison and assertion utilities | `common` |
| `starter` | Spring Boot Starter for easy integration | All modules |
| `example` | Integration examples demonstrating all features | `starter` |

## Quick Start

### 1. Add Spring Boot Starter Dependency

```xml
<dependency>
    <groupId>com.github.loadup</groupId>
    <artifactId>loadup-testify-spring-boot-starter</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <scope>test</scope>
</dependency>
```

The starter automatically includes all necessary modules (common, data, core, assertions) and configures Spring Boot auto-configuration.

### 2. Create Test Class

```java
@SpringBootTest(classes = YourApplication.class)
@ActiveProfiles("test")
public class UserServiceTest extends TestifyTestBase {

    @TestBean  // Marks the service under test
    @Autowired
    private UserService userService;

    @MockitoBean  // Mock external dependencies
    private RoleService roleService;

    @Test(dataProvider = "TestifyProvider")
    public void testCreateUser(String caseId, PrepareData prepareData) {
        runTest(caseId, prepareData);
    }
}
```

### 3. Create Test Data Directory Structure

Test data files are placed alongside test classes:

```
src/test/java/com/your/package/test/user/
├── UserServiceTest.java
└── UserService.createUser/          # {ServiceName}.{methodName}/
    ├── case01/                       # caseId
    │   ├── PrepareData/
    │   │   └── table_users.csv
    │   ├── ExpectedData/
    │   │   └── table_users.csv
    │   └── test_config.yaml
    └── case02/
        └── ...
```

### 4. Define Test Configuration (test_config.yaml)

```yaml
description: "Test creating a new user"

args:
  - username: "${faker.name.username}"
    email: "${faker.internet.emailAddress}"
    firstName: "${faker.name.firstName}"
    lastName: "${faker.name.lastName}"

result:
  username: "${=faker.name.username}"
  email: "${=faker.internet.emailAddress}"

expected:
  - field: "response.username"
    operator: "NOT_NULL"
  - field: "response.createdAt"
    operator: "DATETIME_TOLERANCE"
    dateTolerance: 5000  # Allow 5 seconds difference

ignoreFields:
  - id
  - createdAt

enabled: true
```

## Variable Resolution

### Datafaker Integration

Use `${faker.xxx}` syntax to generate random data:

```yaml
username: "${faker.name.username}"
email: "${faker.internet.emailAddress}"
phone: "${faker.phoneNumber.cellPhone}"
```

### Variable Reference

Use `${=variable_name}` to reference captured variables:

```yaml
# In PrepareData, this generates a random username and captures it
username: "${faker.name.username}"

# In ExpectedData, reference the captured value
expected_username: "${=faker.name.username}"
```

### Shared Variable Pool

Variables are automatically captured during PrepareData processing and can be referenced in ExpectedData assertions.

## Assertion Operators

| Operator | Description |
|----------|-------------|
| `EQUALS` | Exact equality comparison |
| `NOT_EQUALS` | Not equal comparison |
| `NOT_NULL` | Value is not null |
| `IS_NULL` | Value is null |
| `GT` | Greater than |
| `GTE` | Greater than or equal |
| `LT` | Less than |
| `LTE` | Less than or equal |
| `CONTAINS` | String contains |
| `STARTS_WITH` | String starts with |
| `ENDS_WITH` | String ends with |
| `REGEX` | Regular expression match |
| `IGNORE_ORDER` | Ignore order in collections |
| `IGNORE_FIELDS` | Ignore specific fields |
| `DATETIME_TOLERANCE` | Compare datetime with tolerance (in milliseconds) |
| `JSON_EQUALS` | Semantic JSON comparison (ignores key ordering and whitespace) |

## Configuration Properties

Configure LoadUp Testify in `application.properties` or `application.yml`:

```properties
# Enable/disable auto-configuration (default: true)
loadup.testify.enabled=true

# Default datetime tolerance in milliseconds (default: 5000)
loadup.testify.date-tolerance=5000

# Clear tables before test (default: true)
loadup.testify.clear-tables-before-test=true

# Enable verbose logging (default: true)
loadup.testify.verbose-logging=true
```

## Features

### @TestBean Annotation

Mark the service under test with `@TestBean` instead of overriding `getTestBean()`:

```java
@TestBean
@Autowired
private UserService userService;
```

### Method Name Normalization

Test methods automatically map to service methods:
- `testCreateUser` → `createUser`
- `testCreateUserWithRole` → `createUserWithRole`

### JSON Field Comparison

For fields containing JSON strings (like `extraInfo`), use `JSON_EQUALS` operator:

```yaml
expected:
  - field: "response.extraInfo"
    operator: "JSON_EQUALS"
    value: '{"key": "value"}'
```

### DateTime Tolerance

Compare datetime fields with tolerance:

```yaml
expected:
  - field: "response.createdAt"
    operator: "DATETIME_TOLERANCE"
    dateTolerance: 5000  # milliseconds
```

## Building

```bash
mvn clean install
```

## Technology Stack

- **Java:** 17+
- **Spring Boot:** 3.5.8
- **TestNG:** 7.11.0
- **Jackson:** 2.19.4
- **OpenCSV:** 5.12.0
- **Datafaker:** 2.5.3
- **Mockito:** 5.20.0
- **Spring Data JDBC:** For database operations

## License

MIT License