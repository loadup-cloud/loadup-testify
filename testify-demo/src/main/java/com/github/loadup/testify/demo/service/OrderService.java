package com.github.loadup.testify.demo.service;

import com.github.loadup.testify.demo.model.Order;
import org.springframework.stereotype.Service;

/** User service for demo - demonstrates integration testing with Testify. */
@Service
public class OrderService {

  /** Create a new user. */
  public Order createOrder(String userName) {
    Order order = new Order();
    order.setOrderId("12345");
    order.setOrderName(userName);
    return order;
  }
}
