package ru.otus.spring.dao.book;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.model.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.spring.dao.Constants.*;

@DisplayName("Dao для работы с книгами")
@JdbcTest
@AutoConfigureTestDatabase
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {
    private static final int DEFAULT_BOOKS_COUNT = 3;

    @Autowired
    private BookDaoJdbc dao;

    @DisplayName("возвращать ожидаемое количество книг")
    @Test
    void shoudReturnExpectedBookCount() {
        int count = dao.count();
        assertThat(count).isEqualTo(DEFAULT_BOOKS_COUNT);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shoudInsertBook() {
        Book expected = new Book(4L, TEST_BOOK_TITLE, TEST_AUTHOR_FULLNAME, TEST_GENRE_NAME);
        dao.insert(expected);
        Book actual = dao.getByTitle(expected.getTitle());
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("удалить книгу из БД")
    @Test
    void shoudDeleteBook() {
        dao.delete(TEST_ID);
        int count = dao.count();
        assertThat(count).isEqualTo(EXPECTED_COUNT);
    }

    @DisplayName("получить книгу из БД по названию")
    @Test
    void shouldGetByTitleBook() {
        Book book = dao.getByTitle(EXPECTED_BOOK_TITLE);
        assertThat(book.getTitle()).isEqualTo(EXPECTED_BOOK_TITLE);
    }

    @DisplayName("получить книгу из БД по имени автора")
    @Test
    void getByAuthor() {
        Book book = dao.getByAuthor(TEST_AUTHOR_FULLNAME);
        assertThat(book.getAuthor()).isEqualTo(TEST_AUTHOR_FULLNAME);
    }

    @DisplayName("получить все книги из БД")
    @Test
    void ShoudGetAllBooks() {
        List<Book> books = dao.getAll();
        assertThat(books.size()).isEqualTo(DEFAULT_COUNT);
    }
}