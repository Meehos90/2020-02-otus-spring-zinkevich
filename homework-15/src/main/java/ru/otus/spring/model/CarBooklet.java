package ru.otus.spring.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class CarBooklet {
    private final Long orderId;
    private final String model;

    private List<BodyType> bodyTypes = new ArrayList<>();
    private List<Color> colors = new ArrayList<>();
    private List<Complectation> complectations = new ArrayList<>();
}
