package ru.otus.storage.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.storage.dao.InventoryRepository;
import ru.otus.storage.exception.EntityNotFoundException;
import ru.otus.storage.model.*;
import ru.otus.storage.service.InventoryService;
import ru.otus.storage.service.PartsService;
import ru.otus.storage.service.PlacesService;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.otus.storage.service.impl.PlacesServiceImpl.UNLOADIG_ZONE;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final StoragUtil storagUtil;
    private final PartsService partsService;
    private final PlacesService placesService;
    private final InventoryRepository inventoryRepository;

    static List<String> articlesList = new ArrayList<>();
    static Map<String, String> errorArticlesMap = new HashMap<>();

    @Override
    public InventoryResponse addPartsToStorage(Map<String, Integer> articles) {
        articles.forEach((article, count) -> {
                    Part part = null;
                    if (count == 0) {
                        errorArticlesMap.put(article, "Incorrect count");
                    } else {
                        if (!partsService.existsPartByArticle(article)) {
                            part = storagUtil.decodeArticleToPart(article);
                            if (part != null) {
                                partsService.savePart(part);
                            }
                        }
                        if (part != null) {
                            part = partsService.findByArticle(article);
                            addPartsToInventory(part, article, count);
                        }
                    }
                }
        );
        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setSuccessArticles(articlesList);
        inventoryResponse.setErrorArticles(errorArticlesMap);
        return inventoryResponse;
    }

    private void addPartsToInventory(Part part, String article, Integer count) {
        if (part != null) {
            Inventory inventory = new Inventory();
            if (inventoryRepository.existsByPartId(part.getId())) {
                inventory = inventoryRepository.findByPartId(part.getId());
                count = count + inventory.getCount();
            }
            inventory.setCount(count);
            inventory.setPart(part);
            inventory.setPlace(placesService.findUnloadingZone());
            inventoryRepository.save(inventory);
            articlesList.add(article);
        }
    }

    @Override
    public Inventory changePlaceOfPart(ChangePlaceOfPart changePlaceOfPart) {
        Long newPlaceId = changePlaceOfPart.getNewPlaceId();
        Long partId = changePlaceOfPart.getPartId();
        Long placeId = changePlaceOfPart.getPlaceId();
        Integer count = changePlaceOfPart.getCount();

        Place place = placesService.findById(newPlaceId);
        Part part = partsService.findById(partId);

        Inventory inventory = findInventory(placeId, partId);
        changeCountOfInventory(inventory, count);

        Inventory newInventory = new Inventory(part, count, place);

        return saveNewInventory(newInventory, count);
    }

    private Inventory saveNewInventory(Inventory inventory, Integer count) {
        Long placeId = inventory.getPlace().getId();
        Long partId = inventory.getPart().getId();
        Optional<Inventory> optInventory = inventoryRepository.findByPlaceIdAndPartId(placeId, partId);
        if (optInventory.isPresent()) {
            inventory = optInventory.get();
            inventory.setCount(inventory.getCount() + count);
        }
        return inventoryRepository.save(inventory);
    }

    private void changeCountOfInventory(Inventory inventory, Integer count) {
        int newCount = inventory.getCount() - count;
        if (newCount < 0) {
            throw new IllegalStateException("Incorrect count '" + newCount + "'");
        }
        if (newCount == 0) {
            inventoryRepository.delete(inventory);
        } else {
            inventory.setCount(newCount);
            inventoryRepository.save(inventory);
        }
    }

    private Inventory findInventory(Long placeId, Long partId) {
        List<Inventory> inventoryByPlaceId = findByPlaceId(placeId);
        List<Inventory> inventoryByPartId = findByPartId(partId);
        return inventoryByPlaceId.stream()
                .filter(inventoryByPartId::contains)
                .reduce((a, b) -> {
                    throw new IllegalStateException("Too many inventories with part id '" + partId + "' and place id '" + placeId + "'' found");
                }).orElseThrow(() -> new EntityNotFoundException("Inventory was not found"));
    }

    private List<Inventory> findByPlaceId(Long placeId) {
        List<Inventory> inventoryByPlaceId = inventoryRepository.findAllByPlaceId(placeId);
        if ((long) inventoryByPlaceId.size() == 0) {
            throw new EntityNotFoundException("Inventories was not found by place id '" + placeId + " '");
        }
        return inventoryByPlaceId;
    }

    private List<Inventory> findByPartId(Long partId) {
        List<Inventory> inventoryByPartId = inventoryRepository.findAllByPartId(partId);
        if ((long) inventoryByPartId.size() == 0) {
            throw new EntityNotFoundException("Inventories was not found by part id '" + partId + " '");
        }
        return inventoryByPartId;
    }

    @Override
    public List<Inventory> findAllInventories() {
        return inventoryRepository.findAll();
    }


    @Override
    public String getInventoryOnStorage(String article, Integer count) {
        if (!partsService.existsPartByArticle(article)) {
            throw new EntityNotFoundException("This part was not found by article'" + article + "'");
        }

        Part part = partsService.findByArticle(article);
        Map<String, Integer> partsAndCount = new HashMap<>();
        List<Inventory> inventories = findAllInventories().stream()
                .filter(inventory -> inventory.getPart().getId() == part.getId())
                .filter(inventory -> !inventory.getPlace().getName().equals(UNLOADIG_ZONE))
                .collect(Collectors.toList());
        AtomicInteger changingCount = new AtomicInteger(count);
        for (Inventory inventory : inventories) {
            int tmpCount = inventory.getCount() - changingCount.get();
            if (tmpCount > 0) {
                inventory.setCount(tmpCount);
                inventoryRepository.save(inventory);
                break;
            } else {
                inventoryRepository.deleteById(inventory.getId());
                changingCount.set(Math.abs(tmpCount));
            }
        }
        partsAndCount.put(part.getArticle(), count);
        return mapToJson(partsAndCount);
    }

    private String mapToJson(Map<String, Integer> result) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
