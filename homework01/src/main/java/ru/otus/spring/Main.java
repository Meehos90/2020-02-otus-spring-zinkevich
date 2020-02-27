package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.CsvService;
import ru.otus.spring.service.InterrogationService;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        Scanner scanner = new Scanner(System.in);
        CsvService csvReader = context.getBean(CsvService.class);
        InterrogationService interrogationService = context.getBean(InterrogationService.class);
        HashMap<String, List<String>> inquirer = csvReader.csvFileRead();
        interrogationService.askQuestions(scanner, inquirer);
    }

}
