package ru.otus.storage.service;

import ru.otus.storage.model.Place;


public interface PlacesService {
    Place findUnloadingZone();
    Place findById(Long id);
}
