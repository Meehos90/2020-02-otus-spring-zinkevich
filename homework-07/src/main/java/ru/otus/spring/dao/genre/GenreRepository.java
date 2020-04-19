package ru.otus.spring.dao.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.model.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    long count();
    Genre save(Genre genre);
    void deleteById(long id);
    Genre findById(long id);
    @Query("select g from Genre g where g.name = :name")
    Genre findByName(@Param("name") String name);
    List<Genre> findAll();
}
