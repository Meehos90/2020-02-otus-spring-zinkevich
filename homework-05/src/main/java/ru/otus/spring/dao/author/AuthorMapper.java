package ru.otus.spring.dao.author;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.spring.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String fullname = resultSet.getString("fullname");
        return new Author(id, fullname);
    }
}
