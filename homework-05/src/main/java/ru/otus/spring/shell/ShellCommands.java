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
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final IOService messageService;

    @ShellMethod(value = "Search books by name", key = {"sbn", "book name"})
    public Book searchBookByName() {
        messageService.showMessage("Введите название книги:");
        String value = messageService.getMessage();
        return bookDao.getByTitle(value);
    }

    @ShellMethod(value = "Search books by author", key = {"sba", "book author"})
    public Book searchBookByAuthor() {
        messageService.showMessage("Введите полное имя автора книги:");
        String value = messageService.getMessage();
        return bookDao.getByAuthor(value);
    }

    @ShellMethod(value = "View all books", key = {"library"})
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    @ShellMethod(value = "Search genres by name", key = {"sgn", "genre name"})
    public Genre getGenreByName() {
        messageService.showMessage("Введите название жанра");
        String value = messageService.getMessage();
        return genreDao.getByName(value);
    }

    @ShellMethod(value = "View all genres", key = {"genres"})
    public List<Genre> getAllGenres() {
        return genreDao.getAll();
    }

    @ShellMethod(value = "Search authors by fullname", key = {"saf", "author fullname"})
    public Author getAuthorByName() {
        messageService.showMessage("Введите полное имя автора");
        String value = messageService.getMessage();
        return authorDao.getByFullname(value);
    }

    @ShellMethod(value = "View all authors", key = {"authors"})
    public List<Author> getAllAuthors() {
        return authorDao.getAll();
    }
}
