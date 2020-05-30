package ru.otus.spring.handlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.spring.dao.author.AuthorRepository;
import ru.otus.spring.exception.NameRequiredException;
import ru.otus.spring.model.Author;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Slf4j
@RequiredArgsConstructor
public class AuthorHandler {
    private final AuthorRepository repository;

    public Mono<ServerResponse> findAllAuthors(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON).body(repository.findAll(), Author.class);
    }

    public Mono<ServerResponse> getAuthorById(ServerRequest request) {
       return repository.findById(request.pathVariable("id"))
               .flatMap(author -> ok().contentType(APPLICATION_JSON).bodyValue(author));
    }

    public Mono<ServerResponse> findAuthorByFullName(ServerRequest request) {
        return request.queryParam("fullName")
                .map(repository::findByFullName)
                .map(author -> ok().body(author, Author.class))
                .orElse(notFound().build());
    }

    public Mono<ServerResponse> findAuthorById(ServerRequest request) {
        return request.queryParam("id")
                .map(repository::findById)
                .map(author -> ok().body(author, Author.class))
                .orElse(notFound().build());
    }

    public Mono<ServerResponse> putAuthor(ServerRequest request) {
        log.debug("put author");
        return request.body(BodyExtractors.toMono(Author.class))
                .doOnNext(author -> {
                    author.setId(request.pathVariable("id"));
                    repository.save(author).subscribe();
                })
                .flatMap(author -> ok().contentType(APPLICATION_JSON).bodyValue(author))
                .switchIfEmpty(Mono.error(new NameRequiredException(BAD_REQUEST, "fullName is empty")));
    }

    public Mono<ServerResponse> addAuthor(ServerRequest request) {
              return request.body(BodyExtractors.toMono(Author.class))
                .map(repository::save)
                .flatMap(author -> ok().contentType(APPLICATION_JSON).body(author, Author.class));
    }

    public Mono<ServerResponse> deleteAuthor(ServerRequest request) {
        return noContent().build((repository.deleteById(request.pathVariable("id"))));
    }

}
