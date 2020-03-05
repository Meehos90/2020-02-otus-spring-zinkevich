package ru.otus.spring.service;

import java.util.Scanner;


public class ConsoleSurveyService implements SurveyService {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void showMessage(String message) {

    }

    @Override
    public String getAnswer() {
        return scanner.nextLine();
    }
}
