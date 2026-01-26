package io.github.loadup.testify.demo.service;

import static org.testng.Assert.*;

import io.github.loadup.testify.core.annotation.TestifyParam;
import io.github.loadup.testify.demo.DemoApplication;
import io.github.loadup.testify.demo.model.User;
import io.github.loadup.testify.starter.base.TestifyBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testng.annotations.Test;

/**
 * Integration test for UserService demonstrating YAML-driven testing with Testify.
 *
 * <p>This test uses: - YAML-defined test data and variables - Automatic database setup and cleanup
 * - Database assertions with rich diff reporting - Zero-code test logic (just method signature)
 */
@SpringBootTest(classes = DemoApplication.class)
@ActiveProfiles("test")
public class UserServiceTest extends TestifyBase {

  @Autowired private UserService userService;

  @Autowired private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;

  /**
   * YAML-driven test for createUser method. Test data, setup, and assertions are defined in
   * testCreateUser.yaml
   */
  @Test(
      dataProvider = "testifyData")
  public void testCreateUser(@TestifyParam("users") User user) {
    // Test logic is automatically executed by TestifyListener
    // - Variables are resolved from YAML
    // - Database is cleaned up before test
    // - Method parameters are injected from YAML input
    // - Database assertions are verified after test

    // The actual service call happens automatically based on YAML configuration
    System.out.println(
        "Local Field Hash: " + System.identityHashCode(this.userService.orderService));
    runTest(() -> userService.createUser(user));
    //    Reporter.getCurrentTestResult().setAttribute("actualResponse", res);
  }

  /** Traditional test for updateUserStatus (can coexist with YAML tests). */
  @Test
  public void testUpdateUserStatus() {
    // Arrange
    String userId = "test-user-update-456";

    // Cleanup and setup
    jdbcTemplate.execute("DELETE FROM users WHERE user_id = '" + userId + "'");
    jdbcTemplate.update(
        "INSERT INTO users (user_id, user_name, email, status, created_at) VALUES (?, ?, ?, ?, ?)",
        userId,
        "Jane Smith",
        "jane@example.com",
        "ACTIVE",
        System.currentTimeMillis());

    // Act
    userService.updateUserStatus(userId, "INACTIVE");

    // Assert
    String status =
        jdbcTemplate.queryForObject(
            "SELECT status FROM users WHERE user_id = ?", String.class, userId);
    assertEquals(status, "INACTIVE", "Status should be updated to INACTIVE");
  }

  /** Traditional test for deleteUser. */
  @Test
  public void testDeleteUser() {
    // Arrange
    String userId = "test-user-delete-789";

    // Setup
    jdbcTemplate.update(
        "INSERT INTO users (user_id, user_name, email, status, created_at) VALUES (?, ?, ?, ?, ?)",
        userId,
        "Bob Wilson",
        "bob@example.com",
        "ACTIVE",
        System.currentTimeMillis());

    // Act
    userService.deleteUser(userId);

    // Assert
    Integer count =
        jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM users WHERE user_id = ?", Integer.class, userId);
    assertEquals(count, 0, "User should be deleted");
  }
}
