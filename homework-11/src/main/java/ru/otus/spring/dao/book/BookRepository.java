package ru.otus.spring.dao.book;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.model.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    Mono<Long> count();
    
    Mono<Book> save(Mono<Book> book);

    Mono<Void> deleteById(String id);

    Mono<Book> findById(String id);

    Mono<Book> findByTitle(String title);
    
    Flux<Book> findByAuthorFullName(String fullname);

    Flux<Book> findByAuthorId(String id);

    Flux<Book> findByGenreName(String name);

    Flux<Book> findByGenreId(String id);

    Flux<Book> findAll();

    Mono<Void> removeBooksByAuthorId(String id);

    Mono<Void> removeBooksByGenreId(String id);
    
}
