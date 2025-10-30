# Test Execution Guide - LoadUp Testify

## ✅ Project Build Complete

The LoadUp Testify framework has been successfully built and is ready for testing.

## 📋 Pre-Requisites

Before running tests, ensure:
1. ✅ Java 17+ is installed
2. ✅ Maven 3.6+ is installed  
3. ✅ All modules have been compiled: `mvn clean install -DskipTests`

## 🚀 Running Tests

### Option 1: Run All Tests in Example Module

```bash
cd /Users/lise/PersonalSpace/loadup-cloud/loadup-testify/testify-example
mvn test
```

### Option 2: Run Specific Test Class

```bash
# Run simple user service test
cd testify-example
mvn test -Dtest=SimpleUserServiceTest

# Run framework-integrated test
mvn test -Dtest=UserServiceTest

# Run order service test
mvn test -Dtest=OrderServiceTest
```

### Option 3: Run with TestNG XML

```bash
cd testify-example
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

### Option 4: Run Tests in IDE

**IntelliJ IDEA:**
1. Right-click on `testify-example/src/test/java`
2. Select "Run 'All Tests'"

**Eclipse:**
1. Right-click on test class
2. Select "Run As" → "TestNG Test"

## 📊 Available Test Classes

### 1. SimpleUserServiceTest ✅
**Location:** `testify-example/src/test/java/.../test/SimpleUserServiceTest.java`

**Test Cases:**
- `testCreateUserSimple()` - Creates a new user and verifies all fields
- `testCreateUserDuplicateUsernameShouldFail()` - Verifies duplicate username validation

**Run Command:**
```bash
mvn test -Dtest=SimpleUserServiceTest
```

### 2. UserServiceTest (Framework-Integrated)
**Location:** `testify-example/src/test/java/.../test/UserServiceTest.java`

**Features:**
- Uses Testify DataProvider
- Loads test cases from YAML configuration
- Supports database initialization and verification
- Includes mock configuration

**YAML Config:** `testify-example/src/test/resources/test-configs/UserServiceTest/testCreateUser.yaml`

**Test Cases Defined:**
- TC001: Create user with valid data
- TC002: Create user with database reference
- TC003: Create duplicate user should fail
- TC004: Create user with minimum data

**Run Command:**
```bash
mvn test -Dtest=UserServiceTest
```

### 3. OrderServiceTest (Framework-Integrated)
**Location:** `testify-example/src/test/java/.../test/OrderServiceTest.java`

**Features:**
- Uses Testify DataProvider
- Database reference system (`${refId}`)
- Database verification after test
- Mock external notifications

**YAML Config:** `testify-example/src/test/resources/test-configs/OrderServiceTest/testCreateOrder.yaml`

**Test Cases Defined:**
- TC_ORDER_001: Create order successfully
- TC_ORDER_002: Create order for non-existent user should fail
- TC_ORDER_003: Create multiple orders for same user

**Run Command:**
```bash
mvn test -Dtest=OrderServiceTest
```

## 📝 Test Configuration Files

### User Service Test Config
**File:** `test-configs/UserServiceTest/testCreateUser.yaml`

```yaml
name: User Service Test Suite
targetClass: com.loadup.testify.example.service.UserService
targetMethod: createUser

globalMocks:
  - target: notificationService
    method: sendWelcomeEmail
    behavior: DO_NOTHING

testCases:
  - id: TC001
    name: Create user with valid data
    databaseSetup:
      cleanBefore: true
      cleanAfter: true
    inputs:
      username: "john_doe"
      email: "john@example.com"
      password: "SecurePass123"
      fullName: "John Doe"
    expected:
      assertions:
        - field: username
          type: EQUALS
          value: "john_doe"
        - field: id
          type: NOT_NULL
```

### Order Service Test Config
**File:** `test-configs/OrderServiceTest/testCreateOrder.yaml`

```yaml
name: Order Service Test Suite
targetClass: com.loadup.testify.example.service.OrderService
targetMethod: createOrder

testCases:
  - id: TC_ORDER_001
    name: Create order successfully
    databaseSetup:
      cleanBefore: true
      data:
        users:
          - refId: testUser
            columns:
              username: "john_doe"
              email: "john@example.com"
    inputs:
      userId: ${testUser}
      amount: 299.99
    expected:
      assertions:
        - field: userId
          type: EQUALS
          value: ${testUser}
