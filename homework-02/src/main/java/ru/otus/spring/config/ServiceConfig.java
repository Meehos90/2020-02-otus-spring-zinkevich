package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.dao.UserDataDao;
import ru.otus.spring.service.SurveyService;
import ru.otus.spring.service.TestingService;
import ru.otus.spring.service.impl.ConsoleSurveyService;
import ru.otus.spring.service.impl.ConsoleTestingService;

@Configuration
public class ServiceConfig {

    @Bean
    public SurveyService surveyService() {
        return new ConsoleSurveyService();
    }

    @Bean
    public TestingService testingService(SurveyService surveyService, QuestionsDao questionsDao, UserDataDao userDataDao) {
        return new ConsoleTestingService(surveyService, questionsDao, userDataDao);
    }

}
