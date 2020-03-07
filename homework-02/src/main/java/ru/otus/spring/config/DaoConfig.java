package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.otus.spring.dao.ConsoleUserDataDao;
import ru.otus.spring.dao.CsvQuestionsDao;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.dao.UserDataDao;
import ru.otus.spring.service.SurveyService;

@PropertySource("classpath:application.properties")
@Configuration
public class DaoConfig {

    @Bean
    public QuestionsDao questionsDao(@Value("${csv.file}") String csvfile) {
        return new CsvQuestionsDao(csvfile);
    }

    @Bean
    public UserDataDao userDataDao(SurveyService surveyService) {
        return new ConsoleUserDataDao(surveyService);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
