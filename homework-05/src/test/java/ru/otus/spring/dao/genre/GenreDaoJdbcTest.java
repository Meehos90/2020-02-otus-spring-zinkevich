package ru.otus.spring.dao.genre;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.model.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.spring.dao.Constants.*;

@DisplayName("Dao для работы с жанрами")
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    @Autowired
    GenreDaoJdbc dao;

    @DisplayName("возвращать ожидаемое количество жанров")
    @Test
    void shoudReturnExpectedGenreCount() {
        int count = dao.count();
        assertThat(count).isEqualTo(DEFAULT_COUNT);
    }

    @DisplayName("добавлять жанр в БД")
    @Test
    void shoudInsertAuthor() {
        Genre expected = new Genre(4L, EXPECTED_GENRE_NAME);
        dao.insert(expected);
        Genre actual = dao.getById(expected.getId());
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("изменить жанр в БД")
    @Test
    void shouldUpdateGenre() {
        Genre expected = new Genre(TEST_ID, EXPECTED_GENRE_NAME);
        dao.update(expected);
        Genre actual = dao.getById(TEST_ID);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("удалить жанр из БД")
    @Test
    void shoudDeleteGenre() {
        dao.delete(TEST_ID);
        int count = dao.count();
        assertThat(count).isEqualTo(EXPECTED_COUNT);
    }

    @DisplayName("получить жанр из БД по id")
    @Test
    void shouldGetByIdGenre() {
        Genre genre = dao.getById(TEST_ID);
        assertThat(genre.getId()).isEqualTo(TEST_ID);
    }

    @DisplayName("получить жанр из БД по названию")
    @Test
    void shouldGetByNameGenre() {
        Genre genre = dao.getByName(EXPECTED_GENRE_NAME);
        assertThat(genre.getName()).isEqualTo(EXPECTED_GENRE_NAME);
    }

    @DisplayName("получить все жанры из БД")
    @Test
    void shoudGetAllGenres() {
        List<Genre> genres = dao.getAll();
        assertThat(genres.size()).isEqualTo(DEFAULT_COUNT);
    }
}