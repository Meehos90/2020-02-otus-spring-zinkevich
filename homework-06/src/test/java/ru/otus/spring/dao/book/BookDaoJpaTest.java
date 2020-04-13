package ru.otus.spring.dao.book;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.author.AuthorDaoJpa;
import ru.otus.spring.dao.genre.GenreDaoJpa;
import ru.otus.spring.exception.NoEntityException;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.otus.spring.dao.Constants.Authors.*;
import static ru.otus.spring.dao.Constants.Books.*;
import static ru.otus.spring.dao.Constants.Books.TEST_AUTHOR_FULLNAME;
import static ru.otus.spring.dao.Constants.EXPECTED_QERIES_COUNT;

@DisplayName("Dao для работы с книгами")
@DataJpaTest
@Import({BookDaoJpa.class, AuthorDaoJpa.class, GenreDaoJpa.class})
class BookDaoJpaTest {

    @Autowired
    private BookDaoJpa bookDao;
    @Autowired
    private AuthorDaoJpa authorDao;
    @Autowired
    private GenreDaoJpa genreDao;
    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемое количество книг")
    @Test
    void shouldReturnExpectedBookCount() {
        long count = bookDao.count();
        assertThat(count).isEqualTo(DEFAULT_BOOKS_COUNT);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        Book expected = getBook(0);
        bookDao.save(expected);
        Book actual = bookDao.findByTitle(TEST_BOOK_TITLE);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("изменить книгу в БД")
    @Test
    void shouldUpdateBook() {
        Book expected = getBook(UPDATE_TEST_BOOK);
        bookDao.updateBookById(expected);
        Book actual = bookDao.findByTitle(TEST_BOOK_TITLE);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("удалить книгу из БД")
    @Test
    void shouldDeleteBook() {
        bookDao.deleteById(TEST_BOOK_ID);
        Throwable thrown = assertThrows(NoEntityException.class, () -> {
            bookDao.findById(TEST_BOOK_ID);
        });
        assertNotNull(thrown.getMessage());
    }

    @DisplayName("получить книгу из БД по названию")
    @Test
    void shouldGetByTitleBook() {
        Book book = bookDao.findByTitle(EXPECTED_TEST_BOOK_TITLE);
        assertThat(book.getTitle()).isEqualTo(EXPECTED_TEST_BOOK_TITLE);
    }

    @DisplayName("получить книгу из БД по имени автора")
    @Test
    void shouldReturnBookByAuthor() {
        List<Book> books = bookDao.findByAuthor(TEST_AUTHOR_FULLNAME);
        for(Book book : books) {
            assertThat(book.getAuthor().getFullName()).isEqualTo(TEST_AUTHOR_FULLNAME);
        }
    }

    @DisplayName("получить книгу из БД по названию жанра")
    @Test
    void shouldReturnBookByGenre() {
        List<Book> books = bookDao.findByGenre(TEST_GENRE_NAME);
        for(Book book : books) {
            assertThat(book.getGenre().getName()).isEqualTo(TEST_GENRE_NAME);
        }
    }

    @DisplayName("получить все книги из БД")
    @Test
    void shoudGetAllBooks() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        val books = bookDao.findAll();
        assertThat(books).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
                .allMatch(b -> b.getTitle() != null)
                .allMatch(b -> b.getAuthor() != null)
                .allMatch(b -> b.getGenre() != null);
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QERIES_COUNT);
    }

    private Book getBook(long id) {
        Author author = authorDao.findById(TEST_AUTHOR_ID);
        Genre genre = genreDao.findById(TEST_GENRE_ID);
        return new Book(id, TEST_BOOK_TITLE, author, genre);
    }
}