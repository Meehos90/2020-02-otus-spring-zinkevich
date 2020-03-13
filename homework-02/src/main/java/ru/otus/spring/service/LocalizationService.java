package ru.otus.spring.service;

import java.util.Locale;

import ru.otus.spring.dao.QuestionsDao;

public interface LocalizationService {
    Locale getLanguageLocale();
    QuestionsDao getCsvFile();
}
