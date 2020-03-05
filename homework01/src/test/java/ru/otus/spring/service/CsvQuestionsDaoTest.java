package ru.otus.spring.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ru.otus.spring.dao.CsvQuestionsDao;
import ru.otus.spring.model.Survey;

@DisplayName("Класс CsvService")
public class CsvQuestionsDaoTest {

    @DisplayName("Умение корректно считывать вопросы и ответы из csv-файла")
    @Test
    public void generateListFromCsv() {
        CsvQuestionsDao csvQuestionsDao = new CsvQuestionsDao("csv/questionsTest.csv");
        List<Survey> surveyList = csvQuestionsDao.csvFileRead();

        for(Survey survey : surveyList) {
            assertTrue(survey.getQuestion().contains("Работаете ли вы программистом?"));
            assertTrue(survey.getAnswer().contains("да/нет"));
        }

    }
}
