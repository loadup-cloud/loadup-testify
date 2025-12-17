package com.github.loadup.testify.example.model;

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
