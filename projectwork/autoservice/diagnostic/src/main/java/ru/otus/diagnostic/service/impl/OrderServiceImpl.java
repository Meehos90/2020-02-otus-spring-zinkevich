package ru.otus.diagnostic.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.diagnostic.dao.OrderRepository;
import ru.otus.diagnostic.feign.StorageServiceProxy;
import ru.otus.diagnostic.model.Order;
import ru.otus.diagnostic.model.PreOrder;
import ru.otus.diagnostic.service.OrderService;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final StorageServiceProxy storageServiceProxy;

    @Override
    public Order findOrderById(Long orderId) {
        return orderRepository.findOrderById(orderId);
    }

    @Override
    public String findArticleByParams(String partName, String autoMark, String autoModel, String autoYear) {
        return storageServiceProxy.findArticleByParams(partName, autoMark, autoModel, autoYear);
    }

    @Override
    public String checkInventoriesOnStorage(Map<String, Integer> partsAndCount) {
        return storageServiceProxy.checkInventoriesOnStorage(partsAndCount);
    }

    @Override
    public Order addOrder(PreOrder preOrder) {
        String readyPartsAndCount = storageServiceProxy.setInventoriesToOrder(preOrder.getPartsAndCount());
        Order order = new Order();
        order.setPartsAndCount(readyPartsAndCount);
        order.setJobTime(LocalDateTime.now().plusHours(preOrder.getAfterWhatTime()).withNano(0));
        return orderRepository.save(order);
    }

}
