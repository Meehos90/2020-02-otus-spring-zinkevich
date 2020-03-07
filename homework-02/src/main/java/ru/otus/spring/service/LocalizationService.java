package ru.otus.spring.service;

public interface LocalizationService {
    void choosenLang(String lang);
    String greeting();
    String notFullName();
}
