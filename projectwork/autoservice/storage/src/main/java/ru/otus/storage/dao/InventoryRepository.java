package ru.otus.storage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.storage.model.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsByPartId(Long id);
    Optional<Inventory> findByPlaceIdAndPartId(Long placeId, Long partId);
    Inventory findByPartId(Long id);
    List<Inventory> findAllByPlaceId(Long id);
    List<Inventory> findAllByPartId(Long id);
    List<Inventory> findAll();
}
