package ru.otus.spring.dao.genre;

import ru.otus.spring.model.Genre;

import java.util.List;

public interface GenreDao {
    long count();
    void save(Genre genre);
    void updateNameById(Genre genre);
    void deleteById(long id);
    Genre findById(long id);
    Genre findByName(String name);
    List<Genre> findAll();
}
