package ru.otus.diagnostic.model;

import lombok.Data;

import java.util.Map;

@Data
public class PreOrder {
    private Map<String, Integer> partsAndCount;
    private Long afterWhatTime;
}
