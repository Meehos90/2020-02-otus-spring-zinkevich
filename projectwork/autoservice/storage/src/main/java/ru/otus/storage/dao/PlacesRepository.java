package ru.otus.storage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.storage.model.Place;

import java.util.List;
import java.util.Optional;

public interface PlacesRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByName(String name);
    boolean existsByName(String name);
    List<Place> findAllById(Long id);
}
