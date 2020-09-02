package ru.otus.storage.service;

import ru.otus.storage.model.Place;

import java.util.List;


public interface PlacesService {
    Place findUnloadingZone();
    Place findById(Long id);
    List<Place> findPlacesByArticle(String article);
}
