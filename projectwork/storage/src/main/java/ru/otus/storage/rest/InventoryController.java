package ru.otus.storage.rest;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.storage.model.ChangePlaceOfPart;
import ru.otus.storage.model.Inventory;
import ru.otus.storage.model.InventoryResponse;
import ru.otus.storage.service.InventoryService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Api(tags = "storage", protocols = "HTTP")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/inventory/all-inventories")
    public List<Inventory> findAllInventories() {
        return inventoryService.findAllInventories();
    }

    @PostMapping("/iventory/actions/add-parts")
    public InventoryResponse addPartsToStorage(@Valid @RequestBody Map<String, Integer> articles) {
        return inventoryService.addPartsToStorage(articles);
    }

    @PostMapping("/iventory/actions/change-place")
    public Inventory changePlaceOfPart(@Valid @RequestBody ChangePlaceOfPart changePlaceOfPart) {
        return inventoryService.changePlaceOfPart(changePlaceOfPart);
    }
}
