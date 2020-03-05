package ru.otus.spring.service.impl;

import java.util.Scanner;

import ru.otus.spring.service.SurveyService;


public class ConsoleSurveyService implements SurveyService {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public String getMessage() {
        return scanner.nextLine();
    }
}
