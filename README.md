# Testify - Zero-Code Integration Testing Framework

> 基于 Spring Boot 3.4.3 和 JDK 21 的声明式集成测试框架  
> 通过 YAML 定义测试数据、Mock 行为和数据库断言，实现零代码侵入的自动化测试

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)]()
[![JDK](https://img.shields.io/badge/JDK-21-orange)]()
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-green)]()

## 🚀 Quick Start

### Add Dependency

```xml
<dependency>
    <groupId>com.github.loadup.framework</groupId>
    <artifactId>testify-spring-boot-starter</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <scope>test</scope>
</dependency>
```

### Create Test YAML

Create `src/test/resources/testcases/UserServiceTest/testCreateUser.yaml`:

```yaml
variables:
  userId: ${fn.uuid()}
  userName: ${faker.name.fullName()}
  email: ${faker.internet.emailAddress()}
  createdAt: ${time.now()}

input:
  - userId: ${userId}
    userName: ${userName}
    email: ${email}

expect:
  database:
    table: users
    mode: strict
    rows:
      - _match: {user_id: ${userId}}
        user_name: ${userName}
        status: ACTIVE
        created_at:
          op: approx
          val: ${createdAt}
          delta: 1000
```

---

## ✨ Features

### 🎯 Zero Code Intrusion
- **YAML-driven**: Define test data, mocks, and assertions in YAML files
- **Convention over configuration**: Auto-match YAML files by test class/method names
- **No service code changes**: Tests run against existing services without modification

### 🔧 Powerful Variable Engine
```yaml
variables:
  # Faker integration
  name: ${faker.name.fullName()}
  email: ${faker.internet.emailAddress()}
  
  # Time calculations
  now: ${time.now()}
  tomorrow: ${time.now('+1d')}
  lastWeek: ${time.now('-7d')}
  formatted: ${time.format('+1d', 'yyyy-MM-dd')}
  
  # Built-in functions
  id: ${fn.uuid()}
  random: ${fn.random(1, 100)}
  text: ${fn.randomString(20)}
  
  # Variable references
  fullName: ${name}
  greeting: Hello ${name}!
```

### 🎭 Smart Operator Matching

| Operator | Description | Example |
|----------|-------------|---------|
| `eq` | Equals (default) | `status: ACTIVE` |
| `ne` | Not equals | `{op: ne, val: DELETED}` |
| `gt`/`ge` | Greater than (or equal) | `{op: gt, val: 100}` |
| `lt`/`le` | Less than (or equal) | `{op: le, val: 99}` |
| `regex` | Regex match | `{op: regex, val: "^\\d{10}$"}` |
| `approx` | Time approximation | `{op: approx, val: ${now}, delta: 1000}` |
| `json` | JSON comparison | `{op: json, val: {...}, mode: full}` |
| `contains` | String contains | `{op: contains, val: "test"}` |

### 📊 Database Assertions with Rich Diff Reports

```yaml
expect:
  database:
    table: orders
    mode: strict
    rows:
      - _match: {order_id: ${orderId}}  # Row matching criteria
        status:
          op: eq
          val: COMPLETED
        total_amount:
          op: ge
          val: 100
        metadata:
          op: json
          val: {"source": "web"}
          mode: partial  # LENIENT mode
```

**Diff Output**:
```
❌ [Database Assertion Failed] Table: orders
================================================================================
Row Index: [0] | Status: DIFF | Match Criteria: {order_id=abc-123}
  | Field         | Expected (Operator) | Actual Value    | Message
  | ------------- | ------------------- | --------------- | ---------------
  | status        | COMPLETED           | PENDING         | ❌ Values not equal
  | total_amount  | >= 100              | 95              | ❌ Numeric comparison failed
--------------------------------------------------------------------------------
```

### 🗄️ Flexible Column Name Matching

Configure in `application-test.yml`:
```yaml
testify:
  database:
    columnNamingStrategy: caseInsensitive  # or camelCase, snake_case
```

- **caseInsensitive** (recommended): `user_id` matches `USER_ID`, `User_Id`
- **camelCase**: `user_name` → `userName`
- **snake_case**: `userName` → `user_name`

### 🐳 Testcontainers Integration

```yaml
testify:
  containers:
    enabled: true  # Auto-start MySQL, Redis containers
```

When `enabled: false`, uses physical database from `spring.datasource.*` properties.

---

## 📦 Architecture

```
testify
├── testify-core              # Models (TestContext, RowDiff)
├── testify-data-engine       # Variable resolution (Faker, SpEL, time)
├── testify-assert-engine     # Operators & DB assertions
├── testify-mock-engine       # Mock lifecycle (Mockito integration)
└── testify-spring-boot-starter  # Auto-configuration & TestNG
```

