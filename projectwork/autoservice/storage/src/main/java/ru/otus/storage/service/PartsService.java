package ru.otus.storage.service;

import ru.otus.storage.model.Part;

public interface PartsService {
    boolean existsPartByArticle(String article);

    Part findByArticle(String article);

    Part findById(Long id);

    Part savePart(Part part);

    Part findPartByParameters(String mark, String model, Long year);
}
