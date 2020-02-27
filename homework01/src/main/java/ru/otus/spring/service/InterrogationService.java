package ru.otus.spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class InterrogationService {
    private int missedAnswers = 0;

    public void askQuestions(Scanner scanner, HashMap<String, List<String>> interrogation) {
        String name = introduceYorself(scanner);
        interrogation.forEach((q, a) -> {
            System.out.println(q);
            String answer = scanner.nextLine();
            if(!a.contains(answer.toLowerCase())) {
               missedAnswers++;
               answer = "Вы не ответили на вопрос правильно!";
               System.out.println(answer);
            }
        });
        questionsResult(name, missedAnswers);
    }

    private String introduceYorself(Scanner scanner) {
        System.out.println("Здравствуйте, представьтесь пожалуйста");
        String name = scanner.nextLine();
        if(name.isEmpty()) {
            System.out.println("Вы не назвали себя!");
            return introduceYorself(scanner);
        }
        return name;
    }

    private void questionsResult(String name, int missedAnswers) {
        if(missedAnswers > 0) {
            System.out.println(name + " вы пропустили или не ответили правильно на " + missedAnswers + " вопросов");
        } else {
            System.out.println("Поздравляем, " + name + "! Вы ответили на все вопросы!");
        }
    }
}
