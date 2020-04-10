package ru.otus.spring.dao.book;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с книгами")
@DataJpaTest
@Import(BookDaoJpa.class)
class BookDaoJpaTest {
    private static final long DEFAULT_BOOKS_COUNT = 6L;

    @Autowired
    private BookDaoJpa dao;

    @DisplayName("возвращать ожидаемое количество книг")
    @Test
    void shoudReturnExpectedBookCount() {
        long count = dao.count();
        assertThat(count).isEqualTo(DEFAULT_BOOKS_COUNT);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shoudInsertBook() {
        Book expected = getBook(7L);
        dao.save(expected);
        Book actual = dao.findByTitle("TEST_BOOK_TITLE");
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("изменить книгу в БД")
    @Test
    void shouldUpdateBook() {
        Book expected = getBook(3L);
        dao.updateBookById(expected);
        Book actual = dao.findByTitle("TEST_BOOK_TITLE");
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    private Book getBook(long id) {
        Author author = new Author(2, "Джон Толкин");
        Genre genre = new Genre(2, "Фэнтези");
        return new Book(id, "TEST_BOOK_TITLE", author, genre);
    }

    @DisplayName("удалить книгу из БД")
    @Test
    void shoudDeleteBook() {
        dao.deleteById(6L);
        long count = dao.count();
        assertThat(count).isEqualTo(5L);
    }

    @DisplayName("получить книгу из БД по названию")
    @Test
    void shouldGetByTitleBook() {
        Book book = dao.findByTitle("Зов Ктулху");
        assertThat(book.getTitle()).isEqualTo("Зов Ктулху");
    }

    @DisplayName("получить книгу из БД по имени автора")
    @Test
    void getByAuthor() {
        List<Book> books = dao.findByAuthor("Говард Лавкрафт");
        for(Book book : books) {
            assertThat(book.getAuthor().getFullName()).isEqualTo("Говард Лавкрафт");
        }
    }

    @DisplayName("получить книгу из БД по названию жанра")
    @Test
    void getByGenre() {
        List<Book> books = dao.findByGenre("Ужасы");
        for(Book book : books) {
            assertThat(book.getGenre().getName()).isEqualTo("Ужасы");
        }
    }

    @DisplayName("получить все книги из БД")
    @Test
    void shoudGetAllBooks() {
        List<Book> books = dao.getAll();
        assertThat(books.size()).isEqualTo(DEFAULT_BOOKS_COUNT);
    }
}