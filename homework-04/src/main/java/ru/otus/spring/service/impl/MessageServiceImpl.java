package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.LocalizationProperties;
import ru.otus.spring.service.MessageService;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageSource messageSource;
    private final LocalizationProperties localProps;

    @Override
    public String getLocaleMessage(String message, Object ...objects) {
        return messageSource.getMessage(message, objects, localProps.getLanguageLocale());
    }
}
