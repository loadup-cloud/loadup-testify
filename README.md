# LoadUp Testify

A Spring Boot based data-driven test SDK/library for simplified test case writing, data preparation, and assertions.

## Overview

LoadUp Testify is a Maven multi-module project designed to be integrated into other projects as a second-party library for data-driven testing. It simplifies:

- Test case data preparation from CSV files
- Database state assertions
- Variable generation with Datafaker
- Cross-file variable sharing and references
- Complex object mapping with Jackson

## Project Structure

| Module | Description | Dependencies |
|--------|-------------|--------------|
| `common` | Global configuration, exceptions, variable resolution, and Datafaker integration | None |
| `data` | Database interaction with Spring Data JDBC for PrepareData and ExpectedData | `common` |
| `core` | Core test execution engine with TestifyTestBase and TestifyProvider | `common`, `data` |
| `assertions` | Configurable data comparison and assertion utilities | `common` |
| `example` | Integration examples demonstrating all features | `core`, `data`, `assertions` |

## Quick Start

### 1. Add Dependency

```xml
<dependency>
    <groupId>com.loadup</groupId>
    <artifactId>loadup-testify-core</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <scope>test</scope>
</dependency>
```

### 2. Create Test Class

```java
@SpringBootTest(classes = YourApplication.class)
@ActiveProfiles("test")
public class UserServiceTest extends TestifyTestBase {

    @Autowired
    private UserService userService;

    @Override
    protected Object getTestBean() {
        return userService;
    }

    @Test(dataProvider = "TestifyProvider")
    public void testCreateUser(String caseId, PrepareData prepareData) {
        runTest(caseId, prepareData);
    }
}
```

### 3. Create Test Data Directory Structure

```
src/test/resources/com/your/package/
└── case01/
    ├── PrepareData/
    │   └── table_users.csv
    ├── ExpectedData/
    │   └── table_users.csv
    └── test_config.yaml
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

## Building

```bash
mvn clean install
```

## Technology Stack

- **Java:** 17+
- **Spring Boot:** 3.2.5
- **TestNG:** 7.9.0
- **Jackson:** 2.17.0
- **OpenCSV:** 5.9
- **Datafaker:** 2.2.2
- **Mockito:** 5.11.0
- **Spring Data JDBC:** For database operations

## License

MIT License