---

## 🔬 Advanced Features

### Variable Dependency Resolution

Variables can reference other variables with automatic dependency ordering:

```yaml
variables:
  firstName: ${faker.name.firstName()}
  lastName: ${faker.name.lastName()}
  fullName: ${firstName} ${lastName}  # References other variables
  greeting: Hello, ${fullName}!
```

### Time Offset Calculations

Supports flexible time arithmetic:
```yaml
variables:
  now: ${time.now()}
  oneDay: ${time.now('+1d')}      # Add 1 day
  twoHours: ${time.now('-2h')}    # Subtract 2 hours
  thirtyMin: ${time.now('+30m')}  # Add 30 minutes
  epoch: ${time.epochMilli('+7d')} # Epoch milliseconds
```

### JSON Partial Matching

```yaml
expect:
  database:
    table: products
    rows:
      - _match: {product_id: ${productId}}
        metadata:
          op: json
          val: |
            {
              "category": "electronics",
              "tags": ["new", "featured"]
            }
          mode: partial  # Ignores extra fields in actual JSON
```

### SQL Cleanup with Variables

```yaml
setup:
  clean_sql: |
    DELETE FROM orders WHERE user_id = '${userId}';
    DELETE FROM order_items WHERE order_id = '${orderId}';
```

---

## 🛠️ Configuration

### application-test.yml

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/testdb  # Used when containers disabled
    username: root
    password: root

testify:
  containers:
    enabled: false  # true to use Testcontainers
  database:
    columnNamingStrategy: caseInsensitive
```

---

## 📖 YAML Structure Reference

```yaml
# Variable definitions (resolved first)
variables:
  var1: ${faker.expression}
  var2: ${time.now('+1d')}
  var3: ${fn.uuid()}

# Test method input parameters
input:
  - param1: value1
    param2: ${var1}

# Mock configurations (to be implemented in Phase 4)
mocks:
  - bean: serviceName
    method: methodName
    returnValue: {...}

# Database setup
setup:
  clean_sql: DELETE FROM table WHERE id = '${var1}'
  db_setup:
    table: tableName
    data:
      - field1: value1
        field2: ${var2}

# Assertions
expect:
  database:
    table: tableName
    mode: strict  # or lenient
    rows:
      - _match: {id: ${var1}}  # Optional: row matching criteria
        field1: value1
        field2:
          op: operator
          val: expectedValue
```

---

## 🏗️ Build & Test

### Compile Framework
```bash
mvn clean compile
```

### Install Locally
```bash
mvn clean install -DskipTests
```

---

## 🎯 Implementation Status

| Phase | Status | Progress |
|-------|--------|----------|
| **Phase 1**: Core & Data Engine | ✅ Complete | 100% |
| **Phase 2**: Assert Engine | ✅ Complete | 100% |
| **Phase 3**: Mock & Starter | ✅ Complete | 80% |
| **Phase 4**: Demo & Verification | 🚧 In Progress | 20% |

### Completed Components

✅ **VariableEngine** - Full time offset support, dependency resolution  
✅ **All Operator Matchers** - SimpleMatcher, NumberMatcher, RegexMatcher, ApproxTimeMatcher, JsonMatcher  
✅ **ColumnNormalizer** - Case-insensitive, snake_case/camelCase conversion  
✅ **DbAssertEngine** - Enhanced with rich diff reporting  
✅ **MockRegistry** - Thread-local mock tracking  
✅ **Spring Boot Integration** - Auto-configuration, properties support  

### Remaining Work (Phase 4)

🚧 TestNG Listener & Data Provider  
🚧 SQL Execution Engine  
🚧 Complete Testcontainers Support  
🚧 Demo Module with Examples  
🚧 TestifyBase Abstract Class  

---

## 📚 Documentation

- [Implementation Plan](./brain/implementation_plan.md)
- [Walkthrough](./brain/walkthrough.md)
- [Task Tracking](./brain/task.md)

---

## 🤝 Contributing

This framework uses JDK 21 features extensively:
- **Record classes** for immutable data models
- **Pattern matching for switch** in operator processing
- **Enhanced instanceof** for type checking

---

## 📄 License

Copyright © 2026 LoadUp Framework

---

## 🙏 Acknowledgments

Built with:
- Spring Boot 3.4.3
- TestNG 7.10.1
- Mockito 5.14.2
- Datafaker 2.5.3
- JSONassert 1.5.3
- Testcontainers 2.0.3
