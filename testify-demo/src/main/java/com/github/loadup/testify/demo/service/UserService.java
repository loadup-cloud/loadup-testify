package com.github.loadup.testify.demo.service;

import com.github.loadup.testify.demo.model.Order;
import com.github.loadup.testify.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/** User service for demo - demonstrates integration testing with Testify. */
@Service
public class UserService {

  private final JdbcTemplate jdbcTemplate;
  @Autowired public OrderService orderService;

  public UserService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  /** Create a new user. */
  public User createUser(String userId, String userName, String email) {
    long createdAt = System.currentTimeMillis();

    String sql =
        "INSERT INTO users (user_id, user_name, email, status, created_at) VALUES (?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql, userId, userName, email, "ACTIVE", createdAt);
    User user = new User(userId, userName, email);
    if (userId.equals("test-12345")) {
      throw new IllegalArgumentException("User ID cannot be empty");
    }
    System.out.println(">>> [EXEC] OrderService instance: " + System.identityHashCode(orderService));
    Order order = orderService.createOrder(userName);
    user.setOrder(order);
    return user;
  }

  /** Get user by ID. */
  public User getUserById(String userId) {
    String sql = "SELECT * FROM users WHERE user_id = ?";
    return jdbcTemplate.queryForObject(
        sql,
        (rs, rowNum) -> {
          User user = new User();
          user.setUserId(rs.getString("user_id"));
          user.setUserName(rs.getString("user_name"));
          user.setEmail(rs.getString("email"));
          user.setStatus(rs.getString("status"));
          return user;
        },
        userId);
  }

  /** Update user status. */
  public void updateUserStatus(String userId, String newStatus) {
    String sql = "UPDATE users SET status = ? WHERE user_id = ?";
    jdbcTemplate.update(sql, newStatus, userId);
  }

  /** Delete user. */
  public void deleteUser(String userId) {
    String sql = "DELETE FROM users WHERE user_id = ?";
    jdbcTemplate.update(sql, userId);
  }
}
