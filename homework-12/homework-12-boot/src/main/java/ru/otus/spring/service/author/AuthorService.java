package ru.otus.spring.service.author;

import ru.otus.spring.model.Author;

import java.util.List;

public interface AuthorService {
    Author save(Author author);

    Author update(Author authorDetails);

    List<Author> findAll();

    boolean existsByFullName(String fullName);

    boolean existsById(Long id);

    Author findById(Long id);

    void deleteById(Long id);
}
