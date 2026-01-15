package com.github.loadup.testify.demo.model;

/**
 * User entity for demo.
 */
public class User {
    private String userId;
    private String userName;
    private String email;
    private String status;
    private Long createdAt;

    public User() {
    }

    public User(String userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.status = "ACTIVE";
        this.createdAt = System.currentTimeMillis();
    }

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
