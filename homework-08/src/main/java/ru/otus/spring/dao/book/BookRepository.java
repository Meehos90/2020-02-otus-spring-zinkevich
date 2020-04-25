package ru.otus.spring.dao.book;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.model.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, Long> {
    
    long count();
    
    Book save(Book book);
    
    void deleteById(String id);
    
    Book findById(String id);
    
    Book findByTitle(String title);
    
    List<Book> findByAuthorFullName(String fullname);
    
    List<Book> findByAuthorId(String id);
    
    List<Book> findByGenreName(String name);
    
    List<Book> findByGenreId(String id);
    
    List<Book> findAll();
    
    boolean existsById(String id);
    
    void removeBooksByAuthorId(String id);
    
    void removeBooksByGenreId(String id);
    
}
