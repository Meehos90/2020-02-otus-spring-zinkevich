package ru.otus.spring.service.author;

import ru.otus.spring.model.Author;

import java.util.List;

public interface AuthorService {
    Author edit(Long id, String fullName);

    List<Author> findAll();

    boolean existsByFullName(String fullName);

    boolean existsById(Long id);

    Author findById(Long id);

    void deleteById(Long id);
}
