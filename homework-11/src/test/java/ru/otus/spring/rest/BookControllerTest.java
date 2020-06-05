package ru.otus.spring.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Mono;
import ru.otus.spring.dao.book.BookRepository;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

@SpringBootTest
public class BookControllerTest {
    @Autowired
    private RouterFunction route;
    @Autowired
    private BookRepository repository;

    private WebTestClient getClient() {
        return WebTestClient
                .bindToRouterFunction(route)
                .build();
    }

    private Book getBook() {
        Mono<Book> bookMono = repository.findByTitle("Хребты Безумия");
        return bookMono.block();
    }

    @DisplayName("Должен вернуть все книги из БД")
    @Test
    public void shouldReturnAllBooks() {
        getClient().get()
                .uri("/api/books")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Book.class)
                .hasSize(6);
    }

    @DisplayName("Должен найти книгу по id")
    @Test
    public void shouldReturnBookById() {
        getClient().get()
                .uri("/api/book/{id}", getBook().getId())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.title").isEqualTo("Хребты Безумия")
                .jsonPath("$.author.fullName").isEqualTo("Говард Лавкрафт")
                .jsonPath("$.genre.name").isEqualTo("ужасы");
    }

    @DisplayName("Должен вернуть ошибку поиска книги по id")
    @Test
    public void shouldNotReturnBookById() {
        getClient().get()
                .uri("/api/book/{id}", "5ed78e011a07a42968831df1")
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody()
                .isEmpty();
    }

    @DisplayName("Должен найти книгу по названию")
    @Test
    public void shouldReturnBookByTitle() {
        getBookByTitle("Хребты Безумия");
    }

    @DisplayName("Должен вернуть ошибку поиска по названию")
    @Test
    public void shouldNotReturnBookByTitle() {
        getClient().get()
                .uri("/api/book/find?title={title}", "Несуществующее название")
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody()
                .isEmpty();
    }

    @DisplayName("Должен добавить книгу в БД")
    @DirtiesContext
    @Test
    public void shouldAddBook() {
        Author author = new Author("Говард Лавкрафт");
        Genre genre = new Genre("ужасы");
        Book mist = new Book("Дагон", author, genre);
        getClient().post()
                .uri("/api/book/post")
                .body(Mono.just(mist), Book.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.title").isEqualTo("Дагон")
                .jsonPath("$.author.fullName").isEqualTo("Говард Лавкрафт")
                .jsonPath("$.genre.name").isEqualTo("ужасы");
        getBookByTitle("Дагон");
    }

    @DisplayName("Должен удалить книгу из БД")
    @Test
    public void shouldDeleteBook() {
        Mono<Book> bookMono = repository.findByTitle("Хребты Безумия");
        Book book = bookMono.block();
        getClient().delete().uri("/api/book/delete/{id}", book.getId())
                .exchange()
                .expectStatus()
                .isNoContent();
        getClient().get()
                .uri("/api/book/{id}", book.getId())
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    private void getBookByTitle(String title) {
        getClient().get()
                .uri("/api/book/find?title={title}", title)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.title").isEqualTo(title)
                .jsonPath("$.author.fullName").isEqualTo("Говард Лавкрафт")
                .jsonPath("$.genre.name").isEqualTo("ужасы");
    }
}
