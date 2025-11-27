package com.loadup.testify.example.service;

import com.loadup.testify.example.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Example User service for demonstrating Testify functionality.
 */
@Slf4j
@Service
public class UserService {

    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<User> USER_ROW_MAPPER = (rs, rowNum) -> User.builder()
            .id(rs.getLong("id"))
            .username(rs.getString("username"))
            .email(rs.getString("email"))
            .firstName(rs.getString("first_name"))
            .lastName(rs.getString("last_name"))
            .age(rs.getInt("age"))
            .active(rs.getBoolean("active"))
            .build();

    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Create a new user.
     *
     * @param user the user to create
     * @return the created user with ID
     */
    @Transactional
    public User createUser(User user) {
        log.info("Creating user: {}", user.getUsername());
        
        String sql = "INSERT INTO users (username, email, first_name, last_name, age, active) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        jdbcTemplate.update(sql,
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getAge(),
                user.getActive() != null ? user.getActive() : true);

        // Get the generated ID
        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        user.setId(id);

        return user;
    }

    /**
     * Find a user by ID.
     *
     * @param id the user ID
     * @return the user, or empty if not found
     */
    public Optional<User> findById(Long id) {
        log.info("Finding user by ID: {}", id);
        
        String sql = "SELECT * FROM users WHERE id = ?";
        List<User> users = jdbcTemplate.query(sql, USER_ROW_MAPPER, id);
        
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    /**
     * Find a user by username.
     *
     * @param username the username
     * @return the user, or empty if not found
     */
    public Optional<User> findByUsername(String username) {
        log.info("Finding user by username: {}", username);
        
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql, USER_ROW_MAPPER, username);
        
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    /**
     * Find all active users.
     *
     * @return list of active users
     */
    public List<User> findAllActive() {
        log.info("Finding all active users");
        
        String sql = "SELECT * FROM users WHERE active = true";
        return jdbcTemplate.query(sql, USER_ROW_MAPPER);
    }

    /**
     * Update a user.
     *
     * @param user the user with updated information
     * @return the updated user
     */
    @Transactional
    public User updateUser(User user) {
        log.info("Updating user: {}", user.getId());
        
        String sql = "UPDATE users SET username = ?, email = ?, first_name = ?, " +
                     "last_name = ?, age = ?, active = ? WHERE id = ?";
        
        jdbcTemplate.update(sql,
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getAge(),
                user.getActive(),
                user.getId());

        return user;
    }

    /**
     * Delete a user by ID.
     *
     * @param id the user ID
     * @return true if deleted, false if not found
     */
    @Transactional
    public boolean deleteUser(Long id) {
        log.info("Deleting user: {}", id);
        
        String sql = "DELETE FROM users WHERE id = ?";
        int rows = jdbcTemplate.update(sql, id);
        
        return rows > 0;
    }

    /**
     * Count users by age range.
     *
     * @param minAge the minimum age
     * @param maxAge the maximum age
     * @return the count of users in the age range
     */
    public int countByAgeRange(int minAge, int maxAge) {
        log.info("Counting users by age range: {} - {}", minAge, maxAge);
        
        String sql = "SELECT COUNT(*) FROM users WHERE age >= ? AND age <= ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, minAge, maxAge);
        
        return count != null ? count : 0;
    }
}
