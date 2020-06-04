package ru.otus.spring.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.spring.dao.genre.GenreRepository;
import ru.otus.spring.exception.ServerWebInputException;
import ru.otus.spring.model.Genre;

import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.noContent;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RequiredArgsConstructor
public class GenreHandler {
    private final GenreRepository repository;

    public Mono<ServerResponse> findAllGenres(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON).body(repository.findAll(), Genre.class);
    }

    public Mono<ServerResponse> getGenreById(ServerRequest request) {
        return repository.findById(request.pathVariable("id"))
                .flatMap(genre -> ok().contentType(APPLICATION_JSON).bodyValue(genre))
                .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, String.format("Genre with id = %s is not found",
                        request.pathVariable("id")))));
    }

    public Mono<ServerResponse> findGenreByName(ServerRequest request) {
        Optional<String> name = request.queryParam("name");
        if (name.isPresent()) {
            return repository.findByName(name.get())
                    .flatMap(genre -> ok().contentType(APPLICATION_JSON).bodyValue(genre))
                    .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, String.format("Genre with name = %s is not found", name))));
        } else {
            throw new ServerWebInputException(BAD_REQUEST, "Name is empty");
        }
    }

    public Mono<ServerResponse> putGenre(ServerRequest request) {
        return request.body(BodyExtractors.toMono(Genre.class))
                .filter(g -> g.getName() != null)
                .map(g -> {
                    g.setId(request.pathVariable("id"));
                    return repository.save(g);
                })
                .flatMap(genre -> ok().contentType(APPLICATION_JSON).body(genre, Genre.class))
                .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, "Body is empty")));
    }

    public Mono<ServerResponse> addGenre(ServerRequest request) {
        return request.body(BodyExtractors.toMono(Genre.class))
                .map(repository::save)
                .flatMap(genre -> ok().contentType(APPLICATION_JSON).body(genre, Genre.class))
                .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, "Body is empty")));
    }

    public Mono<ServerResponse> deleteGenre(ServerRequest request) {
        return noContent().build((repository.deleteById(request.pathVariable("id"))));
    }
}
