package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.dao.author.AuthorDao;
import ru.otus.spring.dao.book.BookDao;
import ru.otus.spring.dao.genre.GenreDao;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.IOService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {
    public static final String ENTER_BOOK_TITLE = "Введите название книги:";
    public static final String ENTER_GENRE_NAME = "Введите название жанра:";
    public static final String ENTER_AUTHOR_FULLNAME = "Введите полное имя автора:";
    public static final String ENTER_DELETE_BOOK_TITLE = "Введите название книги, которую хотите удалить:";
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final IOService messageService;

    @ShellMethod(value = "Create book", key = {"ins", "book insert"})
    public void insertBook() {
        String title = getMessage(ENTER_BOOK_TITLE);
        bookDao.insert(new Book(0, title, getAuthor(), getGenre()));
        messageService.showMessage("Успешно добавлена книга: " + title);
    }

    @ShellMethod(value = "Update book", key = {"upd", "update book"})
    public void updateBook() {
        String title = getMessage(ENTER_BOOK_TITLE);
        Book book = bookDao.getByTitle(title);
        bookDao.update(new Book(book.getId(), title, getAuthor(), getGenre()));
        messageService.showMessage("Книга успешно изменена: " + title);
    }

    @ShellMethod(value = "Delete book by title", key = {"del", "delete book"})
    public void deleteBook() {
        messageService.showMessage(ENTER_DELETE_BOOK_TITLE);
        String title = messageService.getMessage();
        Book book = bookDao.getByTitle(title);
        bookDao.delete(book.getId());
        messageService.showMessage("Книга \"" + title + "\" успешно удалена.");
    }

    @ShellMethod(value = "Search books by name", key = {"sbn", "search book by name"})
    public Book searchBookByName() {
        messageService.showMessage(ENTER_BOOK_TITLE);
        String value = messageService.getMessage();
        return bookDao.getByTitle(value);
    }

    @ShellMethod(value = "Search books by author", key = {"sba", "search book by author"})
    public List<Book> searchBookByAuthor() {
        messageService.showMessage(ENTER_AUTHOR_FULLNAME);
        String value = messageService.getMessage();
        return bookDao.getByAuthor(value);
    }

    @ShellMethod(value = "Search books by genre", key = {"sbg", "search book by genre"})
    public List<Book> searchBookByGenre() {
        messageService.showMessage(ENTER_GENRE_NAME);
        String value = messageService.getMessage();
        return bookDao.getByGenre(value);
    }

    @ShellMethod(value = "View all books", key = {"lib", "library"})
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    @ShellMethod(value = "Search genres by name", key = {"sgn", "genre name"})
    public Genre getGenreByName() {
        messageService.showMessage(ENTER_GENRE_NAME);
        String value = messageService.getMessage();
        return genreDao.getByName(value);
    }

    @ShellMethod(value = "Delete genre", key = {"dg", "delete genre", "del genre"})
    private void deleteGenre() {
        messageService.showMessage(ENTER_GENRE_NAME);
        String value = messageService.getMessage();
        Genre genre = genreDao.getByName(value);
        genreDao.delete(genre.getId());
        messageService.showMessage("Жанр \"" + value + "\" успешно удален вместе с книгами.");
    }

    @ShellMethod(value = "View all genres", key = {"genres"})
    public List<Genre> getAllGenres() {
        return genreDao.getAll();
    }

    @ShellMethod(value = "Search authors by fullname", key = {"saf", "author fullname"})
    public Author getAuthorByName() {
        messageService.showMessage(ENTER_AUTHOR_FULLNAME);
        String value = messageService.getMessage();
        return authorDao.getByFullname(value);
    }

    @ShellMethod(value = "View all authors", key = {"authors"})
    public List<Author> getAllAuthors() {
        return authorDao.getAll();
    }

    @ShellMethod(value = "Delete author", key = {"da", "delete author", "del auth"})
    private void deleteAuthor() {
        messageService.showMessage(ENTER_AUTHOR_FULLNAME);
        String value = messageService.getMessage();
        Author author = authorDao.getByFullname(value);
        authorDao.delete(author.getId());
        messageService.showMessage("Автор \"" + value + "\" успешно удален вместе с его книгами.");
    }

    private String getMessage(String message) {
        messageService.showMessage(message);
        return messageService.getMessage();
    }

    private Genre getGenre() {
        String name = getMessage(ENTER_GENRE_NAME);
        Genre genre;
        try {
            genre = genreDao.getByName(name);
        } catch (EmptyResultDataAccessException e) {
            return insertAndReturnGenre(name);
        }
        return genre;
    }

    private Author getAuthor() {
        String fullname = getMessage(ENTER_AUTHOR_FULLNAME);
        Author author;
        try {
            author = authorDao.getByFullname(fullname);
        } catch (EmptyResultDataAccessException e) {
            return insertAndReturnAuthor(fullname);
        }
        return author;
    }

    private Genre insertAndReturnGenre(String name) {
        genreDao.insert(new Genre(0, name));
        return genreDao.getByName(name);
    }

    private Author insertAndReturnAuthor(String fullname) {
        authorDao.insert(new Author(0, fullname));
        return authorDao.getByFullname(fullname);
    }
}
