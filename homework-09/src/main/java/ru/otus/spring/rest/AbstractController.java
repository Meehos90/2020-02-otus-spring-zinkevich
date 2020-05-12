package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.service.message.MessageService;

public class AbstractController {
    @Autowired
    public MessageService messageService;
}
