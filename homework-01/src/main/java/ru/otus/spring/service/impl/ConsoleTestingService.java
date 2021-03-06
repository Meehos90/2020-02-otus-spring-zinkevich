package ru.otus.spring.service.impl;

import java.util.Arrays;
import java.util.List;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.dao.ConsoleUserDataDao;
import ru.otus.spring.dao.CsvQuestionsDao;
import ru.otus.spring.model.Survey;
import ru.otus.spring.service.TestingService;

@RequiredArgsConstructor
public class ConsoleTestingService implements TestingService {
    private int missedAnswers = 0;
    private static final String DELIMETER = "/";
    private final ConsoleSurveyService consoleSurveyService;
    private final CsvQuestionsDao csvQuestionsDao;
    private final ConsoleUserDataDao userDataDao;

    public void startTesting() {
        List<Survey> surveys = csvQuestionsDao.csvFileRead();
        String name = userDataDao.introduceYorself();
        for (Survey survey : surveys) {
            consoleSurveyService.showMessage(survey.getQuestion());
            String realAnswer = consoleSurveyService.getMessage().toLowerCase();
            answersAnalysis(survey, realAnswer);
        }
        questionsResult(name, missedAnswers);
    }

    private void answersAnalysis(Survey survey, String realAnswer) {
        if (survey.getAnswer().contains(DELIMETER)) {
            List<String> answers = Arrays.asList(survey.getAnswer().trim().split(DELIMETER));
            if (!answers.contains(realAnswer)) {
                wrongAnswer();
            }
        } else if (!survey.getAnswer().contains(realAnswer)) {
            wrongAnswer();
        }
    }

    private void wrongAnswer() {
        missedAnswers++;
        consoleSurveyService.showMessage("Вы не ответили на вопрос правильно!");
    }

    private void questionsResult(String name, int missedAnswers) {
        if (missedAnswers > 0) {
            consoleSurveyService.showMessage(
                    name + " вы пропустили или не ответили правильно на " + missedAnswers + " вопросов");
        } else {
            consoleSurveyService.showMessage("Поздравляем, " + name + "! Вы ответили на все вопросы!");
        }
    }
}
