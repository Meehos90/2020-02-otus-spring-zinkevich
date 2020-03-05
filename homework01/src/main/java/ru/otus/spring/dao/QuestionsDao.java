package ru.otus.spring.dao;

import ru.otus.spring.exception.QuestionsLoadingException;
import ru.otus.spring.model.Interrogation;

import java.util.List;

public interface QuestionsDao {
    List<Interrogation> csvFileRead() throws QuestionsLoadingException;
}
