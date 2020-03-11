package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.service.LocalizationService;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.UserService;

@Repository
@RequiredArgsConstructor
public class ConsoleUserService implements UserService {
    private final IOService consoleIOService;
    private final LocalizationService localizationService;

    public String getUserInfo() {
        consoleIOService.showMessage(localizationService.greeting());
        String name = consoleIOService.getMessage();
        if (name.isEmpty()) {
            consoleIOService.showMessage(localizationService.getNotFullNameMessage());
            return getUserInfo();
        }
        return name;
    }
}
