package ru.otus.spring.dao;

import ru.otus.spring.model.Survey;

import java.util.List;

public interface QuestionsDao {
    List<Survey> getSurveyList();
}
