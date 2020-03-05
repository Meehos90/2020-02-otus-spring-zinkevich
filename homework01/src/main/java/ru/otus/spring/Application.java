package ru.otus.spring;

import java.util.List;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.dao.CsvQuestionsDao;
import ru.otus.spring.model.Survey;
import ru.otus.spring.service.impl.ConsoleTestingService;

@RequiredArgsConstructor
public class Application {
    private final ConsoleTestingService consoleTestingService;
    private final CsvQuestionsDao csvQuestionsDao;

    public void startApp() {
       List<Survey> surveyList = csvQuestionsDao.csvFileRead();
        consoleTestingService.startTesting(surveyList);
    }

}
