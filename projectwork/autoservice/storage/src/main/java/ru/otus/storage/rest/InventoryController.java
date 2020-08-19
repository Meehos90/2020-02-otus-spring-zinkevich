package ru.otus.storage.rest;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.storage.model.ChangePlaceOfPart;
import ru.otus.storage.model.Inventory;
import ru.otus.storage.model.InventoryResponse;
import ru.otus.storage.service.InventoryService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Api(tags = "inventory", protocols = "HTTP")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/inventory/all-inventories")
    public List<Inventory> findAllInventories() {
        return inventoryService.findAllInventories();
    }

    @PostMapping("/inventory/actions/add-parts")
    public InventoryResponse addPartsToStorage(@Valid @RequestBody Map<String, Integer> articles) {
        return inventoryService.addPartsToStorage(articles);
    }

    @PostMapping("/inventory/actions/change-place")
    public Inventory changePlaceOfPart(@Valid @RequestBody ChangePlaceOfPart changePlaceOfPart) {
        return inventoryService.changePlaceOfPart(changePlaceOfPart);
    }

    @PostMapping("/inventory/action/check-inventories-on-storage")
    public String checkInventoriesOnStorage(@Valid @RequestBody Map<String, Integer> partsAndCount) {
        return inventoryService.checkInventoriesOnStorage(partsAndCount);
    }

    @PostMapping("/inventory/action/set-inventories-to-order")
    public String setInventoriesToOrder(@Valid @RequestBody Map<String, Integer> partsAndCount) {
        return inventoryService.setInventoriesToOrder(partsAndCount);
    }

}
