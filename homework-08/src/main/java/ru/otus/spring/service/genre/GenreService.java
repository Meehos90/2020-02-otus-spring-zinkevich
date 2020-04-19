package ru.otus.spring.service.genre;

import ru.otus.spring.model.Genre;

import java.util.List;

public interface GenreService {
    Genre fingGenreByName();
    void delete();
    List<Genre> findAll();
}
