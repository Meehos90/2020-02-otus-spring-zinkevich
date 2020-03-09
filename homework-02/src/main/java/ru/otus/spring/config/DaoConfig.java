package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.otus.spring.dao.CsvQuestionsDao;
import ru.otus.spring.dao.QuestionsDao;

@Configuration
public class DaoConfig {

    @Bean
    @Primary
    public QuestionsDao questionsDao(@Value("${csv.file}") String csvFile) {return new CsvQuestionsDao(csvFile);
    }

    @Bean
    public QuestionsDao questionsDaoRu(@Value("${ru.csv.file}") String csvFile) {return new CsvQuestionsDao(csvFile);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
