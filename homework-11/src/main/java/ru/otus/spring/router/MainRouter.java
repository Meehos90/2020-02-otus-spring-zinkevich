package ru.otus.spring.router;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.spring.dao.author.AuthorRepository;
import ru.otus.spring.dao.book.BookRepository;
import ru.otus.spring.dao.comment.CommentRepository;
import ru.otus.spring.dao.genre.GenreRepository;
import ru.otus.spring.handlers.AuthorHandler;
import ru.otus.spring.handlers.BookHandler;
import ru.otus.spring.handlers.CommentHandler;
import ru.otus.spring.handlers.GenreHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class MainRouter {

    @Bean
    public RouterFunction<ServerResponse> functionalRoute(AuthorRepository authorRepository,
                                                          GenreRepository genreRepository,
                                                          BookRepository bookRepository,
                                                          CommentRepository commentRepository) {
        AuthorHandler authorHandler = new AuthorHandler(authorRepository);
        GenreHandler genreHandler = new GenreHandler(genreRepository);
        BookHandler bookHandler = new BookHandler(bookRepository, authorRepository);
        CommentHandler commentHandler = new CommentHandler(commentRepository);

        RouterFunction<ServerResponse> route = route()
                .GET("/api/authors", accept(APPLICATION_JSON), authorHandler::findAllAuthors)
                .GET("/api/author/find", queryParam("fullName", StringUtils::isNotEmpty), authorHandler::findAuthorByFullName)
                .GET("/api/author/{id}", accept(APPLICATION_JSON), authorHandler::getAuthorById)
                .PUT("/api/author/put/{id}", accept(APPLICATION_JSON), authorHandler::putAuthor)
                .POST("/api/author/post", authorHandler::addAuthor)
                .DELETE("/api/author/delete/{id}", accept(APPLICATION_JSON), authorHandler::deleteAuthor)

                .GET("/api/genres", accept(APPLICATION_JSON), genreHandler::findAllGenres)
                .GET("/api/genre/find", queryParam("name", StringUtils::isNotEmpty), genreHandler::findGenreByName)
                .GET("/api/genre/{id}", accept(APPLICATION_JSON), genreHandler::getGenreById)
                .PUT("/api/genre/put/{id}", accept(APPLICATION_JSON), genreHandler::putGenre)
                .POST("/api/genre/post", genreHandler::addGenre)
                .DELETE("/api/genre/delete/{id}", accept(APPLICATION_JSON), genreHandler::deleteGenre)

                .GET("/api/books", accept(APPLICATION_JSON), bookHandler::findAllBooks)
                .GET("/api/book/find", queryParam("title", StringUtils::isNotEmpty), bookHandler::findBookByTitle)
                .GET("/api/book/{id}", accept(APPLICATION_JSON), bookHandler::getBookById)
                .PUT("/api/book/put/{id}", accept(APPLICATION_JSON), bookHandler::putBook)
                .POST("/api/book/post", bookHandler::addBook)
                .DELETE("/api/book/delete/{id}", accept(APPLICATION_JSON), bookHandler::deleteBook)

                .GET("/api/comments", accept(APPLICATION_JSON), commentHandler::findAllComments)
                .GET("/api/comment/find", queryParam("content", StringUtils::isNotEmpty), commentHandler::findCommentByContent)
                .GET("/api/comment/{id}", accept(APPLICATION_JSON), commentHandler::getCommentById)
                .PUT("/api/comment/put/{id}", accept(APPLICATION_JSON), commentHandler::putComment)
                .POST("/api/comment/post", commentHandler::addComment)
                .DELETE("/api/comment/delete/{id}", accept(APPLICATION_JSON), commentHandler::deleteComment)

                .build();
        return route;
    }
}
