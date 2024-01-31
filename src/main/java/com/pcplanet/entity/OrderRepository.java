package com.pcplanet.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
//    Order createOrder(User user, List<Product> products);
//    void saveOrder(Order order);
}
