package ru.otus.spring.dao.book;

import ru.otus.spring.model.Book;

import java.util.List;

public interface BookDao {
    long count();
    void save(Book book);
    void updateBookById(Book book);
    void deleteById(long id);
    Book findByTitle(String title);
    List<Book> findByAuthor(String fullname);
    List<Book> findByGenre(String name);
    List<Book> getAll();
}
