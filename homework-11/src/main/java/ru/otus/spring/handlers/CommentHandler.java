package ru.otus.spring.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.spring.dao.comment.CommentRepository;
import ru.otus.spring.exception.ServerWebInputException;
import ru.otus.spring.model.Comment;

import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.noContent;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RequiredArgsConstructor
public class CommentHandler {
    private final CommentRepository repository;

    public Mono<ServerResponse> findAllComments(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON).body(repository.findAll(), Comment.class);
    }

    public Mono<ServerResponse> getCommentById(ServerRequest request) {
        return repository.findById(request.pathVariable("id"))
                .flatMap(comment -> ok().contentType(APPLICATION_JSON).bodyValue(comment))
                .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, String.format("Comment with id = %s is not found",
                        request.pathVariable("id")))));
    }

    public Mono<ServerResponse> findCommentByContent(ServerRequest request) {
        Optional<String> content = request.queryParam("content");
        if (content.isPresent()) {
            return repository.findByContent(content.get())
                    .flatMap(comment -> ok().contentType(APPLICATION_JSON).bodyValue(comment))
                    .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, String.format("Comment with content = %s is not found", content))));
        } else {
            throw new ServerWebInputException(BAD_REQUEST, "Content is empty");
        }
    }

    public Mono<ServerResponse> putComment(ServerRequest request) {
        return request.body(BodyExtractors.toMono(Comment.class))
                .filter(c -> c.getContent() != null)
                .map(c -> {
                    c.setId(request.pathVariable("id"));
                    return repository.save(c);
                })
                .flatMap(comment -> ok().contentType(APPLICATION_JSON).body(comment, Comment.class))
                .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, "Body is empty")));
    }

    public Mono<ServerResponse> addComment(ServerRequest request) {
        return request.body(BodyExtractors.toMono(Comment.class))
                .map(repository::save)
                .flatMap(comment -> ok().contentType(APPLICATION_JSON).body(comment, Comment.class))
                .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, "Body is empty")));
    }

    public Mono<ServerResponse> deleteComment(ServerRequest request) {
        return noContent().build((repository.deleteById(request.pathVariable("id"))));
    }
}
