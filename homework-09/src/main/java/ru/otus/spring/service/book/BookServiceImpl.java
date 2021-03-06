package ru.otus.spring.service.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.otus.spring.exception.NoEntityException;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.AbstractService;

import static ru.otus.spring.service.Constants.BOOK_NOT_FOUND;


@Service
public class BookServiceImpl extends AbstractService implements BookService {

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new NoEntityException(BOOK_NOT_FOUND));
    }

    @Override
    public boolean existsByTitle(String title) {
        return bookRepository.existsByTitle(title);
    }


    @Override
    public Book edit(Long id, String title, String author, String genre) {
        if (existsById(id)) {
            Book book = findById(id);
            return bookRepository.save(new Book(book.getId(), title, getAuthor(author), getGenre(genre)));
        } else {
            return bookRepository.save(new Book(0, title, getAuthor(author), getGenre(genre)));
        }
    }

    @Override
    public boolean existsById(Long id) {
        if (id != null) {
            return bookRepository.existsById(id);
        }
        return false;
    }

    @Override
    public void deleteById(Long id) {
        Book book = findById(id);
        bookRepository.deleteById(book.getId());
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }


    public Genre getGenre(String name) {
        Genre genre = genreRepository.findByName(name);
        if (genre == null) {
            return insertAndReturnGenre(name);
        }
        return genre;
    }

    public Author getAuthor(String fullName) {
        Author author = authorRepository.findByFullName(fullName);
        if (author == null) {
            return insertAndReturnAuthor(fullName);
        }
        return author;
    }

    private Genre insertAndReturnGenre(String name) {
        genreRepository.save(new Genre(0, name));
        return genreRepository.findByName(name);
    }

    private Author insertAndReturnAuthor(String fullName) {
        authorRepository.save(new Author(0, fullName));
        return authorRepository.findByFullName(fullName);
    }
}
