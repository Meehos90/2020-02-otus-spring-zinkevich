package ru.otus.storage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.storage.model.Mark;

public interface MarkRepository extends JpaRepository<Mark, Long> {
}
