package ru.otus.spring.dao.book;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.model.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    Mono<Long> count();
    
    Mono<Book> save(Book book);

    Mono<Void> deleteById(String id);

    Mono<Book> findById(String id);

    Mono<Book> findByTitle(String title);

    Flux<Book> findAll();
}
