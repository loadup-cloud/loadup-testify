package com.loadup.testify.example;

import com.loadup.testify.core.base.TestifyTestBase;
import com.loadup.testify.core.model.PrepareData;
import com.loadup.testify.example.config.ExampleApplication;
import com.loadup.testify.example.model.User;
import com.loadup.testify.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testng.annotations.Test;

/**
 * Example test class demonstrating Testify data-driven testing.
 * 
 * This test class shows how to:
 * 1. Use the TestifyProvider for data-driven tests
 * 2. Prepare database data from CSV files
 * 3. Execute test methods with complex parameters
 * 4. Assert database state after test execution
 * 5. Use Datafaker for random data generation
 * 6. Reference captured variables across test phases
 */
@SpringBootTest(classes = ExampleApplication.class)
@ActiveProfiles("test")
public class UserServiceTest extends TestifyTestBase {

    @Autowired
    private UserService userService;

    @Override
    protected Object getTestBean() {
        return userService;
    }

    /**
     * Test creating a user with data-driven test cases.
     * 
     * Test data is loaded from:
     * - src/test/resources/com/loadup/testify/example/case01/test_config.yaml
     * - src/test/resources/com/loadup/testify/example/case01/PrepareData/*.csv
     * - src/test/resources/com/loadup/testify/example/case01/ExpectedData/*.csv
     */
    @Test(dataProvider = "TestifyProvider")
    public void testCreateUser(String caseId, PrepareData prepareData) {
        runTest(caseId, prepareData);
    }

    /**
     * Test finding a user by username.
     */
    @Test(dataProvider = "TestifyProvider")
    public void testFindByUsername(String caseId, PrepareData prepareData) {
        runTest(caseId, prepareData);
    }

    /**
     * Direct test method that can be called by the test execution engine.
     */
    public User createUser(User user) {
        return userService.createUser(user);
    }

    /**
     * Direct test method for finding users.
     */
    public User findByUsername(String username) {
        return userService.findByUsername(username).orElse(null);
    }
}
