package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
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
        bookDao.insert(new Book(1L + bookDao.count(), title, getAuthor(), getGenre()));
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
    public Book searchBookByAuthor() {
        messageService.showMessage(ENTER_AUTHOR_FULLNAME);
        String value = messageService.getMessage();
        return bookDao.getByAuthor(value);
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

    private String getMessage(String message) {
        messageService.showMessage(message);
        return messageService.getMessage();
    }

    private Genre getGenre() {
        String name = getMessage(ENTER_GENRE_NAME);
        List<Genre> genres = genreDao.getAll();
        return genres.stream().filter(g -> g.getName().equals(name)).findFirst()
                .orElse(insertAndReturnGenre(name));
    }

    private Author getAuthor() {
        String fullname = getMessage(ENTER_AUTHOR_FULLNAME);
        List<Author> authors = authorDao.getAll();
        return authors.stream().filter(author -> author.getFullName().equals(fullname)).findFirst()
                .orElse(insertAndReturnAuthor(fullname));
    }

    private Genre insertAndReturnGenre(String name) {
        genreDao.insert(new Genre(genreDao.count() + 1L, name));
        return genreDao.getByName(name);
    }

    private Author insertAndReturnAuthor(String fullname) {
        authorDao.insert(new Author(authorDao.count() + 1L, fullname));
        return authorDao.getByFullname(fullname);
    }
}
