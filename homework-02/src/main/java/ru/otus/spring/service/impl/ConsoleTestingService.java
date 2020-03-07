package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.dao.UserDataDao;
import ru.otus.spring.model.Survey;
import ru.otus.spring.service.LocalizationService;
import ru.otus.spring.service.SurveyService;
import ru.otus.spring.service.TestingService;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsoleTestingService implements TestingService {

    @Value("${missed.answers}")
    private int missedAnswers;

    @Value("${choose.language}")
    private String language;

    @Value("${unchoosen.language}")
    private String unchoosenLang;

    private static final String DELIMETER = "/";
    private final SurveyService consoleSurveyService;
    private final LocalizationService localizationService;
    private final QuestionsDao csvQuestionsDao;
    private final UserDataDao userDataDao;

    public void startTesting() {
        chooseLanguage();
        List<Survey> surveys = csvQuestionsDao.csvFileRead();
        String name = userDataDao.introduceYorself();
        for (Survey survey : surveys) {
            consoleSurveyService.showMessage(survey.getQuestion());
            String realAnswer = consoleSurveyService.getMessage().toLowerCase();
            answersAnalysis(survey, realAnswer);
        }
        questionsResult(name, missedAnswers);
    }

    private void chooseLanguage() {
        consoleSurveyService.showMessage(language);
        String lang = consoleSurveyService.getMessage().toLowerCase();
        localizationService.choosenLang(lang);
        if(!lang.contains("english") && !lang.contains("russian")) {
            consoleSurveyService.showMessage(unchoosenLang);
        }
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
        ++missedAnswers;
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
