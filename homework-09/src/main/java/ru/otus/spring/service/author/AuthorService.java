package ru.otus.spring.service.author;

import ru.otus.spring.model.Author;

import java.util.List;

public interface AuthorService {
    void add(String fullName);

    Author update(Long id, String fullName);

    List<Author> findAll();

    boolean existsByFullName(String fullName);

    Author findById(Long id);

    void deleteById(Long id);
}
