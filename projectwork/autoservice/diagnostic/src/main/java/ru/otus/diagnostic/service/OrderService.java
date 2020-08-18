package ru.otus.diagnostic.service;

import ru.otus.diagnostic.model.Order;

import java.util.Map;

public interface OrderService {

    Order findOrderById(Long orderId);

    Order addOrder(Map<String, Integer> partsAndCount);

    String findArticleByParams(String partName, String autoMark, String autoModel, String autoYear);

    String checkInventoriesOnStorage(Map<String, Integer> partsAndCount);

    String setInventoriesToOrder(Map<String, Integer> partsAndCount);

}
