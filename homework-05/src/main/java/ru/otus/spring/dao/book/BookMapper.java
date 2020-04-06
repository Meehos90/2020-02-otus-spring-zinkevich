package ru.otus.spring.dao.book;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String title = resultSet.getString("title");
        long authorId = resultSet.getLong("author_id");
        String authorFullname = resultSet.getString("fullname");
        long genreId = resultSet.getLong("genre_id");
        String genreName = resultSet.getString("name");

        Author author = new Author(authorId, authorFullname);
        Genre genre = new Genre(genreId, genreName);

        return new Book(id, title, author, genre);
    }
}
