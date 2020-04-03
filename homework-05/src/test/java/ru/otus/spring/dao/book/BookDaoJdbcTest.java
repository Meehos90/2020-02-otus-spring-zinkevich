package ru.otus.spring.dao.book;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.spring.dao.Constants.*;

@DisplayName("Dao для работы с книгами")
@JdbcTest
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
        Author author = new Author(4L, "Ствен Кинг");
        Genre genre = new Genre(4L, "Ужасы");
        Book expected = new Book(4L, "Оно", author, genre);
        dao.insert(expected);
        Book actual = dao.getByTitle("Оно");
        System.out.println(actual);
        //assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("изменить книгу в БД")
    @Test
    void shouldUpdateBook() {
        Author author = new Author(TEST_ID, TEST_AUTHOR_FULLNAME);
        Genre genre = new Genre(TEST_ID, TEST_GENRE_NAME);
        Book expected = new Book(TEST_ID, TEST_BOOK_TITLE, author, genre);
        dao.update(expected);
        Book actual = dao.getByTitle(TEST_BOOK_TITLE);
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