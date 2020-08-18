package ru.otus.diagnostic.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.diagnostic.dao.OrderRepository;
import ru.otus.diagnostic.feign.StorageServiceProxy;
import ru.otus.diagnostic.model.Order;
import ru.otus.diagnostic.service.OrderService;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

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
    public String setInventoriesToOrder(Map<String, Integer> partsAndCount) {
        return storageServiceProxy.setInventoriesToOrder(partsAndCount);
    }

    @Override
    public Order addOrder(Map<String, Integer> partsAndCount) {
        String readyPartsAndCount = storageServiceProxy.setInventoriesToOrder(partsAndCount);
        Order order = new Order();
        order.setPartsAndCount(readyPartsAndCount);
        order.setJobTime(LocalDateTime.now().plusHours(2));
        return orderRepository.save(order);
    }



    private void sleepRandomly() {
        Random rand = new Random();
        int randomNum = rand.nextInt(3) + 1;
        if(randomNum == 3) {
            log.info("It is a chance for demonstrating Hystrix action");
            try {
                log.info("Start sleeping...." + System.currentTimeMillis());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.info("Hystrix thread interupted...." + System.currentTimeMillis());
                e.printStackTrace();
            }
        }
    }
}
