package ru.otus.spring.dao.genre;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.model.Genre;

import java.util.List;

public interface GenreRepository extends MongoRepository<Genre, Long> {
    long count();

    Genre save(Genre genre);

    void deleteById(long id);

    Genre findById(long id);

    @Query("select g from Genre g where g.name = :name")
    Genre findByName(@Param("name") String name);

    List<Genre> findAll();
}
