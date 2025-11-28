package com.loadup.testify.example.service;

import com.loadup.testify.example.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Example Role service for demonstrating Mockito mocking in Testify.
 */
@Slf4j
@Service
public class RoleService {

    /**
     * Find a role by ID.
     *
     * @param id the role ID
     * @return the role, or empty if not found
     */
    public Optional<Role> findById(Long id) {
        log.info("Finding role by ID: {}", id);
        // This would typically query the database
        // For demonstration, we return empty - will be mocked in tests
        return Optional.empty();
    }

    /**
     * Find a role by name.
     *
     * @param name the role name
     * @return the role, or empty if not found
     */
    public Optional<Role> findByName(String name) {
        log.info("Finding role by name: {}", name);
        // This would typically query the database
        // For demonstration, we return empty - will be mocked in tests
        return Optional.empty();
    }

    /**
     * Create a new role.
     *
     * @param role the role to create
     * @return the created role with ID
     */
    public Role createRole(Role role) {
        log.info("Creating role: {}", role.getName());
        // This would typically insert into the database
        // For demonstration, we just return the role - will be mocked in tests
        return role;
    }
}
