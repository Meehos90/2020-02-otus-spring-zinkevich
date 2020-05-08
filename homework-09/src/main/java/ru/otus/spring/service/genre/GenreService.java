package ru.otus.spring.service.genre;

import ru.otus.spring.model.Genre;

import java.util.List;

public interface GenreService {
    void deleteById(Long id);

    List<Genre> findAll();

    Genre findById(Long id);

    boolean existsByName(String name);

    boolean existsById(Long id);

    Genre edit(Long id, String name);
}
