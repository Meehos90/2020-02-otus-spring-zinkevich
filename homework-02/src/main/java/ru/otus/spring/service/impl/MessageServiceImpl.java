package ru.otus.spring.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import ru.otus.spring.logging.Logger;
import ru.otus.spring.service.LocalizationService;
import ru.otus.spring.service.MessageService;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageSource messageSource;

    private final LocalizationService localizationService;

    public MessageServiceImpl(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    @Logger
    @Override
    public String getLocaleMessage(String message, Object[] objects) {
        return messageSource.getMessage(message, objects, localizationService.getLanguageLocale());
    }
}
