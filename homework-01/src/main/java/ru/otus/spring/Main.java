package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.otus.spring.service.impl.ConsoleTestingService;

public class Main {

    public static void main(String[] args)  {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        ConsoleTestingService testingService = context.getBean(ConsoleTestingService.class);
        testingService.startTesting();
    }

}
