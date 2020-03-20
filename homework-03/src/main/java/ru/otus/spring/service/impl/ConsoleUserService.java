package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.logging.Logger;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.MessageService;
import ru.otus.spring.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsoleUserService implements UserService {
    private final IOService consoleIOService;
    private final MessageService messageService;

    @Logger
    @Override
    public String getUserInfo() {
        consoleIOService.showMessage(messageService.getLocaleMessage("message.greeting"));
        String name = consoleIOService.getMessage();
        if (name.isEmpty()) {
            log.warn("Name is empty");
            consoleIOService.showMessage(messageService.getLocaleMessage("message.empty.full.name"));
            return getUserInfo();
        }
        log.info("User name is {}", name);
        return name;
    }
}
