package ru.otus.spring.dao.genre;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.model.Genre;

import java.util.List;

public interface GenreRepository extends MongoRepository<Genre, Long> {
    long count();

    Genre save(Genre genre);

    void deleteById(String id);

    Genre findById(String id);

    Genre findByName(String name);

    List<Genre> findAll();
    
    Genre findByIdIsNull(String id);
}
