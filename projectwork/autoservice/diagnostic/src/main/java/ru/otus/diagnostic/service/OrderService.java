package ru.otus.diagnostic.service;

import ru.otus.diagnostic.model.Order;
import ru.otus.diagnostic.model.PreOrder;

import java.util.Map;

public interface OrderService {

    Order findOrderById(Long orderId);

    Order addOrder(PreOrder preOrder);

    String findArticleByParams(String partName, String autoMark, String autoModel, String autoYear);

    String checkInventoriesOnStorage(Map<String, Integer> partsAndCount);

}
