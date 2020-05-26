package ru.otus.spring.dao.comment;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.model.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {

    Mono<Long> count();

    Mono<Comment> save(Mono<Comment> comment);

    Mono<Void> deleteById(String id);

    Mono<Comment> findById(String id);

    Mono<Comment> findByContent(String content);

    Flux<Comment> findByBookTitle(String title);

    Flux<Comment> findAll();
}
