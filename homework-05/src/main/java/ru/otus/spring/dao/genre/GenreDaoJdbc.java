package ru.otus.spring.dao.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.model.Genre;

import java.util.Collections;
import java.util.List;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from genres", Integer.class);
    }

    @Override
    public void insert(Genre genre) {
        jdbc.getJdbcOperations().update("insert into genres (id, name) values (?, ?)",
                genre.getId(), genre.getName());
    }

    @Override
    public void update(Genre genre) {
        jdbc.getJdbcOperations().update("update genres set name = ? where id = ?", genre.getName(), genre.getId());
    }

    @Override
    public void delete(long id) {
        jdbc.update("delete from genres where id = :id", Collections.singletonMap("id", id));
    }

    @Override
    public Genre getById(long id) {
        return jdbc.queryForObject("select * from genres where id = :id",
                Collections.singletonMap("id", id), new GenreMapper());
    }

    @Override
    public Genre getByName(String name) {
        return jdbc.queryForObject("select * from genres where name = :name",
                Collections.singletonMap("name", name), new GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select * from genres", new GenreMapper());
    }
}
