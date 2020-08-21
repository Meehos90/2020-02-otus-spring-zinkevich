package ru.otus.diagnostic.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.diagnostic.dao.OrderRepository;
import ru.otus.diagnostic.feign.StorageService;
import ru.otus.diagnostic.model.Order;
import ru.otus.diagnostic.model.PreOrder;
import ru.otus.diagnostic.service.OrderService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final StorageService storageService;

    @Transactional
    @Override
    public Order findOrderById(Long orderId) {
        return orderRepository.findOrderById(orderId);
    }

    @Transactional
    @Override
    public String findArticleByParams(String partName, String autoMark, String autoModel, String autoYear) {
        return storageService.findArticleByParams(partName, autoMark, autoModel, autoYear);
    }

    @Transactional
    @Override
    public String checkInventoriesOnStorage(Map<String, Integer> partsAndCount) {
        return storageService.checkInventoriesOnStorage(partsAndCount);
    }

    @Transactional
    @Override
    public Order addOrder(PreOrder preOrder) {
        String readyPartsAndCount = storageService.setInventoriesToOrder(preOrder.getPartsAndCount());
        Order order = new Order();
        order.setPartsAndCount(readyPartsAndCount);
        order.setJobTime(LocalDateTime.now().plusHours(preOrder.getAfterWhatTime()).withNano(0));
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Map<String, String> findOrderDetails() {
        Map<String, String> orderDetails = new HashMap<>();
        List<Order> orders = findAll();
        orders.forEach(order -> {
            orderDetails.put(String.valueOf(order.getId()), order.getPartsAndCount());
        });
        return orderDetails;
    }
}
