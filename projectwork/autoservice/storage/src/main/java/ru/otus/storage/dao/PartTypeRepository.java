package ru.otus.storage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.storage.model.PartType;

public interface PartTypeRepository extends JpaRepository<PartType, Long> {
}
