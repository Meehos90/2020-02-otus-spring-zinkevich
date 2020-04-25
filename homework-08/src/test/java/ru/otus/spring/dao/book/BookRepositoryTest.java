package ru.otus.spring.dao.book;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.dao.AbstractRepositoryTest;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.otus.spring.Constants.*;

@DisplayName("Dao для работы с книгами")
class BookRepositoryTest extends AbstractRepositoryTest {
    
    @Autowired
    private BookRepository bookRepository;
    
    @DisplayName("возвращать ожидаемое количество книг")
    @Test
    void shouldReturnExpectedBookCount() {
        long count = bookRepository.count();
        assertThat(count).isEqualTo(1);
    }
    
    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        Book expected = new Book(NEW_BOOK, new Author(TEST_AUTHOR), new Genre(TEST_GENRE));
        bookRepository.save(expected);
        Book actual = bookRepository.findByTitle(NEW_BOOK);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }
    
    @DisplayName("изменить книгу в БД")
    @DirtiesContext
    @Test
    void shouldUpdateBook() {
        Book expected = bookRepository.findByTitle(TEST_BOOK);
        expected.setTitle(EXPECTED_BOOK);
        bookRepository.save(expected);
        Book actual = bookRepository.findByTitle(EXPECTED_BOOK);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }
    
    @DisplayName("удалить книгу из БД")
    @Test
    void shouldDeleteBook() {
        bookRepository.deleteById(getBookId(TEST_BOOK));
        Book book = bookRepository.findById(TEST_BOOK);
        assertNull(book);
    }
    
    @DisplayName("получить книгу из БД по названию")
    @Test
    void shouldGetByTitleBook() {
        Book book = bookRepository.findByTitle(TEST_BOOK);
        assertThat(book.getTitle()).isEqualTo(TEST_BOOK);
    }
    
    @DisplayName("получить книгу из БД по имени автора")
    @Test
    void shouldReturnBookByAuthor() {
        List<Book> books = bookRepository.findByAuthorFullName(TEST_AUTHOR);
        for (Book book : books) {
            assertThat(book.getAuthor().getFullName()).isEqualTo(TEST_AUTHOR);
        }
    }
    
    @DisplayName("получить книгу из БД по названию жанра")
    @Test
    void shouldReturnBookByGenre() {
        List<Book> books = bookRepository.findByGenreName(TEST_GENRE);
        for (Book book : books) {
            assertThat(book.getGenre().getName()).isEqualTo(TEST_GENRE);
        }
    }
    
    @DisplayName("получить все книги из БД")
    @Test
    void shoudGetAllBooks() {
        val books = bookRepository.findAll();
        assertThat(books).isNotNull().hasSize(1)
                .allMatch(b -> b.getTitle() != null);
    }
    
    private String getBookId(String title) {
        Book book = bookRepository.findByTitle(title);
        return book.getId();
    }
}