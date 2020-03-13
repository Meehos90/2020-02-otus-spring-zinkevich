package ru.otus.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import ru.otus.spring.service.LocalizationService;
import ru.otus.spring.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageSource messageSource;

    private final LocalizationService localizationService;

    public MessageServiceImpl(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    @Override
    public String getLocaleMessage(String message, Object[] objects) {
        return messageSource.getMessage(message, objects, localizationService.getLanguageLocale());
    }
}
