package ru.otus.spring.dao.author;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.model.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

    Mono<Long> count();

    Mono<Author> save(Author author);

    Mono<Void> deleteById(String id);

    Mono<Author> findById(String id);

    Flux<Author> findAll();

    Mono<Author> findByFullName(String fullname);
}
