package ru.otus.spring.dao.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.model.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    long count();
    Author save(Author author);
    void deleteById(long id);
    Author findById(long id);
    @Query("select a from Author a where a.fullName = :fullname")
    Author findByFullName(@Param("fullname") String fullname);
    List<Author> findAll();
}
