package ru.otus.spring.dao.genre;

import ru.otus.spring.model.Genre;

import java.util.List;

public interface GenreDao {
    int count();
    void insert(Genre genre);
    void update(Genre genre);
    void delete(long id);
    Genre getById(long id);
    Genre getByName(String name);
    List<Genre> getAll();
}
