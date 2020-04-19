package ru.otus.spring.service.author;

import ru.otus.spring.model.Author;

import java.util.List;

public interface AuthorService {
    void save();

    void update();

    Author findByFullname();

    void delete();

    List<Author> findAll();
}
