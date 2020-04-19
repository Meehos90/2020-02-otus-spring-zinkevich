package ru.otus.spring.service.book;

import ru.otus.spring.model.Book;

import java.util.List;

public interface BookService {
    void save();

    Book findByTitle();

    List<Book> findByAuthor();

    List<Book> findByGenre();

    void update();

    void delete();

    List<Book> findAll();
}
