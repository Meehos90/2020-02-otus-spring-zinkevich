package ru.otus.spring.dao.author;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.model.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(count).isEqualTo(3L);
    }

    @DisplayName("добавлять автора в БД")
    @Test
    void shouldToSaveAuthor() {
        Author expected = new Author(0, "EXPECTED_AUTHOR_FULLNAME");
        dao.save(expected);
        Author actual = dao.findById(expected.getId());
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("изменить автора в БД")
    @Test
    void shouldUpdateAuthor() {
        Author expected = new Author(3L, "EXPECTED_AUTHOR_FULLNAME");
        dao.save(expected);
        Author actual = dao.findById(3L);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("удалить автора из БД")
    @Test
    void shoudDeleteAuthor() {
        dao.deleteById(3L);
        long count = dao.count();
        assertThat(count).isEqualTo(2L);
    }

    @DisplayName("получить автора из БД по id")
    @Test
    void shouldGetByIdAuthor() {
        Author author = dao.findById(3L);
        assertThat(author.getId()).isEqualTo(3L);
    }

    @DisplayName("получить автора из БД по имени")
    @Test
    void shouldGetByFullnameAuthor() {
        Author author = dao.findByFullName("Говард Лавкрафт");
        assertThat(author.getFullName()).isEqualTo("Говард Лавкрафт");
    }

    @DisplayName("получить всех авторов из БД")
    @Test
    void shoudGetAllAuthors() {
        List<Author> authors = dao.findAll();
        assertThat(authors.size()).isEqualTo(3L);
    }
}