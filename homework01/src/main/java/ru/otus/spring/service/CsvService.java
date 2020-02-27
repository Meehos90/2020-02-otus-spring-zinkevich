package ru.otus.spring.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
public class CsvService {
    private final String csvFile;
    private final static String DELIMETER = "/";

    public HashMap<String, List<String>> csvFileRead() {
        HashMap<String, List<String>> interrogation = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFile))) {
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            List<String[]> records = csvReader.readAll();
            List<String> answers = new ArrayList<>();
            for (String[] record : records) {
                String question = record[0].trim();
                String answer = record[1].trim();
                if (!answer.contains(DELIMETER)) {
                    answers.add(answer);
                    interrogation.put(question, answers);
                } else {
                    interrogation.put(question, Arrays.asList(answer.split(DELIMETER)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return interrogation;
    }
}
