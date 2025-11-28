package com.loadup.testify.example.service;

import com.loadup.testify.example.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Example Order service for demonstrating JSON field handling and datetime tolerance.
 */
@Slf4j
@Service
public class OrderService {

    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs, rowNum) -> Order.builder()
            .id(rs.getLong("id"))
            .userId(rs.getLong("user_id"))
            .orderNumber(rs.getString("order_number"))
            .totalAmount(rs.getBigDecimal("total_amount"))
            .status(rs.getString("status"))
            .extraInfo(rs.getString("extra_info"))
            .createdAt(rs.getTimestamp("created_at") != null ? 
                    rs.getTimestamp("created_at").toLocalDateTime() : null)
            .updatedAt(rs.getTimestamp("updated_at") != null ? 
                    rs.getTimestamp("updated_at").toLocalDateTime() : null)
            .build();

    public OrderService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Create a new order.
     *
     * @param order the order to create
     * @return the created order with ID
     */
    @Transactional
    public Order createOrder(Order order) {
        log.info("Creating order: {} for user: {}", order.getOrderNumber(), order.getUserId());
        
        LocalDateTime now = LocalDateTime.now();
        order.setCreatedAt(now);
        order.setUpdatedAt(now);
        
        String sql = "INSERT INTO orders (user_id, order_number, total_amount, status, extra_info, created_at, updated_at) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        jdbcTemplate.update(sql,
                order.getUserId(),
                order.getOrderNumber(),
                order.getTotalAmount(),
                order.getStatus() != null ? order.getStatus() : "PENDING",
                order.getExtraInfo(),
                order.getCreatedAt(),
                order.getUpdatedAt());

        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        order.setId(id);

        return order;
    }

    /**
     * Create an order with extra info JSON field.
     * Demonstrates JSON String field handling.
     *
     * @param userId the user ID
     * @param orderNumber the order number
     * @param totalAmount the total amount
     * @param extraInfo the extra info as JSON string
     * @return the created order
     */
    @Transactional
    public Order createOrderWithExtraInfo(Long userId, String orderNumber, BigDecimal totalAmount, String extraInfo) {
        log.info("Creating order with extraInfo: {} for user: {}", orderNumber, userId);
        
        Order order = Order.builder()
                .userId(userId)
                .orderNumber(orderNumber)
                .totalAmount(totalAmount)
                .status("PENDING")
                .extraInfo(extraInfo)
                .build();
        
        return createOrder(order);
    }

    /**
     * Find an order by ID.
     *
     * @param id the order ID
     * @return the order, or empty if not found
     */
    public Optional<Order> findById(Long id) {
        log.info("Finding order by ID: {}", id);
        
        String sql = "SELECT * FROM orders WHERE id = ?";
        List<Order> orders = jdbcTemplate.query(sql, ORDER_ROW_MAPPER, id);
        
        return orders.isEmpty() ? Optional.empty() : Optional.of(orders.get(0));
    }

    /**
     * Find an order by order number.
     *
     * @param orderNumber the order number
     * @return the order, or empty if not found
     */
    public Optional<Order> findByOrderNumber(String orderNumber) {
        log.info("Finding order by order number: {}", orderNumber);
        
        String sql = "SELECT * FROM orders WHERE order_number = ?";
        List<Order> orders = jdbcTemplate.query(sql, ORDER_ROW_MAPPER, orderNumber);
        
        return orders.isEmpty() ? Optional.empty() : Optional.of(orders.get(0));
    }

    /**
     * Find all orders for a user.
     *
     * @param userId the user ID
     * @return list of orders
     */
    public List<Order> findByUserId(Long userId) {
        log.info("Finding orders for user: {}", userId);
        
        String sql = "SELECT * FROM orders WHERE user_id = ?";
        return jdbcTemplate.query(sql, ORDER_ROW_MAPPER, userId);
    }

    /**
     * Update an order's status.
     *
     * @param id the order ID
     * @param status the new status
     * @return the updated order
     */
    @Transactional
    public Order updateStatus(Long id, String status) {
        log.info("Updating order {} status to: {}", id, status);
        
        LocalDateTime now = LocalDateTime.now();
        String sql = "UPDATE orders SET status = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, status, now, id);
        
        return findById(id).orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }

    /**
     * Update an order's extra info.
     *
     * @param id the order ID
     * @param extraInfo the new extra info JSON
     * @return the updated order
     */
    @Transactional
    public Order updateExtraInfo(Long id, String extraInfo) {
        log.info("Updating order {} extra info", id);
        
        LocalDateTime now = LocalDateTime.now();
        String sql = "UPDATE orders SET extra_info = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, extraInfo, now, id);
        
        return findById(id).orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }
}
