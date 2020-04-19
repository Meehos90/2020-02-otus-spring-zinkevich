package ru.otus.spring.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.model.Book;
import ru.otus.spring.service.book.BookService;

import java.util.List;

@ShellComponent
public class BookCommands {
    @Autowired
    private BookService bookService;

    /*
    create book - crb
    search book by title - sbt
    search book by author - sba
    search book by genre - sbg
    update book - upb
    delete book - deb
    all books - library
    */
    @ShellMethod(value = "Create book", key = {"crb", "book create"})
    private void saveBook() {
        bookService.save();
    }

    @ShellMethod(value = "Search books by title", key = {"sbt", "search book by title"})
    private Book searchBookByTitle() {
        return bookService.findByTitle();
    }

    @ShellMethod(value = "Search books by author", key = {"sba", "search book by author"})
    private List<Book> searchBookByAuthor() {
        return bookService.findByAuthor();
    }

    @ShellMethod(value = "Search books by genre", key = {"sbg", "search book by genre"})
    private List<Book> searchBookByGenre() {
        return bookService.findByGenre();
    }

    @ShellMethod(value = "Update book", key = {"upb", "update book"})
    private void updateBook() {
        bookService.update();
    }

    @ShellMethod(value = "Delete book by title", key = {"deb", "delete book"})
    private void deleteBook() {
        bookService.delete();
    }

    @ShellMethod(value = "View all books", key = {"lib", "library"})
    private List<Book> getAllBooks() {
        return bookService.findAll();
    }
}
