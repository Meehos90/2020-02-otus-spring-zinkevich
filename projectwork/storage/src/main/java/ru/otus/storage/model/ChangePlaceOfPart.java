package ru.otus.storage.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ChangePlaceOfPart {
    private Long placeId;
    private Long partId;
    private Long newPlaceId;
    private Integer count;
}
