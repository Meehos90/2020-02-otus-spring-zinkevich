package ru.otus.spring.dao.author;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.exception.NoEntityException;
import ru.otus.spring.model.Author;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.otus.spring.dao.Constants.Authors.*;
import static ru.otus.spring.dao.Constants.EXPECTED_QERIES_COUNT;

@DisplayName("Dao для работы с авторами книг")
@DataJpaTest
@Import(AuthorDaoJpa.class)
class AuthorDaoJpaTest {

    @Autowired
    private AuthorDaoJpa dao;
    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемое количество авторов")
    @Test
    void shouldReturnExpectedAuthorCount() {
        long count = dao.count();
        assertThat(count).isEqualTo(DEFAULT_AUTHORS_COUNT);
    }

    @DisplayName("добавлять автора в БД")
    @Test
    void shouldToSaveAuthor() {
        Author expected = new Author(0, EXPECTED_AUTHOR_FULLNAME);
        dao.save(expected);
        Author actual = dao.findById(expected.getId());
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("изменить автора в БД")
    @Test
    void shouldUpdateAuthor() {
        Author expected = new Author(TEST_AUTHOR_ID, EXPECTED_AUTHOR_FULLNAME);
        dao.save(expected);
        Author actual = dao.findById(TEST_AUTHOR_ID);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("удалить автора из БД")
    @Test
    void shoudDeleteAuthor() {
        dao.deleteById(TEST_AUTHOR_ID);
        Throwable thrown = assertThrows(NoEntityException.class, () -> {
            dao.findById(TEST_AUTHOR_ID);
        });
        assertNotNull(thrown.getMessage());
    }

    @DisplayName("получить автора из БД по id")
    @Test
    void shouldGetByIdAuthor() {
        Author author = dao.findById(TEST_AUTHOR_ID);
        assertThat(author.getId()).isEqualTo(TEST_AUTHOR_ID);
    }

    @DisplayName("получить автора из БД по имени")
    @Test
    void shouldGetByFullnameAuthor() {
        Author author = dao.findByFullName(TEST_AUTHOR_FULLNAME);
        assertThat(author.getFullName()).isEqualTo(TEST_AUTHOR_FULLNAME);
    }

    @DisplayName("получить всех авторов из БД")
    @Test
    void shoudGetAllAuthors() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        val authors = dao.findAll();
        assertThat(authors).isNotNull().hasSize(EXEPECTED_NUMBER_OF_AUTHORS)
                .allMatch(a -> a.getFullName() != null);
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QERIES_COUNT);
    }
}