package ru.otus.spring.dao.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    long count();
    Book save(Book book);
    void deleteById(long id);
    Book findById(long id);
    Book findByTitle(String title);
    @Query("select b from Book b where b.author.fullName = :fullname")
    List<Book> findByAuthor(@Param("fullname") String fullname);
    @Query("select b from Book b where b.genre.name = :name")
    List<Book> findByGenre(@Param("name")String name);
    List<Book> findAll();
    boolean existsById(long id);

}
