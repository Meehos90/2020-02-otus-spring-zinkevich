package ru.otus.spring.service.impl;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.logging.Logger;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.MessageService;
import ru.otus.spring.service.UserService;

@Repository
@RequiredArgsConstructor
public class ConsoleUserService implements UserService {
    private final IOService consoleIOService;
    private final MessageService messageService;

    @Logger
    @Override
    public String getUserInfo() {
        consoleIOService.showMessage(messageService.getLocaleMessage("message.greeting", null));
        String name = consoleIOService.getMessage();
        if (name.isEmpty()) {
            consoleIOService.showMessage(messageService.getLocaleMessage("message.empty.full.name", null));
            return getUserInfo();
        }
        return name;
    }
}
