package ru.otus.spring.service;

import java.util.List;

import ru.otus.spring.model.Survey;

public interface TestingService {
    void startTesting(List<Survey> surveys);
}
