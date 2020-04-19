package ru.otus.spring.dao.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findById(long id);
    @Query("select a from Author a where a.fullName = :fullname")
    Author findByFullName(@Param("fullname") String fullname);
}
