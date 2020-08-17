package ru.otus.diagnostic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.diagnostic.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderById(Long orderId);
}
