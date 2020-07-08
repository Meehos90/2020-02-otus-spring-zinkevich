package ru.otus.spring.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface FordDealer {
    @Gateway(requestChannel = "ordersChannel")
    void selectModel(String model);
}
