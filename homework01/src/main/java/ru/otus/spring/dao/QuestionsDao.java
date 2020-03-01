package ru.otus.spring.dao;

import ru.otus.spring.model.Interrogation;

import java.util.List;

public interface QuestionsDao {
    List<Interrogation> csvFileRead();
}
