package ru.otus.spring.dao.book;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.spring.dao.author.AuthorRepository;
import ru.otus.spring.dao.genre.GenreRepository;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.otus.spring.dao.Constants.Authors.TEST_AUTHOR_ID;
import static ru.otus.spring.dao.Constants.Books.*;


@DisplayName("Dao для работы с книгами")
@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;

    @DisplayName("возвращать ожидаемое количество книг")
    @Test
    void shouldReturnExpectedBookCount() {
        long count = bookRepository.count();
        assertThat(count).isEqualTo(DEFAULT_BOOKS_COUNT);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        Book expected = getBook(0);
        bookRepository.save(expected);
        Book actual = bookRepository.findByTitle(TEST_BOOK_TITLE);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("изменить книгу в БД")
    @Test
    void shouldUpdateBook() {
        Book expected = getBook(UPDATE_TEST_BOOK);
        bookRepository.save(expected);
        Book actual = bookRepository.findByTitle(TEST_BOOK_TITLE);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("удалить книгу из БД")
    @Test
    void shouldDeleteBook() {
        bookRepository.deleteById(TEST_BOOK_ID);
        Book book = bookRepository.findById(TEST_BOOK_ID);
        assertNull(book);
    }

    @DisplayName("получить книгу из БД по названию")
    @Test
    void shouldGetByTitleBook() {
        Book book = bookRepository.findByTitle(EXPECTED_TEST_BOOK_TITLE);
        assertThat(book.getTitle()).isEqualTo(EXPECTED_TEST_BOOK_TITLE);
    }

    @DisplayName("получить книгу из БД по имени автора")
    @Test
    void shouldReturnBookByAuthor() {
        List<Book> books = bookRepository.findByAuthorFullName(TEST_AUTHOR_FULLNAME);
        for (Book book : books) {
            assertThat(book.getAuthor().getFullName()).isEqualTo(TEST_AUTHOR_FULLNAME);
        }
    }

    @DisplayName("получить книгу из БД по названию жанра")
    @Test
    void shouldReturnBookByGenre() {
        List<Book> books = bookRepository.findByGenreName(TEST_GENRE_NAME);
        for (Book book : books) {
            assertThat(book.getGenre().getName()).isEqualTo(TEST_GENRE_NAME);
        }
    }

    @DisplayName("получить все книги из БД")
    @Test
    void shoudGetAllBooks() {
        val books = bookRepository.findAll();
        assertThat(books).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
                .allMatch(b -> b.getTitle() != null)
                .allMatch(b -> b.getAuthor() != null)
                .allMatch(b -> b.getGenre() != null);
    }

    private Book getBook(long id) {
        Author author = authorRepository.findById(TEST_AUTHOR_ID);
        Genre genre = genreRepository.findById(TEST_GENRE_ID);
        return new Book(id, TEST_BOOK_TITLE, author, genre);
    }
}