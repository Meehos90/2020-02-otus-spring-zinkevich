package ru.otus.spring.dao.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findById(long id);

    Book findByTitle(String title);

    boolean existsByTitle(String title);

    @EntityGraph(attributePaths = {"author", "genre"})
    Page<Book> findAll(Pageable pageable);
}
