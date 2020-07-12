package ru.otus.spring.dao.author;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    boolean existsByFullName(String fullname);

    Author findByFullName(String fullname);
}
