package ru.otus.spring.rest.author;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.spring.dao.author.AuthorRepository;
import ru.otus.spring.model.Author;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

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

    public Mono<ServerResponse> getAuthorByFullName(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON)
                .body(repository.findByFullName(request.queryParam("name").get()), Author.class);
    }

    public Mono<ServerResponse> getAuthorByFullName2(ServerRequest request) {
        return request.queryParam("name")
                .map(repository::findByFullName)
                .map(author -> ok().body(author, Author.class))
                .orElse(notFound().build());
    }

    public Mono<ServerResponse> addAuthor(ServerRequest request) {
        return request.body(BodyExtractors.toMono(Author.class))
                .map(repository::save)
                .flatMap(author -> ok().contentType(APPLICATION_JSON).body(author, Author.class));
    }

    public Mono<ServerResponse> deleteAuthor(ServerRequest request) {
        return null;
    }
}
