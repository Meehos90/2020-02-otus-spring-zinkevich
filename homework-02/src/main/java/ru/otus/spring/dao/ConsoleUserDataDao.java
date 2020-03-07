package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.service.SurveyService;

@Repository
@RequiredArgsConstructor
public class ConsoleUserDataDao implements UserDataDao{
    private final SurveyService consoleSurveyService;

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
