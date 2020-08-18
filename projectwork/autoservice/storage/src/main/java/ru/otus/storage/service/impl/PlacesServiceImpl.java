package ru.otus.storage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.storage.dao.PlacesRepository;
import ru.otus.storage.exception.EntityNotFoundException;
import ru.otus.storage.model.Place;
import ru.otus.storage.service.PlacesService;

@Service
@RequiredArgsConstructor
public class PlacesServiceImpl implements PlacesService {
    public static final String UNLOADIG_ZONE = "UNLOADING_ZONE";
    private final PlacesRepository placesRepository;

    @Override
    public Place findUnloadingZone() {
       return placesRepository.findByName(UNLOADIG_ZONE)
                .orElseThrow(() -> new EntityNotFoundException("Place was not found by name '" + UNLOADIG_ZONE + " '"));

    }

    @Override
    public Place findById(Long id) {
        return placesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Place was not found by id '" + id + " '"));
    }
}
