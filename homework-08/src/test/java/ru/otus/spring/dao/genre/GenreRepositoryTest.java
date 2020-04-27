package ru.otus.spring.dao.genre;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.dao.AbstractRepositoryTest;
import ru.otus.spring.model.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.otus.spring.Constants.*;

@DisplayName("Dao для работы с жанрами")
class GenreRepositoryTest extends AbstractRepositoryTest {
    
    @Autowired
    GenreRepository repository;
    
    @DisplayName("возвращать ожидаемое количество жанров")
    @Test
    void shoudReturnExpectedGenreCount() {
        long count = repository.count();
        assertThat(count).isEqualTo(1);
    }
    
    @DisplayName("добавлять жанр в БД")
    @Test
    void shoudInsertGenre() {
        Genre expected = new Genre(NEW_GENRE);
        repository.save(expected);
        Genre actual = repository.findById(expected.getId());
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }
    
    @DisplayName("изменить жанр в БД")
    @DirtiesContext
    @Test
    void shouldUpdateGenre() {
        Genre expected = new Genre(getGenreId(TEST_GENRE), EXPECTED_GENRE);
        repository.save(expected);
        Genre actual = repository.findByName(EXPECTED_GENRE);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }
    
    @DisplayName("удалить жанр из БД")
    @DirtiesContext
    @Test
    void shoudDeleteGenre() {
        Genre genre = repository.findByName(TEST_GENRE);
        String id = genre.getId();
        repository.deleteById(id);
        assertNull(repository.findByIdIsNull(id));
    }
    
    @DisplayName("получить жанр из БД по id")
    @Test
    void shouldGetByIdGenre() {
        Genre genre = repository.findById(getGenreId(TEST_GENRE));
        assertThat(genre.getId()).isEqualTo(getGenreId(TEST_GENRE));
    }
    
    @DisplayName("получить жанр из БД по названию")
    @Test
    void shouldGetByNameGenre() {
        Genre genre = repository.findByName(TEST_GENRE);
        assertThat(genre.getName()).isEqualTo(TEST_GENRE);
    }
    
    @DisplayName("получить все жанры из БД")
    @Test
    void shoudGetAllGenres() {
        val genres = repository.findAll();
        assertThat(genres).isNotNull().hasSize(1)
                .allMatch(g -> g.getName() != null);
    }
    
    private String getGenreId(String name) {
        Genre g = repository.findByName(name);
        return g.getId();
    }
    
}