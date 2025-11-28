package com.loadup.testify.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Example User model for demonstrating Testify functionality.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Integer age;
    private Boolean active;
    
    /**
     * The user's role - demonstrates complex nested object mapping.
     */
    private Role role;
    
    /**
     * The timestamp when the user was created - demonstrates date/time tolerance comparison.
     */
    private LocalDateTime createdAt;
}
