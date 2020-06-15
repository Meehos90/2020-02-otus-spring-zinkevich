package ru.otus.spring.service.book;

import ru.otus.spring.model.Book;

import java.util.List;

public interface BookService {
    Book findById(Long id);

    Book save(Book book);

    Book update(Book book);

    void deleteById(Long id);

    List<Book> findAll();

    boolean existsByTitle(String title);

    boolean existsById(Long id);
}
