package ru.otus.storage.service;

import ru.otus.storage.model.YearsRange;

import java.util.List;

public interface YearsRangeService {
    List<YearsRange> findAll();
}
