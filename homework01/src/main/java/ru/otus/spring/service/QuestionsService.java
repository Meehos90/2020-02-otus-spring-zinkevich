package ru.otus.spring.service;

import java.util.HashMap;
import java.util.Scanner;

public class QuestionsService {
    private int missedAnswers = 0;

    public void askQuestions(Scanner scanner, HashMap<String, String> inquirer) {
      //  String name = introduceYorself(scanner);
        inquirer.forEach((q, a) -> {
            System.out.println(q.trim());

            String answer = scanner.nextLine();
            /*if(!answer.equals(a)) {
               missedAnswers++;
               answer = "Вы пропустили вопрос!";
               System.out.println(answer);
            }*/
        });
      //  questionsResult(name, missedAnswers);
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
            System.out.println(name + " вы пропустили " + missedAnswers + " вопросов");
        } else {
            System.out.println("Поздравляем, " + name + "! Вы ответили на все вопросы!");
        }
    }
}
