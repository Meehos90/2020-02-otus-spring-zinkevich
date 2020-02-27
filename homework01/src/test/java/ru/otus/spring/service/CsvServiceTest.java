package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Класс CsvService")
public class CsvServiceTest {

    @DisplayName("Умение корректно считывать вопросы и ответы из csv-файла")
    @Test
    public void generateListFromCsv() {
        String csvFile = "src/test/resources/csv/questionsTest.csv";
        CsvService csvService = new CsvService(csvFile);
        HashMap<String, List<String>> interrogation = csvService.csvFileRead();

        ArrayList<String> answers = new ArrayList<>();
        answers.add("да");
        answers.add("нет");

        assertTrue(interrogation.containsKey("Работаете ли вы программистом?"));
        assertTrue(interrogation.containsValue(answers));
    }
}
