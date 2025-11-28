package com.github.loadup.testify.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Example Order model for demonstrating complex object handling.
 * 
 * <p>The extraInfo field demonstrates JSON String large field comparison.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id;
    private Long userId;
    private String orderNumber;
    private BigDecimal totalAmount;
    private String status;
    
    /**
     * JSON String field for storing extra order information.
     * Demonstrates how to compare JSON String fields in assertions.
     * Example: {"shippingAddress": "123 Main St", "notes": "Leave at door"}
     */
    private String extraInfo;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
