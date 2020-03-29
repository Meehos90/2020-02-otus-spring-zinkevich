package ru.otus.spring.dao.book;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.spring.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        String genre = resultSet.getString("genre");
        return new Book(id, title, author, genre);
    }
}
