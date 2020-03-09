package ru.otus.spring.service;

public interface LocalizationService {
    void choosenLang(String lang);
    String greeting();
    String notFullName();
    String wrongAnswers();
    String incorrectAnswers(String name, int missedAnswers);
    String correctAnswers(String name);
}
