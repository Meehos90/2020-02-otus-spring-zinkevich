package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.MessageService;
import ru.otus.spring.service.impl.localization.LocalizationProperties;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageSource messageSource;

    private final LocalizationProperties localProps;

    @Override
    public String getLocaleMessage(String message, Object ...objects) {
        return messageSource.getMessage(message, objects, localProps.getLanguageLocale());
    }
}
