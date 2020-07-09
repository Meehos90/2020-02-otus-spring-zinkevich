package ru.otus.spring.dao.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.otus.spring.model.Genre;

@RepositoryRestResource(path = "genres")
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findById(long id);

    Genre findByName(String name);

    boolean existsByName(String name);
}
