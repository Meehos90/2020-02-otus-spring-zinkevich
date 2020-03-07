package ru.otus.spring.service.impl.localization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.LocalizationService;

import java.util.Locale;

@Service
public class LocalizationServiceImpl implements LocalizationService {
    @Autowired
    private MessageSource messageSource;

    private Locale languageLocale = Locale.ENGLISH;

    public void choosenLang(String lang) {
        switch (lang) {
            case "english":
                break;
            case "russian":
                languageLocale = Locale.forLanguageTag("ru-RU");
                break;
            default:
                break;
        }
    }

    @Override
    public String greeting() {
       return messageSource.getMessage("message.greeting", null, languageLocale);
    }

    @Override
    public String notFullName() { return messageSource.getMessage("message.empty.full.name", null, languageLocale); }
}
