package ru.otus.spring.service.impl.localization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.InternationalizationService;

import java.util.Locale;

@Service
public class EngLocaleService implements InternationalizationService {
    @Autowired
    private MessageSource messageSource;

    @Override
    public String greeting() {
       return messageSource.getMessage("message.greeting", null, Locale.ENGLISH);
    }
}
