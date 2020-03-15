package ru.otus.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.MessageService;
import ru.otus.spring.service.impl.localization.LocalizationProperties;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocalizationProperties localProps;

    @Override
    public String getLocaleMessage(String message, Object ...objects) {
        return messageSource.getMessage(message, objects, localProps.getLanguageLocale());
    }
}
