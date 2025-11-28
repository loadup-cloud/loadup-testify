package com.loadup.testify.example.test.user;

import com.loadup.testify.core.annotation.TestBean;
import com.loadup.testify.core.base.TestifyTestBase;
import com.loadup.testify.core.model.PrepareData;
import com.loadup.testify.example.config.ExampleApplication;
import com.loadup.testify.example.model.Role;
import com.loadup.testify.example.model.User;
import com.loadup.testify.example.service.RoleService;
import com.loadup.testify.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Example test class demonstrating Testify data-driven testing for UserService.
 * 
 * <p>Directory structure convention:</p>
 * <pre>
 * com/loadup/testify/example/test/user/
 * ├── UserServiceTest.java                        (this test class)
 * ├── UserService.createUser/                     (method directory)
 * │   └── case01/                                 (case directory)
 * │       ├── test_config.yaml                    (test configuration)
 * │       ├── PrepareData/                        (CSV files for multiple tables)
 * │       │   └── table_users.csv
 * │       └── ExpectedData/                       (CSV files for multiple tables)
 * │           └── table_users.csv
 * └── UserService.createUserWithRole/             (another method directory)
 *     └── case01/
 * </pre>
 * 
 * <p>This test class shows how to:</p>
 * <ol>
 *   <li>Use the @TestBean annotation to mark the service under test</li>
 *   <li>Use the TestifyProvider for data-driven tests</li>
 *   <li>Prepare database data from multiple CSV files</li>
 *   <li>Execute test methods with complex parameters</li>
 *   <li>Assert database state after test execution</li>
 *   <li>Use Datafaker for random data generation</li>
 *   <li>Reference captured variables across test phases</li>
 *   <li>Mock external dependencies using Mockito</li>
 *   <li>Test methods with multiple parameters</li>
 *   <li>Test nested complex objects (User with Role)</li>
 * </ol>
 */
@SpringBootTest(classes = ExampleApplication.class)
@ActiveProfiles("test")
public class UserServiceTest extends TestifyTestBase {

    /**
     * The service under test, marked with @TestBean annotation.
     * The service name "UserService" is derived from this field's type.
     */
    @TestBean
    @Autowired
    private UserService userService;

    /**
     * Mock the RoleService to demonstrate external dependency mocking.
     */
    @MockitoBean
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
     * 
     * <p>Method name "testCreateUser" → "createUser"</p>
     * <p>Test data is loaded from: UserService.createUser/case01/</p>
     */
    @Test(dataProvider = "TestifyProvider")
    public void testCreateUser(String caseId, PrepareData prepareData) {
        runTest(caseId, prepareData);
    }

    /**
     * Test creating a user with role - demonstrates multiple parameters and mocking.
     * 
     * <p>Method name "testCreateUserWithRole" → "createUserWithRole"</p>
     * <p>Test data is loaded from: UserService.createUserWithRole/case01/</p>
     * <p>The RoleService is mocked in beforeTestMethod().</p>
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
