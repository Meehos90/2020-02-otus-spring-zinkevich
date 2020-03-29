package ru.otus.spring.dao.book;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.model.Book;

import java.util.Collections;
import java.util.List;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from books", Integer.class);
    }

    @Override
    public void insert(Book book) {
        jdbc.getJdbcOperations().update("insert into books (id, title, author, genre) values (?, ?, ?, ?)",
                book.getId(), book.getTitle(), book.getAuthor(), book.getGenre());
    }

    @Override
    public void delete(long id) {
        jdbc.update("delete from books where id = :id", Collections.singletonMap("id", id));
    }

    @Override
    public Book getByTitle(String title) {
        return jdbc.queryForObject("select * from books where title = :title",
                Collections.singletonMap("title", title), new BookMapper());
    }

    @Override
    public Book getByAuthor(String author) {
        return jdbc.queryForObject("select * from books where author = :author",
                Collections.singletonMap("author", author), new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select * from books", new BookMapper());
    }
}
