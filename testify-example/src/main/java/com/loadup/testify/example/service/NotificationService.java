package com.loadup.testify.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * External service for sending notifications
 * This will be mocked in tests
 */
@Slf4j
@Service
public class NotificationService {

    public void sendWelcomeEmail(String email, String fullName) {
        log.info("Sending welcome email to: {} ({})", email, fullName);
        // In real implementation, this would send actual email
    }

    public void sendOrderConfirmation(String email, String orderNumber) {
        log.info("Sending order confirmation to: {} for order: {}", email, orderNumber);
        // In real implementation, this would send actual email
    }
}

