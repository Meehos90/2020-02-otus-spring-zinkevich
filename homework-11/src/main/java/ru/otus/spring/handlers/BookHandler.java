package ru.otus.spring.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.spring.dao.author.AuthorRepository;
import ru.otus.spring.dao.book.BookRepository;
import ru.otus.spring.dao.genre.GenreRepository;
import ru.otus.spring.exception.ServerWebInputException;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.noContent;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RequiredArgsConstructor
public class BookHandler {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

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
        Mono<Book> bookMono = request.body(BodyExtractors.toMono(Book.class))
                .filter(b -> b.getTitle() != null)
                .flatMap(b -> bookRepository.findById(request.pathVariable("id"))
                        .map(book -> {
                            b.setId(book.getId());
                            return b;
                        })
                        .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, "Id does not exists"))));
        return saveBook(bookMono);
    }

    public Mono<ServerResponse> addBook(ServerRequest request) {
        Mono<Book> bookMono = request.body(BodyExtractors.toMono(Book.class));
        return saveBook(bookMono);
    }

    private Mono<ServerResponse> saveBook(Mono<Book> bookMono) {
        return bookMono.flatMap(b -> authorRepository.findByFullName(b.getAuthor().getFullName())
                .map(a -> {
                    b.setAuthor(a);
                    return b;
                }).switchIfEmpty(Mono.error(() ->
                        new ServerWebInputException(BAD_REQUEST, "Author does not exists"))))
                .flatMap(b -> genreRepository.findByName(b.getGenre().getName())
                        .map(g -> {
                            b.setGenre(g);
                            return b;
                        })).switchIfEmpty(Mono.error(() ->
                        new ServerWebInputException(BAD_REQUEST, "Genre does not exists")))
                .map(bookRepository::save)
                .flatMap(book -> ok().contentType(APPLICATION_JSON).body(book, Book.class))
                .switchIfEmpty(Mono.error(new ServerWebInputException(BAD_REQUEST, "Body is empty")));
    }

    public Mono<ServerResponse> deleteBook(ServerRequest request) {
        return noContent().build((bookRepository.deleteById(request.pathVariable("id"))));
    }

}
