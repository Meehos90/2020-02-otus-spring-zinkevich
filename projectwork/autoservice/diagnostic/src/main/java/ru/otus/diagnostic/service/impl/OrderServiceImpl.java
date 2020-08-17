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
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "7000")
    })
    public Order addOrder(String article, Integer count) {
        String partsAndCount = storageServiceProxy.getInventoryOnStorage(article, count);
        Order order = new Order();
        order.setPartsAndCount(partsAndCount);
        order.setJobTime(LocalDateTime.now().plusHours(2));
        return orderRepository.save(order);
    }

    @Override
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "7000")
    })
    public String findArticleByParams(String partName, String autoMark, String autoModel, String autoYear) {
        sleepRandomly();
        return storageServiceProxy.findArticleByParams(partName, autoMark, autoModel, autoYear);
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
