package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.exception.NoObjectFoundException;
import ru.otus.spring.model.CarBooklet;
import ru.otus.spring.service.BodyTypeService;

import java.util.Arrays;
import java.util.Collections;

import static ru.otus.spring.model.BodyType.*;

@Service
public class BodyTypeServiceImpl implements BodyTypeService {

    @Override
    public CarBooklet initBodyType(CarBooklet carBooklet) {
        String model = carBooklet.getModel();
        switch (model) {
            case "ECO_SPORT":
                carBooklet.setBodyTypes(Collections.singletonList(CROSSOVER));
                break;
            case "FIESTA":
                carBooklet.setBodyTypes(Arrays.asList(SEDAN, HATCHBACK));
                break;
            case "FOCUS":
                carBooklet.setBodyTypes(Arrays.asList(SEDAN, HATCHBACK, STATION_WAGON));
                break;
            default:
                throw new NoObjectFoundException(String.format("%s model is not found", model));
        }
        return carBooklet;
    }
}
