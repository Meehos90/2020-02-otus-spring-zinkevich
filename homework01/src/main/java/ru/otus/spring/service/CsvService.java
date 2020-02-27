package ru.otus.spring.service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CsvService {
    private final String csvFile;

    public HashMap<String, String> csvRead() {
        HashMap<String, String> inquirer = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFile))) {
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            List<String[]> records = csvReader.readAll();
            List<String> answers = new ArrayList<>();
            String question = null;
            String answer = null;
            for (String[] record : records) {
                if(record[1].contains("/")) {
                  for(String answer1 : record[1].split("/"))
                  answers.add(answer1);
                }
                question = record[0];
                answer = record[1];
            }
            System.out.println(answers);
           // interrogation.forEach((k,v) -> System.out.println("Вопрос: " + k + " Ответы:" + v));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return inquirer;
    }
}
