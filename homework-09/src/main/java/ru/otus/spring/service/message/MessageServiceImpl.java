package ru.otus.spring.service.message;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageSource messageSource;

    @Override
    public String getLocaleMessage(String message, Object... objects) {
        return messageSource.getMessage(message, objects, LocaleContextHolder.getLocale());
    }
}
