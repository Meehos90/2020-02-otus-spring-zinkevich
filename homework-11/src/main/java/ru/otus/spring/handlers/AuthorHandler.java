package ru.otus.spring.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.spring.dao.author.AuthorRepository;
import ru.otus.spring.exception.ServerWebInputException;
import ru.otus.spring.model.Author;

import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.noContent;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RequiredArgsConstructor
public class AuthorHandler {
    private final AuthorRepository repository;

    public Mono<ServerResponse> findAllAuthors(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON).body(repository.findAll(), Author.class);
    }

    public Mono<ServerResponse> getAuthorById(ServerRequest request) {
        return repository.findById(request.pathVariable("id"))
                .flatMap(author -> ok().contentType(APPLICATION_JSON).bodyValue(author))
                .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, String.format("Author with id = %s is not found",
                        request.pathVariable("id")))));
    }

    public Mono<ServerResponse> findAuthorByFullName(ServerRequest request) {
        Optional<String> fullName = request.queryParam("fullName");
        if (fullName.isPresent()) {
            return repository.findByFullName(fullName.get())
                    .flatMap(author -> ok().contentType(APPLICATION_JSON).bodyValue(author))
                    .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, String.format("Author with fullName = %s is not found", fullName))));
        } else {
            throw new ServerWebInputException(BAD_REQUEST, "Fullname is empty");
        }
    }

    public Mono<ServerResponse> putAuthor(ServerRequest request) {
        return request.body(BodyExtractors.toMono(Author.class))
                .filter(a -> a.getFullName() != null)
                .flatMap(a -> repository.findById(request.pathVariable("id"))
                        .map(author -> {
                            a.setId(author.getId());
                            return repository.save(a);
                        })
                        .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, "Id does not exists"))))
                .flatMap(author -> ok().contentType(APPLICATION_JSON).body(author, Author.class))
                .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, "Body is empty")));
    }

    public Mono<ServerResponse> addAuthor(ServerRequest request) {
        return request.body(BodyExtractors.toMono(Author.class))
                .map(repository::save)
                .flatMap(author -> ok().contentType(APPLICATION_JSON).body(author, Author.class))
                .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, "Body is empty")));
    }

    public Mono<ServerResponse> deleteAuthor(ServerRequest request) {
        return noContent().build((repository.deleteById(request.pathVariable("id"))));
    }
}
