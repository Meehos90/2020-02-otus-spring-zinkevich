package ru.otus.spring;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.otus.spring.dao.CsvQuestionsDao;
import ru.otus.spring.model.Survey;
import ru.otus.spring.service.ConsoleTestingService;

public class Main {

    public static void main(String[] args)  {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        CsvQuestionsDao csvReader = context.getBean(CsvQuestionsDao.class);
        ConsoleTestingService consoleTestingService = context.getBean(ConsoleTestingService.class);
        List<Survey> surveyList = csvReader.csvFileRead();
        consoleTestingService.askQuestions(surveyList);
    }

}
