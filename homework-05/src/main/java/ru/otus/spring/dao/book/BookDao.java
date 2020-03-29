package ru.otus.spring.dao.book;

import ru.otus.spring.model.Book;

import java.util.List;

public interface BookDao {
    int count();
    void insert(Book book);
    void update(Book book);
    void delete(long id);
    Book getByTitle(String title);
    Book getByAuthor(String author);
    List<Book> getAll();
}
