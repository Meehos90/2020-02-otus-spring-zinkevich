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

    @ShellMethod(value = "Create book", key = {"ins", "book insert"})
    public void insertBook() {
        String title = getTitle();
        bookDao.insert(new Book(1L + bookDao.count(), title, getAuthor(), getGenre()));
        messageService.showMessage("Успешно добавлена книга: " + title);
    }

    @ShellMethod(value = "Update book", key = {"upd", "update book"})
    public void updateBook() {
        String title = getTitle();
        Book book = bookDao.getByTitle(title);
        bookDao.update(new Book(book.getId(), title, getAuthor(), getGenre()));
        messageService.showMessage("Книга успешно изменена: " + title);
    }

    @ShellMethod(value = "Delete book by title", key = {"del", "delete book"})
    public void deleteBook() {
        messageService.showMessage("Введите название книги, которую хотите удалить:");
        String title = messageService.getMessage();
        Book book = bookDao.getByTitle(title);
        bookDao.delete(book.getId());
        messageService.showMessage("Книга \"" + title + "\" успешно удалена.");
    }

    @ShellMethod(value = "Search books by name", key = {"sbbn", "search book by name"})
    public Book searchBookByName() {
        messageService.showMessage("Введите название книги:");
        String value = messageService.getMessage();
        return bookDao.getByTitle(value);
    }

    @ShellMethod(value = "Search books by author", key = {"sbba", "search book by author"})
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

    private String getTitle() {
        messageService.showMessage("Введите название книги");
        return messageService.getMessage();
    }

    private String getAuthorFullName() {
        messageService.showMessage("Введите полное имя автора:");
        return messageService.getMessage();
    }

    private String getGenreName() {
        messageService.showMessage("Введите жанр книги:");
        return messageService.getMessage();
    }

    private Genre getGenre() {
        String name = getGenreName();
        List<Genre> genres = genreDao.getAll();
        return genres.stream().filter(g -> g.getName().equals(name)).findFirst()
                .orElse(new Genre(genreDao.count() + 1L, name));
    }

    private Author getAuthor() {
        String fullname = getAuthorFullName();
        List<Author> authors = authorDao.getAll();
        return authors.stream().filter(author -> author.getFullName().equals(fullname)).findFirst()
                .orElse(new Author(authorDao.count() + 1L, fullname));
    }
}