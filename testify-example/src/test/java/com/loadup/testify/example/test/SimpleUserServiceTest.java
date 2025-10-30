package com.loadup.testify.example.test;

import com.loadup.testify.example.ExampleApplication;
import com.loadup.testify.example.dto.CreateUserRequest;
import com.loadup.testify.example.dto.UserResponse;
import com.loadup.testify.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Simple test for UserService
 */
@SpringBootTest(classes = ExampleApplication.class)
public class SimpleUserServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserService userService;

    @Test
    public void testCreateUserSimple() {
        // Arrange
        CreateUserRequest request = new CreateUserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        request.setFullName("Test User");

        // Act
        UserResponse result = userService.createUser(request);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(result.getUsername(), "testuser");
        assertEquals(result.getEmail(), "test@example.com");
        assertEquals(result.getFullName(), "Test User");
        assertTrue(result.getIsActive());

        System.out.println("✅ Test PASSED: User created successfully with ID: " + result.getId());
    }

    @Test
    public void testCreateUserDuplicateUsernameShouldFail() {
        // Arrange - create first user
        CreateUserRequest request1 = new CreateUserRequest();
        request1.setUsername("duplicateuser");
        request1.setEmail("user1@example.com");
        request1.setPassword("password123");
        request1.setFullName("User One");

        userService.createUser(request1);

        // Arrange - try to create duplicate
        CreateUserRequest request2 = new CreateUserRequest();
        request2.setUsername("duplicateuser");  // Same username
        request2.setEmail("user2@example.com");
        request2.setPassword("password456");
        request2.setFullName("User Two");

        // Act & Assert
        try {
            userService.createUser(request2);
            fail("Expected IllegalArgumentException for duplicate username");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("already exists"));
            System.out.println("✅ Test PASSED: Duplicate username correctly rejected");
        }
    }
}

