package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.model.Interrogation;
import ru.otus.spring.dao.CsvQuestionsDao;
import ru.otus.spring.service.InterrogationService;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        Scanner scanner = new Scanner(System.in);
        CsvQuestionsDao csvReader = context.getBean(CsvQuestionsDao.class);
        InterrogationService interrogationService = context.getBean(InterrogationService.class);
        List<Interrogation> inquirer = csvReader.csvFileRead();
        interrogationService.askQuestions(scanner, inquirer);
    }

}