```

## 🔍 Verifying Test Results

### 1. Console Output
Tests will show results in the console:
```
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
```

### 2. HTML Reports
TestNG generates HTML reports at:
```
testify-example/target/surefire-reports/index.html
```

### 3. Testify Custom Reports
Custom HTML reports will be generated at:
```
testify-example/target/testify-reports/report.html
```

## ⚠️ Troubleshooting

### Issue 1: Tests Not Found
**Problem:** `No tests were executed`

**Solution:**
```bash
# Rebuild the project
mvn clean install -DskipTests
# Then run tests
mvn test
```

### Issue 2: Database Connection Issues
**Problem:** H2 database connection fails

**Solution:**
Check `application.yml` has correct H2 configuration:
```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
```

### Issue 3: Bean Not Found
**Problem:** `No qualifying bean of type...`

**Solution:**
Ensure all modules are installed:
```bash
cd /Users/lise/PersonalSpace/loadup-cloud/loadup-testify
mvn clean install -DskipTests
```

### Issue 4: YAML Configuration Not Found
**Problem:** `Resource not found: test-configs/...`

**Solution:**
Verify YAML files are in the correct location:
```
testify-example/src/test/resources/test-configs/
├── UserServiceTest/
│   └── testCreateUser.yaml
└── OrderServiceTest/
    └── testCreateOrder.yaml
```

## 📈 Expected Test Results

### SimpleUserServiceTest
```
✅ testCreateUserSimple - PASSED
✅ testCreateUserDuplicateUsernameShouldFail - PASSED

Total: 2 tests, 2 passed, 0 failed
```

### UserServiceTest (with framework)
```
✅ TC001: Create user with valid data - PASSED
✅ TC002: Create user with database reference - PASSED
✅ TC003: Create duplicate user should fail - PASSED
✅ TC004: Create user with minimum data - PASSED

Total: 4 tests, 4 passed, 0 failed
```

### OrderServiceTest (with framework)
```
✅ TC_ORDER_001: Create order successfully - PASSED
✅ TC_ORDER_002: Create order for non-existent user should fail - PASSED
✅ TC_ORDER_003: Create multiple orders for same user - PASSED

Total: 3 tests, 3 passed, 0 failed
```

## 🎯 Quick Test Commands

```bash
# Build everything
cd /Users/lise/PersonalSpace/loadup-cloud/loadup-testify
mvn clean install -DskipTests

# Run simple test (no framework dependencies)
cd testify-example
mvn test -Dtest=SimpleUserServiceTest

# Run all tests
mvn test

# Run with verbose output
mvn test -X

# Run and generate reports
mvn test && open target/surefire-reports/index.html
```

## 📚 Test Coverage

The example project includes tests for:

1. **User Management**
   - ✅ Create user with valid data
   - ✅ Duplicate username validation
   - ✅ Required field validation
   - ✅ Database persistence verification

2. **Order Management**
   - ✅ Create order for existing user
   - ✅ Order with database references
   - ✅ Invalid user ID handling
   - ✅ Multiple orders per user

3. **Framework Features Demonstrated**
   - ✅ YAML test configuration
   - ✅ Database initialization
   - ✅ Reference system (`${refId}`)
   - ✅ Mock configuration
   - ✅ Field assertions
   - ✅ Database verification

## 🎓 Next Steps

1. **Run the Simple Test First:**
   ```bash
   mvn test -Dtest=SimpleUserServiceTest
   ```
   This verifies basic Spring Boot and TestNG integration.

2. **Examine Framework Tests:**
   Review `UserServiceTest.java` and `OrderServiceTest.java` to see how the Testify framework is used.

3. **Study YAML Configurations:**
   Look at the YAML files in `test-configs/` to understand configuration options.

4. **Create Your Own Tests:**
   - Copy YAML template
   - Modify for your service
   - Run and iterate

## ✨ Success Indicators

Your tests are working correctly when you see:

1. ✅ Maven build completes without errors
2. ✅ Spring Boot application context loads
3. ✅ H2 database initializes
4. ✅ Tests execute and pass
5. ✅ Reports are generated

## 📞 Support

If tests don't run as expected:

1. Check all prerequisites are installed
2. Verify Maven build succeeds: `mvn clean install -DskipTests`
3. Review error messages in console output
4. Check log files in `target/surefire-reports/`
5. Ensure test resources are in correct directories

---

**Project Status:** ✅ COMPLETE AND READY FOR TESTING

All code, configuration, and tests are in place. Simply run the commands above to execute your tests!

**Date:** October 30, 2025
**Framework:** LoadUp Testify Testing Framework v1.0.0-SNAPSHOT

