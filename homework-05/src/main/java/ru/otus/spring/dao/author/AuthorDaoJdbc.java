package ru.otus.spring.dao.author;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.model.Author;

import java.util.Collections;
import java.util.List;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from authors", Integer.class);
    }

    @Override
    public void insert(Author author) {
        jdbc.getJdbcOperations().update("insert into authors (id, fullname) values (?, ?)",
                author.getId(), author.getFullName());
    }

    @Override
    public void update(Author author) {
        jdbc.getJdbcOperations().update("update authors set fullname = ? where id = ?",
                author.getFullName(), author.getId());
    }

    @Override
    public void delete(long id) {
        jdbc.update("delete from authors where id = :id", Collections.singletonMap("id", id));
    }

    @Override
    public Author getById(long id) {
        return jdbc.queryForObject("select * from authors where id = :id",
                Collections.singletonMap("id", id), new AuthorMapper());
    }

    @Override
    public Author getByFullname(String fullname) {
        return jdbc.queryForObject("select * from authors where fullname = :fullname",
                Collections.singletonMap("fullname", fullname), new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select * from authors", new AuthorMapper());
    }
}
