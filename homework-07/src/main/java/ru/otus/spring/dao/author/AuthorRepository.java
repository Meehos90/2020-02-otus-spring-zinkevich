package ru.otus.spring.dao.author;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findById(long id);

    Author findByFullName(String fullname);
}
