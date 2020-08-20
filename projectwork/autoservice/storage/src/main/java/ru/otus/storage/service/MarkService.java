package ru.otus.storage.service;

import ru.otus.storage.model.Mark;

import java.util.List;

public interface MarkService {
    List<Mark> findAll();
}
