package io.github.loadup.testify.demo.service;

import io.github.loadup.testify.core.annotation.TestifyParam;
import io.github.loadup.testify.demo.Application;
import io.github.loadup.testify.demo.model.User;
import io.github.loadup.testify.starter.base.TestifyBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Integration test for UserService demonstrating YAML-driven testing with Testify.
 *
 * <p>This test uses: - YAML-defined test data and variables - Automatic database setup and cleanup
 * - Database assertions with rich diff reporting - Zero-code test logic (just method signature)
 */
@SpringBootTest(classes = Application.class)
public class UserServiceTest extends TestifyBase {

    @Autowired
    private UserService userService;

    @Autowired
    private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;

    /**
     * YAML-driven test for createUser method. Test data, setup, and assertions are defined in
     * testCreateUser.yaml
     */
    @Test(dataProvider = "testifyData")
    public void testCreateUserWithParam(@TestifyParam("users") User user) {
        runTest(() -> userService.createUser(user));
    }

    @Test(dataProvider = "testifyData")
    public void testCreateUserWithOutParam(User user) {
        runTest(() -> userService.createUser(user));
    }

    @Test(dataProvider = "testifyData")
    public void testCreateUserWithException(User user) {
        runTest(() -> userService.createUser(user));
    }

    @Test(dataProvider = "testifyData")
    public void testGetUserWithSetup(String id) {
        runTest(() -> userService.getUserById(id));
    }

//    /**
//     * Traditional test for updateUserStatus (can coexist with YAML tests).
//     */
//    @Test
//    public void testUpdateUserStatus() {
//        // Arrange
//        String userId = "test-user-update-456";
//
//        // Cleanup and setup
//        jdbcTemplate.execute("DELETE FROM users WHERE user_id = '" + userId + "'");
//        jdbcTemplate.update(
//                "INSERT INTO users (user_id, user_name, email, status, created_at) VALUES (?, ?, ?, ?, ?)",
//                userId,
//                "Jane Smith",
//                "jane@example.com",
//                "ACTIVE",
//                System.currentTimeMillis());
//
//        // Act
//        userService.updateUserStatus(userId, "INACTIVE");
//
//        // Assert
//        String status =
//                jdbcTemplate.queryForObject(
//                        "SELECT status FROM users WHERE user_id = ?", String.class, userId);
//        assertEquals(status, "INACTIVE", "Status should be updated to INACTIVE");
//    }
//
//    /**
//     * Traditional test for deleteUser.
//     */
//    @Test
//    public void testDeleteUser() {
//        // Arrange
//        String userId = "test-user-delete-789";
//
//        // Setup
//        jdbcTemplate.update(
//                "INSERT INTO users (user_id, user_name, email, status, created_at) VALUES (?, ?, ?, ?, ?)",
//                userId,
//                "Bob Wilson",
//                "bob@example.com",
//                "ACTIVE",
//                System.currentTimeMillis());
//
//        // Act
//        userService.deleteUser(userId);
//
//        // Assert
//        Integer count =
//                jdbcTemplate.queryForObject(
//                        "SELECT COUNT(*) FROM users WHERE user_id = ?", Integer.class, userId);
//        assertEquals(count, 0, "User should be deleted");
//    }
}
