package ru.otus.storage.rest;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.storage.model.Place;
import ru.otus.storage.service.PlacesService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "places", protocols = "HTTP")
public class PlaceController {
    private final PlacesService placesService;

    @GetMapping("/places/get-by-article/{article}")
    public List<Place> findAllInventories(@PathVariable String article) {
        return placesService.findPlacesByArticle(article);
    }
}
