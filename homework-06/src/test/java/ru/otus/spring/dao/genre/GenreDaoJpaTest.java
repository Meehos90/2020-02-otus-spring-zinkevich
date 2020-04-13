package ru.otus.spring.dao.genre;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.exception.NoEntityException;
import ru.otus.spring.model.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.otus.spring.dao.Constants.Authors.EXPECTED_QERIES_COUNT;
import static ru.otus.spring.dao.Constants.Genres.*;

@DisplayName("Dao для работы с жанрами")
@DataJpaTest
@Import(GenreDaoJpa.class)
class GenreDaoJpaTest {

    @Autowired
    GenreDaoJpa dao;
    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемое количество жанров")
    @Test
    void shoudReturnExpectedGenreCount() {
        long count = dao.count();
        assertThat(count).isEqualTo(DEFAULT_GENRES_COUNT);
    }

    @DisplayName("добавлять жанр в БД")
    @Test
    void shoudInsertGenre() {
        Genre expected = new Genre(0, EXPECTED_GENRE_NAME);
        dao.save(expected);
        Genre actual = dao.findById(expected.getId());
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("изменить жанр в БД")
    @Test
    void shouldUpdateGenre() {
        dao.deleteById(TEST_GENRE_ID);
        Throwable thrown = assertThrows(NoEntityException.class, () -> {
            dao.findById(TEST_GENRE_ID);
        });
        assertNotNull(thrown.getMessage());
    }

    @DisplayName("удалить жанр из БД")
    @Test
    void shoudDeleteGenre() {
        dao.deleteById(TEST_GENRE_ID);
        long count = dao.count();
        assertThat(count).isEqualTo(DEFAULT_COUNT_AFTER_DELETE);
    }

    @DisplayName("получить жанр из БД по id")
    @Test
    void shouldGetByIdGenre() {
        Genre genre = dao.findById(TEST_GENRE_ID);
        assertThat(genre.getId()).isEqualTo(TEST_GENRE_ID);
    }

    @DisplayName("получить жанр из БД по названию")
    @Test
    void shouldGetByNameGenre() {
        Genre genre = dao.findByName(TEST_GENRE_NAME);
        assertThat(genre.getName()).isEqualTo(TEST_GENRE_NAME);
    }

    @DisplayName("получить все жанры из БД")
    @Test
    void shoudGetAllGenres() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        val genres = dao.findAll();
        assertThat(genres).isNotNull().hasSize(EXEPECTED_NUMBER_OF_GENRES)
                .allMatch(g -> g.getName() != null);
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QERIES_COUNT);
    }

}