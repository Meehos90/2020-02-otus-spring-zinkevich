package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.service.LocalizationService;
import ru.otus.spring.service.SurveyService;

@Repository
@RequiredArgsConstructor
public class ConsoleUserDataDao implements UserDataDao{
    private final SurveyService consoleSurveyService;
    private final LocalizationService localizationService;

    public String introduceYorself() {
        consoleSurveyService.showMessage(localizationService.greeting());
        String name = consoleSurveyService.getMessage();
        if (name.isEmpty()) {
            consoleSurveyService.showMessage(localizationService.notFullName());
            return introduceYorself();
        }
        return name;
    }
}
