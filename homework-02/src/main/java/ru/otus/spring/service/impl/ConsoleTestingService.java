package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.LocaleQuestionsDao;
import ru.otus.spring.service.UserService;
import ru.otus.spring.model.Survey;
import ru.otus.spring.service.LocalizationService;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.TestingService;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsoleTestingService implements TestingService {

    @Value("${missed.answers}")
    private int missedAnswers;

    @Value("${choose.language}")
    private String chooseLanguage;

    @Value("${unchoosen.language}")
    private String unchoosenLang;

    private static final String DELIMETER = "/";
    private final IOService consoleIOService;
    private final LocalizationService localizationService;
    private final UserService userService;
    private final LocaleQuestionsDao questionsDao;

    public void startTesting() {
        chooseLanguage();
        List<Survey> surveys = questionsDao.chooseQuestionsDao().csvFileRead();
        String name = userService.getUserInfo();
        for (Survey survey : surveys) {
            consoleIOService.showMessage(survey.getQuestion());
            String realAnswer = consoleIOService.getMessage().toLowerCase();
            answersAnalysis(survey, realAnswer);
        }
        questionsResult(name, missedAnswers);
    }

    private void chooseLanguage() {
        consoleIOService.showMessage(chooseLanguage);
        String lang = consoleIOService.getMessage().toLowerCase();
        localizationService.choosenLang(lang);
        if (!lang.contains("english") && !lang.contains("russian")) {
            consoleIOService.showMessage(unchoosenLang);
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
        consoleIOService.showMessage(localizationService.wrongAnswers());
    }

    private void questionsResult(String name, int missedAnswers) {
        if (missedAnswers > 0) {
            consoleIOService.showMessage(localizationService.incorrectAnswers(name, missedAnswers));
        } else {
            consoleIOService.showMessage(localizationService.correctAnswers(name));
        }
    }
}
