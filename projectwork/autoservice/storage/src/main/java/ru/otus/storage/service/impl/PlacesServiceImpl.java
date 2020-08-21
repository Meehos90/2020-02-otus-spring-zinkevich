package ru.otus.storage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.storage.dao.InventoryRepository;
import ru.otus.storage.dao.PlacesRepository;
import ru.otus.storage.exception.EntityNotFoundException;
import ru.otus.storage.model.Inventory;
import ru.otus.storage.model.Part;
import ru.otus.storage.model.Place;
import ru.otus.storage.service.PartsService;
import ru.otus.storage.service.PlacesService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlacesServiceImpl implements PlacesService {
    public static final String UNLOADIG_ZONE = "UNLOADING_ZONE";
    private final PlacesRepository placesRepository;
    private final PartsService partsService;
    private final InventoryRepository inventoryRepository;

    @Transactional
    @Override
    public Place findUnloadingZone() {
       return placesRepository.findByName(UNLOADIG_ZONE)
                .orElseThrow(() -> new EntityNotFoundException("Place was not found by name '" + UNLOADIG_ZONE + " '"));

    }

    @Transactional
    @Override
    public Place findById(Long id) {
        return placesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Place was not found by id '" + id + " '"));
    }

    @Transactional
    @Override
    public List<Place> findPlacesByArticle(String article) {
        Part part = partsService.findByArticle(article);
        List<Place> places = new ArrayList<>();
        List<Inventory> inventories = inventoryRepository.findAllByPartId(part.getId());
        if ((long) inventories.size() == 0) {
            throw new EntityNotFoundException("Inventories was not found by part id '" + part.getId() + " '");
        }
        for(Inventory inventory : inventories) {
            Long placeId = inventory.getPlace().getId();
            Place place = findById(placeId);
            places.add(place);
        }
        return places;
    }
}
