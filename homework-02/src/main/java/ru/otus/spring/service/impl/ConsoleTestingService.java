package ru.otus.spring.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.model.Survey;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.LocalizationService;
import ru.otus.spring.service.MessageService;
import ru.otus.spring.service.TestingService;
import ru.otus.spring.service.UserService;

@Service
public class ConsoleTestingService implements TestingService {

    private static final String DELIMETER = "/";
    private final IOService consoleIOService;
    private final LocalizationService localizationService;
    private final UserService userService;
    private final MessageService messageService;

    @Value("${missed.answers}")
    private int missedAnswers;

    public ConsoleTestingService(IOService consoleIOService,
                                 LocalizationService localizationService,
                                 UserService userService,
                                 MessageService messageService) {
        this.consoleIOService = consoleIOService;
        this.localizationService = localizationService;
        this.userService = userService;
        this.messageService = messageService;
    }

    public void startTesting() {
        QuestionsDao questionsDao = localizationService.getCsvFile();
        List<Survey> surveys = questionsDao.csvFileRead();
        String name = userService.getUserInfo();
        for (Survey survey : surveys) {
            consoleIOService.showMessage(survey.getQuestion());
            String realAnswer = consoleIOService.getMessage().toLowerCase();
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
        consoleIOService.showMessage(messageService.getLocaleMessage("message.wrong.answer", null));
    }

    private void questionsResult(String name, int missedAnswers) {
        if (missedAnswers > 0) {
            consoleIOService.showMessage(messageService.getLocaleMessage("message.incorrect.answers", new Object[] {name, missedAnswers}));
        } else {
            consoleIOService.showMessage(messageService.getLocaleMessage("message.correct.answers", new Object[] {name}));
        }
    }
}
