package ru.otus.spring.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.spring.dao.book.BookRepository;
import ru.otus.spring.dao.comment.CommentRepository;
import ru.otus.spring.exception.ServerWebInputException;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;

import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.noContent;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RequiredArgsConstructor
public class CommentHandler {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    public Mono<ServerResponse> findAllComments(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON).body(commentRepository.findAll(), Comment.class);
    }

    public Mono<ServerResponse> getCommentById(ServerRequest request) {
        return commentRepository.findById(request.pathVariable("id"))
                .flatMap(comment -> ok().contentType(APPLICATION_JSON).bodyValue(comment))
                .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, String.format("Comment with id = %s is not found",
                        request.pathVariable("id")))));
    }

    public Mono<ServerResponse> findCommentByContent(ServerRequest request) {
        Optional<String> content = request.queryParam("content");
        if (content.isPresent()) {
            return commentRepository.findByContent(content.get())
                    .flatMap(comment -> ok().contentType(APPLICATION_JSON).bodyValue(comment))
                    .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, String.format("Comment with content = %s is not found", content))));
        } else {
            throw new ServerWebInputException(BAD_REQUEST, "Content is empty");
        }
    }

    public Mono<ServerResponse> putComment(ServerRequest request) {
        Mono<Comment> commentMono = request.body(BodyExtractors.toMono(Comment.class))
                .filter(c -> c.getContent() != null)
                .flatMap(c -> commentRepository.findById(request.pathVariable("id"))
                        .map(comment -> {
                            c.setId(comment.getId());
                            return c;
                        })
                        .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, "Id does not exists"))));
        return saveComment(commentMono);
    }

    public Mono<ServerResponse> addComment(ServerRequest request) {
        Mono<Comment> commentMono = request.body(BodyExtractors.toMono(Comment.class));
        return saveComment(commentMono);
    }

    private Mono<ServerResponse> saveComment(Mono<Comment> commentMono) {
        return commentMono.flatMap(c -> bookRepository.findByTitle(c.getBook().getTitle())
                .map(b -> {
                    c.setBook(b);
                    return c;
                }).switchIfEmpty(Mono.error(() ->
                        new ServerWebInputException(BAD_REQUEST, "Book does not exists"))))
                .map(commentRepository::save)
                .flatMap(comment -> ok().contentType(APPLICATION_JSON).body(comment, Comment.class))
                .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, "Body is empty")));
    }

    public Mono<ServerResponse> deleteComment(ServerRequest request) {
        return noContent().build((commentRepository.deleteById(request.pathVariable("id"))));
    }
}
