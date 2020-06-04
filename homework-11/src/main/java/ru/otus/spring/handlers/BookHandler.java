package ru.otus.spring.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.dao.author.AuthorRepository;
import ru.otus.spring.dao.book.BookRepository;
import ru.otus.spring.exception.ServerWebInputException;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.noContent;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RequiredArgsConstructor
public class BookHandler {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public Mono<ServerResponse> findAllBooks(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON).body(bookRepository.findAll(), Book.class);
    }

    public Mono<ServerResponse> getBookById(ServerRequest request) {
        return bookRepository.findById(request.pathVariable("id"))
                .flatMap(book -> ok().contentType(APPLICATION_JSON).bodyValue(book))
                .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, String.format("Book with id = %s is not found",
                        request.pathVariable("id")))));
    }

    public Mono<ServerResponse> findBookByTitle(ServerRequest request) {
        Optional<String> title = request.queryParam("title");
        if (title.isPresent()) {
            return bookRepository.findByTitle(title.get())
                    .flatMap(book -> ok().contentType(APPLICATION_JSON).bodyValue(book))
                    .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, String.format("Book with title = %s is not found", title))));
        } else {
            throw new ServerWebInputException(BAD_REQUEST, "Title is empty");
        }
    }

    public Mono<ServerResponse> putBook(ServerRequest request) {
        return request.body(BodyExtractors.toMono(Book.class))
                .filter(b -> b.getTitle() != null)
                .map(b -> {
                    b.setId(request.pathVariable("id"));
                    return bookRepository.save(b);
                })
                .flatMap(book -> ok().contentType(APPLICATION_JSON).body(book, Book.class))
                .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, "Body is empty")));
    }

    public Mono<ServerResponse> addBook(ServerRequest request) {
        Flux<String> authorsName = authorRepository.findAll()
                .map(Author::getFullName)
                .collectList()
                .flatMapMany(Flux::fromIterable);

        return Flux.zip(request.body(BodyExtractors.toMono(Book.class)), authorsName)
                .filter(obj -> obj.getT2().contains(obj.getT1().getAuthor().getFullName()))
                .map(objects -> {
                   return bookRepository.save(objects.getT1());
                } )
                .flatMap(book -> ok().contentType(APPLICATION_JSON).body(book, Book.class))
                .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, "Body is empty")));

       /* return request.body(BodyExtractors.toMono(Book.class))
                .map(bookRepository::save)
                .flatMap(book -> ok().contentType(APPLICATION_JSON).body(book, Book.class))
                .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, "Body is empty")));*/
    }

    public Mono<ServerResponse> deleteBook(ServerRequest request) {
        return noContent().build((bookRepository.deleteById(request.pathVariable("id"))));
    }

}
