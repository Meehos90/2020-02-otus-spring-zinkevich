package ru.otus.spring.service.book;

import org.springframework.stereotype.Service;
import ru.otus.spring.exception.NoEntityException;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.AbstractService;

import java.util.List;

import static ru.otus.spring.service.Constants.*;

@Service
public class BookServiceImpl extends AbstractService implements BookService {

    @Override
    public void save() {
        String title = getMessage(ENTER_BOOK_TITLE);
        Author author = getAuthor();
        bookRepository.save(new Book(title, author, getGenre()));
        showMessage(BOOK_SAVE + " " + title);
    }

    @Override
    public Book findByTitle() {
        String title = getMessage(ENTER_BOOK_TITLE);
        Book book = bookRepository.findByTitle(title);
        existBook(book);
        return book;
    }

    @Override
    public List<Book> findByAuthor() {
        String fullname = getMessage(ENTER_AUTHOR_FULLNAME);
        List<Book> books = bookRepository.findByAuthorFullName(fullname);
        if ((long) books.size() == 0) {
            throw new NoEntityException(BOOK_NOT_FOUND);
        }
        return books;
    }

    @Override
    public List<Book> findByGenre() {
        String name = getMessage(ENTER_GENRE_NAME);
        List<Book> books = bookRepository.findByGenreName(name);
        if ((long) books.size() == 0) {
            throw new NoEntityException(BOOK_NOT_FOUND);
        }
        return books;
    }

    @Override
    public void update() {
        Book book = findByTitle();
        bookRepository.save(new Book(book.getId(), book.getTitle(), getAuthor(), getGenre()));
        showMessage(BOOK_UPDATE + " " + book.getTitle());
    }

    @Override
    public void delete() {
        Book book = findByTitle();
        bookRepository.deleteById(book.getId());
        showMessage(BOOK_DELETED_SUCCESSFULLY);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    private void existBook(Book book) {
        try {
            bookRepository.existsById(book.getId());
        } catch (NullPointerException ex) {
            throw new NoEntityException(BOOK_NOT_FOUND);
        }
    }

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
        genreRepository.save(new Genre(name));
        return genreRepository.findByName(name);
    }

    private Author insertAndReturnAuthor(String fullname) {
        authorRepository.save(new Author(fullname));
        return authorRepository.findByFullName(fullname);
    }
}
