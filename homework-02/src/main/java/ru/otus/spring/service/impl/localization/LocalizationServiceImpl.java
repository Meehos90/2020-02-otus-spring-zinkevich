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

    public static String language = "english";

    public void choosenLang(String lang) {
        switch (lang) {
            case "english":
                break;
            case "russian":
                languageLocale = Locale.forLanguageTag("ru-RU");
                language = "russian";
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
    public String getNotFullNameMessage() { return messageSource.getMessage("message.empty.full.name", null, languageLocale); }

    @Override
    public String wrongAnswers() { return messageSource.getMessage("message.wrong.answer", null, languageLocale); }

    @Override
    public String incorrectAnswers(String name, int missedAnswers) {
        return messageSource.getMessage("message.incorrect.answers", new String[] {name, String.valueOf(missedAnswers)}, languageLocale);
    }

    @Override
    public String correctAnswers(String name) {
        return messageSource.getMessage("message.correct.answers", new String[] {name}, languageLocale);
    }

}
