package ru.otus.diagnostic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.diagnostic.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderById(Long orderId);
    List<Order> findAll();
}
