package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.config.LocalizationProperties;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.model.Survey;
import ru.otus.spring.service.impl.ConsoleTestingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.mockito.Mockito.*;

@DisplayName("Класс сервиса тестирования студентов")
@SpringBootTest(classes = ConsoleTestingService.class)
class ConsoleTestingServiceTest {
    private final String QUESTION = "Do you work as a programmer?";
    private final String WRONG_ANSWER = "You did not answer the question correctly!";
    private final String NAME = "James Gosling";
    @MockBean
    private LocalizationProperties localProps;
    @MockBean
    private IOService consoleIOService;
    @MockBean
    private UserService userService;
    @MockBean
    private MessageService messageService;
    @MockBean
    private QuestionsDao questionsDao;
    @Autowired
    private TestingService testingService;

    @BeforeEach
    void setUp() {
        when(localProps.getLanguageLocale()).thenReturn(Locale.ENGLISH);
        when(messageService.getLocaleMessage("message.wrong.answer", null)).thenReturn(WRONG_ANSWER);
        List<Survey> surveyList = new ArrayList<>();
        Survey survey = new Survey();
        survey.setAnswer("yes/no");
        survey.setQuestion(QUESTION);
        surveyList.add(survey);
        when(questionsDao.getSurveyList()).thenReturn(surveyList);
        when(userService.getUserInfo()).thenReturn(NAME);
    }


    @DisplayName("Вызов метода тестирования студентов: неправильный ответ")
    @Test
    public void startTestingNegative() {
        startTesting(" ", 1);
    }

    @DisplayName("Вызов метода тестирования студентов: правильный ответ")
    @Test
    public void startTestingPositive() {
        startTesting("yes", 0);
    }

    void startTesting(String answer, int wrongAnswers) {
        when(consoleIOService.getMessage()).thenReturn(answer);
        testingService.startTesting();
        verify(consoleIOService, times(1)).showMessage(QUESTION);
        verify(consoleIOService, times(1)).getMessage();
        verify(consoleIOService, times(wrongAnswers)).showMessage(WRONG_ANSWER);
    }


}
