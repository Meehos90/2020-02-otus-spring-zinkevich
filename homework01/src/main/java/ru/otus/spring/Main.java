package ru.otus.spring;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import ru.otus.spring.service.CsvService;
import ru.otus.spring.service.QuestionsService;

public class Main {
   // private static final String SAMPLE_CSV_FILE_PATH = "homework01/src/main/resources/csv/questions.csv";

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        Scanner scanner = new Scanner(System.in);
        CsvService csvReader = context.getBean(CsvService.class);
        QuestionsService questionsService = context.getBean(QuestionsService.class);
        HashMap<String, String> inquirer = csvReader.csvRead();
        questionsService.askQuestions(scanner, inquirer);
    }

}
