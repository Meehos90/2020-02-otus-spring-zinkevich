package ru.otus.diagnostic.service;

import ru.otus.diagnostic.model.Order;

public interface OrderService {

    Order findOrderById(Long orderId);

    Order addOrder(String article, Integer count);

    String findArticleByParams(String partName, String autoMark, String autoModel, String autoYear);

}
