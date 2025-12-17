package com.github.loadup.testify.example.service;

/*-
 * #%L
 * LoadUp Testify - Example
 * %%
 * Copyright (C) 2026 LoadUp Cloud
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import com.github.loadup.testify.example.model.Role;
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
