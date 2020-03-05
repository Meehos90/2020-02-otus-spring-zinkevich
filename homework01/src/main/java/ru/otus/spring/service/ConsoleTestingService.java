package ru.otus.spring.service;

import ru.otus.spring.model.Survey;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleTestingService {
    private int missedAnswers = 0;
    private final static String DELIMETER = "/";

    ConsoleSurveyService consoleSurveyService = new ConsoleSurveyService();

    public void askQuestions(List<Survey> surveys) {
        //String name = introduceYorself(scanner);
        for (Survey survey : surveys) {
            System.out.println(survey.getQuestion());
            String realAnswer = consoleSurveyService.getAnswer().toLowerCase();
            if (survey.getAnswer().contains(DELIMETER)) {
                List<String> answers = Arrays.asList(survey.getAnswer().trim().split(DELIMETER));
                if (!answers.contains(realAnswer)) {
                    wrongAnswer();
                }
            } else {
                wrongAnswer();
            }
        }
       // questionsResult(name, missedAnswers);
    }

    private void wrongAnswer() {
        missedAnswers++;
        String wrongAnswer = "Вы не ответили на вопрос правильно!";
        System.out.println(wrongAnswer);
    }

    private String introduceYorself(Scanner scanner) {
        System.out.println("Здравствуйте, представьтесь пожалуйста");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            System.out.println("Вы не назвали себя!");
            return introduceYorself(scanner);
        }
        return name;
    }

    private void questionsResult(String name, int missedAnswers) {
        if (missedAnswers > 0) {
            System.out.println(name + " вы пропустили или не ответили правильно на " + missedAnswers + " вопросов");
        } else {
            System.out.println("Поздравляем, " + name + "! Вы ответили на все вопросы!");
        }
    }
}
