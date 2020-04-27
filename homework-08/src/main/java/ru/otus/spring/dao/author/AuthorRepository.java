package ru.otus.spring.dao.author;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.model.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, Long> {
    long count();

    Author save(Author author);

    void deleteById(String id);

    Author findById(String id);

    Author findByFullName(String fullname);

    List<Author> findAll();
    
    Author findByIdIsNull(String id);
}
