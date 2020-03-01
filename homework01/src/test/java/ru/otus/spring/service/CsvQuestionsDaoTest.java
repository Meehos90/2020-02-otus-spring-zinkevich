package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.dao.CsvQuestionsDao;
import ru.otus.spring.model.Interrogation;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Класс CsvService")
public class CsvQuestionsDaoTest {

    @DisplayName("Умение корректно считывать вопросы и ответы из csv-файла")
    @Test
    public void generateListFromCsv() {
        CsvQuestionsDao csvQuestionsDao = new CsvQuestionsDao("csv/questionsTest.csv");
        List<Interrogation> interrogationList = csvQuestionsDao.csvFileRead();

        for(Interrogation interrogation : interrogationList) {
            assertTrue(interrogation.getQuestion().contains("Работаете ли вы программистом?"));
            assertTrue(interrogation.getAnswer().contains("да/нет"));
        }

    }
}
