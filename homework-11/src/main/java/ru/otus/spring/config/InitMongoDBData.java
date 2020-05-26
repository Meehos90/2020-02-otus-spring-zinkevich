package ru.otus.spring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.author.AuthorRepository;
import ru.otus.spring.model.Author;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class InitMongoDBData {
    private final AuthorRepository authorRepository;

    @Bean
    public void initAuthors() {
        authorRepository.saveAll(Arrays.asList(
                new Author("Говард Лавкрафт"),
                new Author("Стивен Кинг"),
                new Author("Дин Кунц"),
                new Author("Lovecraft")
        )).subscribe(a -> System.out.println(a.getFullName()));
    }
}
