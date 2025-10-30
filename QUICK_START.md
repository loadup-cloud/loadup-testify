# Testify Quick Start Guide

Get started with Testify in 5 minutes!

## Step 1: Build the Project

```bash
cd loadup-testify
mvn clean install
```

## Step 2: Run Example Tests

```bash
cd testify-example
mvn test
```

## Step 3: View Test Report

Open the generated report in your browser:
```bash
open target/testify-reports/report.html
```

## Step 4: Create Your Own Test

### 1. Add Dependencies to Your Project

```xml
<dependencies>
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
</dependencies>
```

### 2. Create a Test Configuration YAML

`src/test/resources/test-configs/MyServiceTest/testMethod.yaml`:

```yaml
name: My Test Suite
targetClass: com.example.MyService
targetMethod: myMethod

testCases:
  - id: TC001
    name: Test successful scenario
    
    databaseSetup:
      cleanBefore: true
      cleanAfter: true
      truncateTables:
        - my_table
      data:
        my_table:
          - refId: record1
            columns:
              name: "Test Record"
              value: 100
    
    inputs:
      recordId: ${record1}
      action: "process"
    
    expected:
      assertions:
        - field: status
          type: EQUALS
          value: "SUCCESS"
        - field: result
          type: NOT_NULL
    
    databaseVerification:
      tables:
        - table: my_table
          where:
            id: ${record1}
          columns:
            - name: processed
              rule: EQUALS
              value: true
```

### 3. Write Test Class

```java
@SpringBootTest
public class MyServiceTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private MyService myService;
    
    @Autowired
    private DatabaseInitService databaseInitService;
    
    @Autowired
    private AssertionService assertionService;
    
    @Autowired
    private DatabaseVerificationService databaseVerificationService;
    
    @Test(dataProvider = "testifyProviderWithPath", 
          dataProviderClass = TestifyDataProvider.class)
    @TestConfig("test-configs/MyServiceTest/testMethod.yaml")
    public void testMethod(TestSuite testSuite, TestCase testCase) {
        TestContext context = TestContext.current();
        
        try {
            // Setup database
            databaseInitService.setup(testCase.getDatabaseSetup());
            
            // Execute test
            Map<String, Object> inputs = testCase.getInputs();
            MyResult result = myService.myMethod(
                context.resolveReference(inputs.get("recordId")),
                (String) inputs.get("action")
            );
            
            // Verify results
            assertionService.verify(result, testCase.getExpected());
            
            // Verify database
            databaseVerificationService.verify(testCase.getDatabaseVerification());
            
        } finally {
            databaseInitService.cleanup(testCase.getDatabaseSetup());
            TestContext.clear();
        }
    }
}
```

### 4. Run Your Tests

```bash
mvn test -Dtest=MyServiceTest
```

## Common Patterns

### Pattern: Test with Mocks

```yaml
testCases:
  - id: TC001
    name: Test with mocked external service
    
    mocks:
      - target: externalApiService
        method: callApi
        behavior: RETURN
        returnValue: {"status": "OK", "data": "mocked"}
    
    inputs:
      userId: 123
    
    expected:
      assertions:
        - field: success
          type: EQUALS
          value: true
```

### Pattern: Test Exception

```yaml
testCases:
  - id: TC002
    name: Should throw exception for invalid input
    
    inputs:
      userId: -1
    
    expected:
      exception: IllegalArgumentException
```

### Pattern: Complex Database Setup

```yaml
databaseSetup:
  cleanBefore: true
  data:
    users:
      - refId: user1
        columns:
          username: "john"
    
    addresses:
      - refId: addr1
        columns:
          user_id: ${user1}
          city: "New York"
    
    orders:
      - refId: order1
        columns:
          user_id: ${user1}
          shipping_address_id: ${addr1}
```

## Next Steps

1. Read the complete [Usage Guide](USAGE_GUIDE.md)
2. Explore the [testify-example](testify-example/) module
3. Check out the [README](README.md) for all features
4. Customize for your project needs

## Getting Help

- Check the [USAGE_GUIDE.md](USAGE_GUIDE.md) for detailed documentation
- Look at examples in `testify-example/src/test/`
- Review YAML examples in `testify-example/src/test/resources/test-configs/`

## Tips

1. **Start Simple**: Begin with basic tests and gradually add complexity
2. **Use References**: Leverage `${refId}` for related data
3. **Clean Database**: Always use `cleanBefore: true` for test isolation
4. **Verify Database**: Use `databaseVerification` to ensure data integrity
5. **Mock External Services**: Mock services you don't control

Happy Testing! 🚀

