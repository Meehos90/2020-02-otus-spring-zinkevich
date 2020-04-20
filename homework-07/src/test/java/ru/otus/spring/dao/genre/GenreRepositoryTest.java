package ru.otus.spring.dao.genre;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.exception.NoEntityException;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Genre;

import static org.junit.jupiter.api.Assertions.*;
import static ru.otus.spring.dao.Constants.Authors.EXPECTED_AUTHOR_FULLNAME;
import static ru.otus.spring.dao.Constants.Authors.TEST_AUTHOR_ID;
import static ru.otus.spring.dao.Constants.EXPECTED_QERIES_COUNT;
import static ru.otus.spring.dao.Constants.Genres.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с жанрами")
@DataJpaTest
class GenreRepositoryTest {

    @Autowired
    GenreRepository repository;
    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемое количество жанров")
    @Test
    void shoudReturnExpectedGenreCount() {
        long count = repository.count();
        assertThat(count).isEqualTo(DEFAULT_GENRES_COUNT);
    }

    @DisplayName("добавлять жанр в БД")
    @Test
    void shoudInsertGenre() {
        Genre expected = new Genre(0, EXPECTED_GENRE_NAME);
        repository.save(expected);
        Genre actual = repository.findById(expected.getId());
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("изменить жанр в БД")
    @Test
    void shouldUpdateGenre() {
        Genre expected = new Genre(TEST_GENRE_ID, EXPECTED_GENRE_NAME);
        repository.save(expected);
        Genre actual = repository.findById(TEST_GENRE_ID);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("удалить жанр из БД")
    @Test
    void shoudDeleteGenre() {
        repository.deleteById(TEST_GENRE_ID);
        Genre genre = repository.findById(TEST_GENRE_ID);
        assertNull(genre);
    }

    @DisplayName("получить жанр из БД по id")
    @Test
    void shouldGetByIdGenre() {
        Genre genre = repository.findById(TEST_GENRE_ID);
        assertThat(genre.getId()).isEqualTo(TEST_GENRE_ID);
    }

    @DisplayName("получить жанр из БД по названию")
    @Test
    void shouldGetByNameGenre() {
        Genre genre = repository.findByName(TEST_GENRE_NAME);
        assertThat(genre.getName()).isEqualTo(TEST_GENRE_NAME);
    }

    @DisplayName("получить все жанры из БД")
    @Test
    void shoudGetAllGenres() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        val genres = repository.findAll();
        assertThat(genres).isNotNull().hasSize(EXEPECTED_NUMBER_OF_GENRES)
                .allMatch(g -> g.getName() != null);
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QERIES_COUNT);
    }

}