package ru.otus.spring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.author.AuthorRepository;
import ru.otus.spring.dao.book.BookRepository;
import ru.otus.spring.dao.genre.GenreRepository;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class InitMongoDBData {
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    Author howardLovecraft = new Author("Говард Лавкрафт");
    Author johnTolkien = new Author("Джон Толкин");
    Author viktorFrankl = new Author("Виктор Франкл");

    Genre horror = new Genre("ужасы");
    Genre fantasy = new Genre("фэнтези");
    Genre psychotherapy = new Genre("психотерапия");

    @Bean
    public void initAuthors() {
        authorRepository.saveAll(Arrays.asList(
                howardLovecraft,
                johnTolkien,
                viktorFrankl
        )).subscribe();
    }

    @Bean
    void initGenres() {
        genreRepository.saveAll(Arrays.asList(
                horror,
                fantasy,
                psychotherapy
        )).subscribe();
    }

    @Bean void initBooks() {
        bookRepository.saveAll(Arrays.asList(
                new Book("Хребты Безумия", howardLovecraft, horror),
                new Book("Зов Ктулху", howardLovecraft, horror),
                new Book("Властелин колец", johnTolkien, fantasy),
                new Book("Братство кольца", johnTolkien, fantasy),
                new Book("Человек в поисках смысла", viktorFrankl, psychotherapy),
                new Book("Воля к смыслу", viktorFrankl, psychotherapy)
        )).subscribe();
    }
}
