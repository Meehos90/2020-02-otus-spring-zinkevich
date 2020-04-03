package ru.otus.spring.dao.author;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.model.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.spring.dao.Constants.*;

@DisplayName("Dao для работы с авторами книг")
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDaoJdbc dao;

    @DisplayName("возвращать ожидаемое количество авторов")
    @Test
    void shoudReturnExpectedAuthorCount() {
        int count = dao.count();
        assertThat(count).isEqualTo(DEFAULT_COUNT);
    }

    @DisplayName("добавлять автора в БД")
    @Test
    void shoudInsertAuthor() {
        Author expected = new Author(4L, EXPECTED_AUTHOR_FULLNAME);
        dao.insert(expected);
        Author actual = dao.getById(expected.getId());
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("изменить автора в БД")
    @Test
    void shouldUpdateAuthor() {
        Author expected = new Author(TEST_ID, EXPECTED_AUTHOR_FULLNAME);
        dao.update(expected);
        Author actual = dao.getById(TEST_ID);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("удалить автора из БД")
    @Test
    void shoudDeleteAuthor() {
        dao.delete(TEST_ID);
        int count = dao.count();
        assertThat(count).isEqualTo(EXPECTED_COUNT);
    }

    @DisplayName("получить автора из БД по id")
    @Test
    void shouldGetByIdAuthor() {
       Author author = dao.getById(TEST_ID);
       assertThat(author.getId()).isEqualTo(TEST_ID);
    }

    @DisplayName("получить автора из БД по имени")
    @Test
    void shouldGetByFullnameAuthor() {
        Author author = dao.getByFullname(TEST_AUTHOR_FULLNAME);
        assertThat(author.getFullName()).isEqualTo(TEST_AUTHOR_FULLNAME);
    }

    @DisplayName("получить всех авторов из БД")
    @Test
    void ShoudGetAllAuthors() {
        List<Author> authors = dao.getAll();
        assertThat(authors.size()).isEqualTo(DEFAULT_COUNT);
    }
}