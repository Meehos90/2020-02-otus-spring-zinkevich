package ru.otus.spring.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.logging.Logger;
import ru.otus.spring.model.Survey;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.MessageService;
import ru.otus.spring.service.TestingService;
import ru.otus.spring.service.UserService;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class ConsoleTestingService implements TestingService {

    private static final String DELIMETER = "/";
    private final IOService consoleIOService;
    private final UserService userService;
    private final MessageService messageService;
    private final QuestionsDao questionsDao;
    private int missedAnswers = 0;

    public ConsoleTestingService(IOService consoleIOService,
                                 UserService userService,
                                 MessageService messageService,
                                 QuestionsDao questionsDao) {
        this.consoleIOService = consoleIOService;
        this.userService = userService;
        this.messageService = messageService;
        this.questionsDao = questionsDao;
    }

    @Logger
    public void startTesting() {
        List<Survey> surveys = questionsDao.csvFileRead();
        String name = userService.getUserInfo();
        for (Survey survey : surveys) {
            consoleIOService.showMessage(survey.getQuestion());
            String realAnswer = consoleIOService.getMessage().toLowerCase();
            answersAnalysis(survey, realAnswer);
        }
        questionsResult(name, missedAnswers);
    }

    @Logger
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

    @Logger
    private void wrongAnswer() {
        log.info("wrong answers");
        missedAnswers++;
        consoleIOService.showMessage(messageService.getLocaleMessage("message.wrong.answer", null));
    }

    @Logger
    private void questionsResult(String name, int missedAnswers) {
        log.info("questions result");
        if (missedAnswers > 0) {
            consoleIOService.showMessage(messageService.getLocaleMessage("message.incorrect.answers", new Object[] {name, missedAnswers}));
        } else {
            consoleIOService.showMessage(messageService.getLocaleMessage("message.correct.answers", new Object[] {name}));
        }
    }
}
