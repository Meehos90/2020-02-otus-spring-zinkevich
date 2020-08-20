package ru.otus.storage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.storage.model.YearsRange;

public interface YearsRangeRepository extends JpaRepository<YearsRange, Long> {
}
