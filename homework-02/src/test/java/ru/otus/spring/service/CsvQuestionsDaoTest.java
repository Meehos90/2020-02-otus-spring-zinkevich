package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.dao.CsvQuestionsDao;
import ru.otus.spring.model.Survey;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Класс CsvQuestionsDao")
public class CsvQuestionsDaoTest {

    @DisplayName("Умение корректно считывать вопросы и ответы из csv-файла")
    @Test
    public void generateListFromCsv() {
        String csvFile = "csv/questionsTest.csv";
        CsvQuestionsDao csvQuestionsDao = new CsvQuestionsDao(csvFile);
        List<Survey> surveyList = csvQuestionsDao.csvFileRead();

        for(Survey survey : surveyList) {
            assertTrue(survey.getQuestion().contains("Работаете ли вы программистом?"));
            assertTrue(survey.getAnswer().contains("да/нет"));
        }

    }
}
