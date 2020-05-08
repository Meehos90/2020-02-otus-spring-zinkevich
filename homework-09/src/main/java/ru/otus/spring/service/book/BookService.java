package ru.otus.spring.service.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.otus.spring.model.Book;

public interface BookService {
    Book findById(Long id);

    Book edit(Long id, String title, String author, String genre);

    void deleteById(Long id);

    Page<Book> findAll(Pageable pageable);

    boolean existsByTitle(String title);

    boolean existsById(Long id);
}
