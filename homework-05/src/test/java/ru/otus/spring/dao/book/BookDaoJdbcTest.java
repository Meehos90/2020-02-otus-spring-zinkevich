package ru.otus.spring.dao.book;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
        Book expected = getBook(0);
        dao.insert(expected);
        Book actual = dao.getByTitle(TEST_BOOK_TITLE);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("изменить книгу в БД")
    @Test
    void shouldUpdateBook() {
        Book expected = getBook(TEST_ID);
        dao.update(expected);
        Book actual = dao.getByTitle(TEST_BOOK_TITLE);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    private Book getBook(long id) {
        Author author = new Author(EXPECTED_ID, EXPECTED_AUTHOR_FULLNAME);
        Genre genre = new Genre(EXPECTED_ID, EXPECTED_GENRE_NAME);
        return new Book(id, TEST_BOOK_TITLE, author, genre);
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
        List<Book> books = dao.getByAuthor(EXPECTED_AUTHOR_FULLNAME);
        for(Book book : books) {
            assertThat(book.getAuthor().getFullName()).isEqualTo(EXPECTED_AUTHOR_FULLNAME);
        }
    }

    @DisplayName("получить книгу из БД по названию жанра")
    @Test
    void getByGenre() {
        List<Book> books = dao.getByGenre(EXPECTED_GENRE_NAME);
        for(Book book : books) {
            assertThat(book.getGenre().getName()).isEqualTo(EXPECTED_GENRE_NAME);
        }
    }

    @DisplayName("получить все книги из БД")
    @Test
    void shoudGetAllBooks() {
        List<Book> books = dao.getAll();
        assertThat(books.size()).isEqualTo(DEFAULT_COUNT);
    }
}