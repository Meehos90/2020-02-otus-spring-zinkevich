package ru.otus.storage.service;

import ru.otus.storage.model.ChangePlaceOfPart;
import ru.otus.storage.model.Inventory;
import ru.otus.storage.model.InventoryResponse;

import java.util.List;
import java.util.Map;

public interface InventoryService {

    InventoryResponse addPartsToStorage(Map<String, Integer> articles);

    Inventory changePlaceOfPart(ChangePlaceOfPart changePlaceOfPart);

    List<Inventory> findAllInventories();

    List<Inventory> findAllInventoriesWithoutUnloadingZone();

    List<Inventory> findInventoriesInUnloadingZone();

}
