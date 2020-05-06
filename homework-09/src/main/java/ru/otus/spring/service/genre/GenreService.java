package ru.otus.spring.service.genre;

import ru.otus.spring.model.Genre;

import java.util.List;

public interface GenreService {
    void deleteById(Long id);

    void add(String name);

    List<Genre> findAll();

    Genre findById(Long id);

    boolean existsByName(String name);

    Genre update(Long id, String name);
}
