package ru.otus.spring.dao.author;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.model.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(AuthorDaoJpa.class)
class AuthorDaoJpaTest {

    @Autowired
    private AuthorDaoJpa dao;

    @DisplayName("возвращать ожидаемое количество авторов")
    @Test
    void shoudReturnExpectedAuthorCount() {
        long count = dao.count();
        assertThat(count).isEqualTo(3L);
    }

    @Test
    void saveTest() {
        Author author = new Author(4L,"Stiven");
        dao.save(author);
    }
}