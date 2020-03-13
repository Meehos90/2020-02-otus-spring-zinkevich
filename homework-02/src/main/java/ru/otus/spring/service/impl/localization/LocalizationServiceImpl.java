package ru.otus.spring.service.impl.localization;

import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import ru.otus.spring.dao.CsvQuestionsDao;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.service.LocalizationService;

public class LocalizationServiceImpl implements LocalizationService {

    private final Locale locale;
    private final Map<String, String> languages;


    public LocalizationServiceImpl(Locale locale, Map<String, String> languages) {
        this.locale = locale;
        this.languages = languages;
    }

    @Override
    public Locale getLanguageLocale() {
        if(locale != null) {
            return locale;
        }
        return Locale.ENGLISH;
    }

    @Override
    public QuestionsDao getCsvFile() {
        for (Entry<String, String> entry : languages.entrySet()) {
            String k = entry.getKey();
            if (locale != null && locale.toString().equals(k)) {
                return new CsvQuestionsDao("csv/questions_" + k + ".csv");
            }
        }
        return new CsvQuestionsDao("csv/questions_en_US.csv");
    }
}
