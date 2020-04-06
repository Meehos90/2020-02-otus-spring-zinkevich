package ru.otus.spring.dao.book;

import ru.otus.spring.model.Book;

import java.util.List;

public interface BookDao {
    int count();
    void insert(Book book);
    void update(Book book);
    void delete(long id);
    Book getByTitle(String title);
    List<Book> getByAuthor(String fullname);
    List<Book> getByGenre(String name);
    List<Book> getAll();
}
