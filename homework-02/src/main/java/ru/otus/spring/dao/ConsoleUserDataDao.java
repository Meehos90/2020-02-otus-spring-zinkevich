package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.service.InternationalizationService;
import ru.otus.spring.service.SurveyService;

@Repository
@RequiredArgsConstructor
public class ConsoleUserDataDao implements UserDataDao{
    private final SurveyService consoleSurveyService;
    private final InternationalizationService inService;

    public String introduceYorself() {
        consoleSurveyService.showMessage(inService.greeting());
        String name = consoleSurveyService.getMessage();
        if (name.isEmpty()) {
            consoleSurveyService.showMessage("Вы не назвали себя!");
            return introduceYorself();
        }
        return name;
    }
}
