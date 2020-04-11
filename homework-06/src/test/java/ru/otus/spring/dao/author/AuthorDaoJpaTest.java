package ru.otus.spring.dao.author;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.model.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.spring.dao.Constants.Authors.*;

@DisplayName("Dao для работы с авторами книг")
@DataJpaTest
@Import(AuthorDaoJpa.class)
class AuthorDaoJpaTest {

    @Autowired
    private AuthorDaoJpa dao;

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
        long count = dao.count();
        assertThat(count).isEqualTo(DEFAULT_COUNT_AFTER_DELETE);
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
        List<Author> authors = dao.findAll();
        assertThat(authors.size()).isEqualTo(TEST_AUTHOR_ID);
    }
}