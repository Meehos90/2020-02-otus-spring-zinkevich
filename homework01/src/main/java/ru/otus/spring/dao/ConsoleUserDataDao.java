package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.service.impl.ConsoleSurveyService;

@RequiredArgsConstructor
public class ConsoleUserDataDao implements UserDataDao{
    private final ConsoleSurveyService consoleSurveyService;

    public String introduceYorself() {
        consoleSurveyService.showMessage("Здравствуйте, представьтесь пожалуйста");
        String name = consoleSurveyService.getMessage();
        if (name.isEmpty()) {
            consoleSurveyService.showMessage("Вы не назвали себя!");
            return introduceYorself();
        }
        return name;
    }
}
