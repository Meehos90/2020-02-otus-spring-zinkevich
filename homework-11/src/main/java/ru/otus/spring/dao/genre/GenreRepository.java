package ru.otus.spring.dao.genre;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.model.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {

    Mono<Long> count();

    Mono<Genre> save(Genre genre);

    Mono<Void> deleteById(String id);

    Mono<Genre> findById(String id);

    Mono<Genre> findByName(String name);

    Flux<Genre> findAll();
}
