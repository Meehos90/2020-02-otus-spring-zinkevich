package ru.otus.storage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.storage.model.Model;

public interface ModelRepository extends JpaRepository<Model, Long> {
}