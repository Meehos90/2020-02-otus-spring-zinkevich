package ru.otus.storage.service;

import ru.otus.storage.model.PartType;

import java.util.List;

public interface PartTypeService {
    List<PartType> findAll();
}
