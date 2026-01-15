package com.github.loadup.testify.demo.service;

import com.github.loadup.testify.demo.DemoApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Integration test for UserService demonstrating Spring Boot + TestNG
 * integration.
 * 
 * This test shows that:
 * - Spring context loads properly with TestNG
 * -@Author wired beans work correctly
 * - Database operations execute successfully
 * - Service methods interact with H2 database
 */
@SpringBootTest(classes = DemoApplication.class)
@ActiveProfiles("test")
public class UserServiceTest extends AbstractTestNGSpringContextTests {

        @Autowired
        private UserService userService;

        @Autowired
        private JdbcTemplate jdbcTemplate;

        @Test(dataProvider = "testifyData", dataProviderClass = com.github.loadup.testify.starter.testng.TestifyDataProvider.class)
        public void testCreateUser() {
                // Arrange
                String userId = "test-user-123";
                String userName = "John Doe";
                String email = "john.doe@example.com";

                // Cleanup
                jdbcTemplate.execute("DELETE FROM users WHERE user_id = '" + userId + "'");

                // Act
                userService.createUser(userId, userName, email);

                // Assert
                Integer count = jdbcTemplate.queryForObject(
                                "SELECT COUNT(*) FROM users WHERE user_id = ?",
                                Integer.class,
                                userId);
                assertEquals(count, 1, "User should be created");

                String actualUserName = jdbcTemplate.queryForObject(
                                "SELECT user_name FROM users WHERE user_id = ?",
                                String.class,
                                userId);
                assertEquals(actualUserName, userName, "Username should match");
        }

        @Test
        public void testUpdateUserStatus() {
                // Arrange
                String userId = "test-user-update-456";

                // Cleanup and setup
                jdbcTemplate.execute("DELETE FROM users WHERE user_id = '" + userId + "'");
                jdbcTemplate.update(
                                "INSERT INTO users (user_id, user_name, email, status, created_at) VALUES (?, ?, ?, ?, ?)",
                                userId, "Jane Smith", "jane@example.com", "ACTIVE", System.currentTimeMillis());

                // Act
                userService.updateUserStatus(userId, "INACTIVE");

                // Assert
                String status = jdbcTemplate.queryForObject(
                                "SELECT status FROM users WHERE user_id = ?",
                                String.class,
                                userId);
                assertEquals(status, "INACTIVE", "Status should be updated to INACTIVE");
        }

        @Test
        public void testDeleteUser() {
                // Arrange
                String userId = "test-user-delete-789";

                // Setup
                jdbcTemplate.update(
                                "INSERT INTO users (user_id, user_name, email, status, created_at) VALUES (?, ?, ?, ?, ?)",
                                userId, "Bob Wilson", "bob@example.com", "ACTIVE", System.currentTimeMillis());

                // Act
                userService.deleteUser(userId);

                // Assert
                Integer count = jdbcTemplate.queryForObject(
                                "SELECT COUNT(*) FROM users WHERE user_id = ?",
                                Integer.class,
                                userId);
                assertEquals(count, 0, "User should be deleted");
        }
}
