package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.logging.Logger;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.MessageService;
import ru.otus.spring.service.UserService;

@Service
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
