package ru.otus.spring.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.author.AuthorRepository;
import ru.otus.spring.dao.book.BookRepository;
import ru.otus.spring.dao.genre.GenreRepository;
import ru.otus.spring.exception.EntityNotFoundException;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public boolean existsByTitle(String title) {
        return bookRepository.existsByTitle(title);
    }


    @Override
    public Book save(Book book) {
        Author author = getAuthor(book.getAuthor().getFullName());
        Genre genre = getGenre(book.getGenre().getName());
        return bookRepository.save(new Book(0, book.getTitle(), author, genre));
    }

    @Override
    public Book update(Book bookDetails) {
        Book book = findById(bookDetails.getId());
        book.setId(bookDetails.getId());
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(getAuthor(bookDetails.getAuthor().getFullName()));
        book.setGenre(getGenre(bookDetails.getGenre().getName()));
        return bookRepository.save(book);
    }

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now().plusHours(2).withNano(0));
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
    public List<Book> findAll() {
        return bookRepository.findAll();
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
