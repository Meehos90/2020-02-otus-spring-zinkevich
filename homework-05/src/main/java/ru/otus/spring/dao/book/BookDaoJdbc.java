package ru.otus.spring.dao.book;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.model.Book;

import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations jdbc;
    private final static String SELECT_BOOKS = "select * from books b join authors a on b.author_id = a.id join genres g on b.genre_id = g.id";

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from books", Integer.class);
    }

    @Override
    public void insert(Book book) {
        jdbc.update("insert into books values (:id, :title, :author_id, :genre_id)",
                Map.of("id", book.getId(), "title", book.getTitle(), "author_id", book.getAuthor().getId(), "genre_id", book.getGenre().getId()));
    }

    @Override
    public void update(Book book) {
        jdbc.update("update books b join authors a on b.author_id = a.id on genres g b.genre_id = g.id set title = :title, author = :author, genre = :genre where id = :id",
                Map.of("title", book.getTitle(), "author", book.getAuthor(), "genre", book.getGenre(), "id", book.getId()));
    }

    @Override
    public void delete(long id) {
        jdbc.update("delete from books where id = :id", Map.of("id", id));
    }

    @Override
    public Book getByTitle(String title) {
        return jdbc.queryForObject(SELECT_BOOKS + " where title = :title",
                Map.of("title", title), new BookMapper());
    }

    @Override
    public Book getByAuthor(String fullname) {
        return jdbc.queryForObject(SELECT_BOOKS + " where fullname = :fullname",
                Map.of("fullname", fullname), new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query(SELECT_BOOKS,
                new BookMapper());
    }
}
