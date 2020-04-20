package ru.otus.spring.dao.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findById(long id);

    Genre findByName(String name);
}
