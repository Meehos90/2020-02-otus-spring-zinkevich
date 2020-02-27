package ru.otus.spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@DisplayName("Класс CsvService")
public class CsvServiceTest {


   /* @ParameterizedTest(name = "Чтение csv-файла")
    @CsvFileSource(resources = "/homework01/src/test/resources/csv/questionsTest.csv", numLinesToSkip = 1)
    public void generateListFromCsv() {
        String csvFile = "homework01/src/test/resources/csv/questionsTest.csv";
        CsvService csvService = new CsvService(csvFile);
        List<String> questionsList = csvService.csvRead();

        List<String> list = new ArrayList<>();
        list.add("Сколько вам лет?");
        list.add("Какого вы пола?");

        assertEquals(questionsList.get(0), list.get(0));
        assertEquals(questionsList.get(1), list.get(1));
    }*/
}
