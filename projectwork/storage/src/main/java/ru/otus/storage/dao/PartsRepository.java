package ru.otus.storage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.storage.model.Part;

import java.util.Optional;

public interface PartsRepository extends JpaRepository<Part, Long> {
    boolean existsByArticle(String article);
    Optional<Part> findByArticle(String article);
}
