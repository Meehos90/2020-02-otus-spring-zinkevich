package ru.otus.spring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.author.AuthorRepository;
import ru.otus.spring.dao.book.BookRepository;
import ru.otus.spring.dao.comment.CommentRepository;
import ru.otus.spring.dao.genre.GenreRepository;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;
import ru.otus.spring.model.Genre;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class InitMongoDBData {
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    Author howardLovecraft = new Author("Говард Лавкрафт");
    Author johnTolkien = new Author("Джон Толкин");
    Author viktorFrankl = new Author("Виктор Франкл");

    Genre horror = new Genre("ужасы");
    Genre fantasy = new Genre("фэнтези");
    Genre psychotherapy = new Genre("психотерапия");

    Book mountainsOfMadness = new Book("Хребты Безумия", howardLovecraft, horror);
    Book callOfCthulhu = new Book("Зов Ктулху", howardLovecraft, horror);
    Book lordOfTheRings = new Book("Властелин колец", johnTolkien, fantasy);
    Book fellowshipOfTheRing = new Book("Братство кольца", johnTolkien, fantasy);
    Book searchForMeaning = new Book("Человек в поисках смысла", viktorFrankl, psychotherapy);
    Book willToMeaning = new Book("Воля к смыслу", viktorFrankl, psychotherapy);

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
                mountainsOfMadness,
                callOfCthulhu,
                lordOfTheRings,
                fellowshipOfTheRing,
                searchForMeaning,
                willToMeaning
        )).subscribe();
    }

    @Bean void initComments() {
        commentRepository.saveAll(Arrays.asList(
                new Comment("страшная книга", mountainsOfMadness),
                new Comment("очень понравилось", callOfCthulhu),
                new Comment("крутая книжка, автор молодец", lordOfTheRings),
                new Comment("можно почитать", fellowshipOfTheRing),
                new Comment("читаю в метро, пока интересно", searchForMeaning),
                new Comment("какая-то философия", willToMeaning)
        )).subscribe();
    }
}
