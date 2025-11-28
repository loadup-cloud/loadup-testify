package com.loadup.testify.example;

import com.loadup.testify.core.annotation.TestBean;
import com.loadup.testify.core.base.TestifyTestBase;
import com.loadup.testify.core.model.PrepareData;
import com.loadup.testify.example.config.ExampleApplication;
import com.loadup.testify.example.model.Role;
import com.loadup.testify.example.model.User;
import com.loadup.testify.example.service.RoleService;
import com.loadup.testify.example.service.UserService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Example test class demonstrating Testify data-driven testing.
 * 
 * This test class shows how to:
 * 1. Use the @TestBean annotation to mark the service under test
 * 2. Use the TestifyProvider for data-driven tests
 * 3. Prepare database data from CSV files
 * 4. Execute test methods with complex parameters
 * 5. Assert database state after test execution
 * 6. Use Datafaker for random data generation
 * 7. Reference captured variables across test phases
 * 8. Mock external dependencies using Mockito
 * 9. Test methods with multiple parameters
 * 10. Test nested complex objects (User with Role)
 */
@SpringBootTest(classes = ExampleApplication.class)
@ActiveProfiles("test")
public class UserServiceTest extends TestifyTestBase {

    /**
     * The service under test, marked with @TestBean annotation.
     * No need to override getTestBean() anymore!
     */
    @TestBean
    @Autowired
    private UserService userService;

    /**
     * Mock the RoleService to demonstrate external dependency mocking.
     */
    @MockBean
    private RoleService roleService;

    @BeforeMethod
    @Override
    protected void beforeTestMethod() {
        // Setup mock behavior for RoleService
        Role mockRole = Role.builder()
                .id(1L)
                .name("USER")
                .description("Default user role")
                .active(true)
                .build();
        
        when(roleService.findByName(anyString())).thenReturn(Optional.of(mockRole));
        when(roleService.createRole(any(Role.class))).thenAnswer(invocation -> {
            Role role = invocation.getArgument(0);
            role.setId(1L);
            return role;
        });
    }

    /**
     * Test creating a user with data-driven test cases.
     * Method name "testCreateUser" will be normalized to "createUser"
     * so no need to specify method in yaml config.
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
     * Test creating a user with role - demonstrates multiple parameters and mocking.
     * Method name "testCreateUserWithRole" will be normalized to "createUserWithRole".
     * 
     * Test data is loaded from case02 which specifies method: "createUserWithRole".
     * The RoleService is mocked in beforeTestMethod().
     */
    @Test(dataProvider = "TestifyProvider")
    public void testCreateUserWithRole(String caseId, PrepareData prepareData) {
        runTest(caseId, prepareData);
    }

    /**
     * Create a new user - method invoked by the test execution engine.
     */
    public User createUser(User user) {
        return userService.createUser(user);
    }

    /**
     * Create a new user with role - demonstrates multiple parameters.
     * This method will be called when testing createUserWithRole.
     * 
     * @param user the user to create
     * @param roleName the role name to assign
     * @return the created user with role
     */
    public User createUserWithRole(User user, String roleName) {
        return userService.createUserWithRole(user, roleName);
    }

    /**
     * Find a user by username.
     */
    public User findByUsername(String username) {
        return userService.findByUsername(username).orElse(null);
    }
}
