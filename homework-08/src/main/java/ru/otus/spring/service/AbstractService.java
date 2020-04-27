package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.dao.author.AuthorRepository;
import ru.otus.spring.dao.book.BookRepository;
import ru.otus.spring.dao.comment.CommentRepository;
import ru.otus.spring.dao.genre.GenreRepository;
import ru.otus.spring.exception.NoEntityException;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.message.IOService;

import static ru.otus.spring.service.Constants.*;

public class AbstractService {
    @Autowired
    public BookRepository bookRepository;
    @Autowired
    public AuthorRepository authorRepository;
    @Autowired
    public GenreRepository genreRepository;
    @Autowired
    public CommentRepository commentRepository;
    @Autowired
    public IOService messageService;

    public String getMessage(String message) {
        messageService.showMessage(message);
        return messageService.getMessage().toLowerCase();
    }

    public void showMessage(String message) {
        messageService.showMessage(message);
    }

    public Genre getGenre() {
        String name = getMessage(ENTER_GENRE_NAME);
        Genre genre = genreRepository.findByName(name);
        if (genre == null) {
            return insertAndReturnGenre(name);
        }
        return genre;
    }

    public Author getAuthor() {
        String fullname = getMessage(ENTER_AUTHOR_FULLNAME);
        Author author = authorRepository.findByFullName(fullname);
        if (author == null) {
            return insertAndReturnAuthor(fullname);
        }
        return author;
    }

    public Book getBook() {
        String title = getMessage(ENTER_BOOK_TITLE);
        Book book = bookRepository.findByTitle(title);
        if(book == null) {
            throw new NoEntityException(BOOK_NOT_FOUND);
        }
        return book;
    }

    private Genre insertAndReturnGenre(String name) {
        genreRepository.save(new Genre( name));
        return genreRepository.findByName(name);
    }

    private Author insertAndReturnAuthor(String fullname) {
        authorRepository.save(new Author( fullname));
        return authorRepository.findByFullName(fullname);
    }
}
