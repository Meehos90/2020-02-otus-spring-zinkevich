package ru.otus.spring.dao.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findById(long id);

    @Query("select g from Genre g where g.name = :name")
    Genre findByName(@Param("name") String name);
}
