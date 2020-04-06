package ru.otus.spring.dao.author;

import ru.otus.spring.model.Author;

import java.util.List;

public interface AuthorDao {
    int count();
    void insert(Author author);
    void update(Author author);
    void delete(long id);
    Author getById(long id);
    Author getByFullname(String fullname);
    List<Author> getAll();
}
