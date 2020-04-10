package ru.otus.spring.dao.author;

import ru.otus.spring.model.Author;

import java.util.List;

public interface AuthorDao {
    long count();
    void save(Author author);
    void updateFullNameById(Author author);
    void deleteById(long id);
    Author findById(long id);
    Author findByFullName(String fullname);
    List<Author> findAll();
}
