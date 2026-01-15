package com.github.loadup.testify.starter.db;

import org.springframework.beans.factory.annotation.Value;

/**
 * Provides database connection from application properties.
 * Used when Testcontainers is disabled.
 */
public class PhysicalDbConnectionProvider implements DbConnectionProvider {

    @Value("${spring.datasource.url:jdbc:mysql://localhost:3306/testdb}")
    private String jdbcUrl;

    @Value("${spring.datasource.username:root}")
    private String username;

    @Value("${spring.datasource.password:}")
    private String password;

    @Override
    public String getJdbcUrl() {
        return jdbcUrl;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
