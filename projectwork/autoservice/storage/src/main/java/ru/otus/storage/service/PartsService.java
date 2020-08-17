package ru.otus.storage.service;

import ru.otus.storage.model.Part;

public interface PartsService {
    boolean existsPartByArticle(String article);

    String findByParameters(String partName, String autoMark, String autoModel, String autoYear);

    Part findByArticle(String article);

    Part findById(Long id);

    Part savePart(Part part);

    void deletePart(Long partId);
}
