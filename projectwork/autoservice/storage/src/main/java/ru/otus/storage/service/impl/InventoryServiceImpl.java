package ru.otus.storage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.storage.dao.InventoryRepository;
import ru.otus.storage.exception.EntityNotFoundException;
import ru.otus.storage.model.ChangePlaceOfPart;
import ru.otus.storage.model.Inventory;
import ru.otus.storage.model.InventoryResponse;
import ru.otus.storage.model.MarkAndModelMap;
import ru.otus.storage.model.Part;
import ru.otus.storage.model.PartTypeMap;
import ru.otus.storage.model.Place;
import ru.otus.storage.model.RangeOfYearsMap;
import ru.otus.storage.service.InventoryService;
import ru.otus.storage.service.PartsService;
import ru.otus.storage.service.PlacesService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.otus.storage.service.impl.PlacesServiceImpl.UNLOADIG_ZONE;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final PartsService partsService;
    private final PlacesService placesService;
    private final InventoryRepository inventoryRepository;

    private final RangeOfYearsMap rangeOfYearsMap = RangeOfYearsMap.getInstance();
    private final PartTypeMap partTypeMap = PartTypeMap.getInstance();
    private final MarkAndModelMap markAndModelMap = MarkAndModelMap.getInstance();
    InventoryResponse inventoryResponse = new InventoryResponse();
    List<String> articlesList = new ArrayList<>();
    Map<String, String> errorArticlesMap = new HashMap<>();

    @Override
    public InventoryResponse addPartsToStorage(Map<String, Integer> articles) {
        articles.forEach((article, count) -> {
                    Part part = null;
                    if (count == 0) {
                        errorArticlesMap.put(article, "Incorrect count");
                    } else {
                        if (!partsService.existsPartByArticle(article)) {
                           part = decodeArticleToPart(article);
                        }
                        if (part != null) {
                            part = partsService.findByArticle(article);
                            addPartsToInventory(part, article, count);
                        }
                    }
                }
        );
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

    private Part decodeArticleToPart(String article) {
        String rangeOfYears = decodeRangeOfYears(article);
        String mark = decodeMark(article);
        String model = null;
        String name = decodePartName(article);
        if (mark != null) {
            model = decodeModel(article);
        }
        Part part = new Part(article, name, mark, model, rangeOfYears);

        if (part.getArticle() != null
                && part.getName() != null
                && part.getMark() != null
                && part.getModel() != null
                && part.getRangeOfYears() != null) {
           return partsService.savePart(part);
        } else {
            return null;
        }
    }

    private String decodeRangeOfYears(String article) {
        String artRangeOfYears = article.substring(3, 5);
        if (rangeOfYearsMap.containsKey(artRangeOfYears)) {
            return rangeOfYearsMap.get(artRangeOfYears).getRange();
        } else {
            errorArticlesMap.put(article, "Incorrect range of years");
            return null;
        }
    }

    private String decodePartName(String article) {
        String artPartType = article.substring(5);
        if (partTypeMap.containsKey(artPartType)) {
            return partTypeMap.get(artPartType).getName();
        } else {
            errorArticlesMap.put(article, "Incorrect part name");
            return null;
        }
    }

    private String decodeMark(String article) {
        String mark = article.substring(0, 1);
        if (markAndModelMap.containsKey(mark)) {
            return markAndModelMap.get(mark).getMarkType().name();
        } else {
            errorArticlesMap.put(article, "Incorrect mark");
            return null;
        }
    }

    private String decodeModel(String article) {
        String mark = article.substring(0, 1);
        String model = article.substring(1, 3);
        MarkAndModelMap.MarkValue markValue = markAndModelMap.get(mark);
        if (markValue.getModelTypes().containsKey(model)) {
            return markValue.getModelTypes().get(model).name();
        } else {
            errorArticlesMap.put(article, "Incorrect model");
            return null;
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
        if(optInventory.isPresent()) {
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
    public List<Inventory> findAllInventoriesWithoutUnloadingZone() {
        return findAllInventories().stream()
                .filter(inventory -> !inventory.getPlace().getName().equals(UNLOADIG_ZONE))
                .collect(Collectors.toList());
    }

    @Override
    public List<Inventory> findInventoriesInUnloadingZone() {
        return findAllInventories().stream()
                .filter(inventory -> inventory.getPlace().getName().equals(UNLOADIG_ZONE))
                .collect(Collectors.toList());
    }
}
