package ru.otus.spring.repository;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.spring.dao.book.BookRepository;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import java.util.Arrays;

@DataMongoTest
public class BookRepositoryTest {
    private Author author = new Author("Стивен Кинг");
    private Genre genre = new Genre("ужасы");
    private Book mist = new Book("Мгла", author, genre);
    private Book it = new Book("Оно", author, genre);
    private Book petSematary = new Book("Кладбище домашних животных", author, genre);

    @Autowired
    private BookRepository repository;

    @DisplayName("Должен вернуть количество книг в БД")
    @DirtiesContext
    @Test
    public void shouldReturnBooksCount() {
        repository.saveAll(Arrays.asList(mist, it, petSematary)).subscribe();
        StepVerifier
                .create(repository.count())
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

    @DisplayName("Должен вернуть все книги из БД")
    @DirtiesContext
    @Test
    public void shouldReturnAllBooks() {
        repository.saveAll(Arrays.asList(mist, it, petSematary)).blockLast();
        StepVerifier
                .create(repository.findAll())
                .expectSubscription()
                .expectNextMatches(book -> book.getTitle().equals("Мгла"))
                .expectNextMatches(book -> book.getTitle().equals("Оно"))
                .expectNextMatches(book -> book.getTitle().equals("Кладбище домашних животных"))
                .expectComplete()
                .verify();
    }

    @DisplayName("Должен добавить книгу в БД")
    @DirtiesContext
    @Test
    public void shouldSetIdOnSave() {
        Mono<Book> bookMono = repository.save(mist);
        StepVerifier
                .create(bookMono)
                .assertNext(book -> Assert.assertNotNull(book.getId()))
                .expectComplete()
                .verify();
    }

    @DisplayName("Должен найти книгу по названию")
    @DirtiesContext
    @Test
    public void shouldFindByTitle() {
        repository.save(mist).block();
        StepVerifier
                .create(repository.findByTitle("Мгла"))
                .expectSubscription()
                .expectNextMatches(book -> book.getTitle().equals("Мгла"))
                .expectComplete()
                .verify();
    }

    @DisplayName("Должен найти книгу по id")
    @DirtiesContext
    @Test
    public void shouldFindById() {
        repository.save(mist).block();
        Mono<Book> bookMono = repository.findByTitle("Мгла");
        Book b = bookMono.block();
        StepVerifier.create(
                repository.findById(b.getId()))
                .expectSubscription()
                .expectNextMatches(book -> book.getTitle().equals("Мгла"))
                .expectComplete()
                .verify();
    }

    @DisplayName("Должен удалить книгу из БД")
    @DirtiesContext
    @Test
    public void shouldDeleteById() {
        repository.save(mist).block();
        Mono<Book> bookMono = repository.findByTitle("Мгла");
        Book b = bookMono.block();
        StepVerifier.create(
                repository.deleteById(b.getId()))
                .expectSubscription()
                .expectComplete()
                .verify();
    }
}
