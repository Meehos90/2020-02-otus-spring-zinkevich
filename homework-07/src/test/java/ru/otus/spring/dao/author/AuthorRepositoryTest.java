package ru.otus.spring.dao.author;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.model.Author;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.otus.spring.dao.Constants.Authors.*;
import static ru.otus.spring.dao.Constants.EXPECTED_QERIES_COUNT;

@DisplayName("Dao для работы с авторами книг")
@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;
    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемое количество авторов")
    @Test
    void shouldReturnExpectedAuthorCount() {
        long count = repository.count();
        assertThat(count).isEqualTo(DEFAULT_AUTHORS_COUNT);
    }

    @DisplayName("добавлять автора в БД")
    @Test
    void shouldToSaveAuthor() {
        Author expected = new Author(0, EXPECTED_AUTHOR_FULLNAME);
        repository.save(expected);
        Author actual = repository.findById(expected.getId());
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("изменить автора в БД")
    @Test
    void shouldUpdateAuthor() {
        Author expected = new Author(TEST_AUTHOR_ID, EXPECTED_AUTHOR_FULLNAME);
        repository.save(expected);
        Author actual = repository.findById(TEST_AUTHOR_ID);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("удалить автора из БД")
    @Test
    void shoudDeleteAuthor() {
        repository.deleteById(TEST_AUTHOR_ID);
        Author author = repository.findById(TEST_AUTHOR_ID);
        assertNull(author);
    }

    @DisplayName("получить автора из БД по id")
    @Test
    void shouldGetByIdAuthor() {
        Author author = repository.findById(TEST_AUTHOR_ID);
        assertThat(author.getId()).isEqualTo(TEST_AUTHOR_ID);
    }

    @DisplayName("получить автора из БД по имени")
    @Test
    void shouldGetByFullnameAuthor() {
        Author author = repository.findByFullName(TEST_AUTHOR_FULLNAME);
        assertThat(author.getFullName()).isEqualTo(TEST_AUTHOR_FULLNAME);
    }

    @DisplayName("получить всех авторов из БД")
    @Test
    void shoudGetAllAuthors() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        val authors = repository.findAll();
        assertThat(authors).isNotNull().hasSize(EXEPECTED_NUMBER_OF_AUTHORS)
                .allMatch(a -> a.getFullName() != null);
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QERIES_COUNT);
    }
}