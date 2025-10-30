package com.loadup.testify.example.service;

import com.loadup.testify.example.dto.CreateOrderRequest;
import com.loadup.testify.example.entity.Order;
import com.loadup.testify.example.entity.User;
import com.loadup.testify.example.repository.OrderRepository;
import com.loadup.testify.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    @Transactional
    public Order createOrder(CreateOrderRequest request) {
        log.info("Creating order for user: {}", request.getUserId());

        // Validate user exists
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + request.getUserId()));

        // Create order
        Order order = new Order();
        order.setUserId(user.getId());
        order.setOrderNumber("ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        order.setAmount(request.getAmount());
        order.setStatus(Order.OrderStatus.PENDING);

        order = orderRepository.save(order);

        // Send confirmation email
        notificationService.sendOrderConfirmation(user.getEmail(), order.getOrderNumber());

        return order;
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));
    }
}

