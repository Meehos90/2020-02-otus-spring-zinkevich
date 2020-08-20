package ru.otus.storage.service;

import ru.otus.storage.model.Inventory;
import ru.otus.storage.model.InventoryResponse;

import java.util.List;
import java.util.Map;

public interface InventoryService {

    InventoryResponse addPartsToStorage(Map<String, Integer> partsAndCount);

    Inventory changePlaceOfPart(Long currentPlaceId, Long partId, Long newPlaceId, Integer count);

    List<Inventory> findAllInventories();

    String checkInventoriesOnStorage(Map<String, Integer> partsAndCount);

    String setInventoriesToOrder(Map<String, Integer> partsAndCount);

}
