package ru.otus.spring.dao.author;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.dao.AbstractRepositoryTest;
import ru.otus.spring.model.Author;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.otus.spring.Constants.*;

@DisplayName("Dao для работы с авторами книг")
class AuthorRepositoryTest extends AbstractRepositoryTest {
    
    @Autowired
    private AuthorRepository repository;
    
    @DisplayName("возвращать ожидаемое количество авторов")
    @DirtiesContext
    @Test
    void shouldReturnExpectedAuthorCount() {
        long count = repository.count();
        assertThat(count).isEqualTo(1);
    }
    
    @DisplayName("добавлять автора в БД")
    @Test
    void shouldToSaveAuthor() {
        Author expected = new Author(NEW_AUTHOR);
        repository.save(expected);
        Author actual = repository.findById(expected.getId());
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }
    
    @DisplayName("изменить автора в БД")
    @DirtiesContext
    @Test
    void shouldUpdateAuthor() {
        Author expected = new Author(getAuthorId(TEST_AUTHOR), EXPECTED_AUTHOR);
        repository.save(expected);
        String id = getAuthorId(EXPECTED_AUTHOR);
        Author actual = repository.findById(id);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }
    
    @DisplayName("удалить автора из БД")
    @DirtiesContext
    @Test
    void shoudDeleteAuthor() {
        Author author = repository.findByFullName(TEST_AUTHOR);
        String id = author.getId();
        repository.deleteById(id);
        assertNull(repository.findByIdIsNull(id));
    }
    
    @DisplayName("получить автора из БД по id")
    @Test
    void shouldGetByIdAuthor() {
        Author author = repository.findById(getAuthorId(TEST_AUTHOR));
        assertThat(author.getId()).isEqualTo(getAuthorId(TEST_AUTHOR));
    }
    
    @DisplayName("получить всех авторов из БД")
    @Test
    void shoudGetAllAuthors() {
        val authors = repository.findAll();
        assertThat(authors).isNotNull().hasSize(1)
                .allMatch(a -> a.getFullName() != null);
    }
    
    private String getAuthorId(String fullname) {
        Author a = repository.findByFullName(fullname);
        return a.getId();
    }
}