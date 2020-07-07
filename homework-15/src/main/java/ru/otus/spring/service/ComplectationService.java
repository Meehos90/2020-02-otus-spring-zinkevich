package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.exception.NoObjectFoundException;
import ru.otus.spring.model.CarBooklet;

import java.util.Arrays;

import static ru.otus.spring.model.Complectation.*;

@Service
public class ComplectationService {

    public CarBooklet initComplectation(CarBooklet carBooklet) {
        String model = carBooklet.getModel();
        switch (model) {
            case "FOCUS":
                carBooklet.setComplectations(Arrays.asList(AMBIENTE, SYNC_EDITION, WHITE_AND_BLACK, TITANIUM));
                break;
            case "ECO_SPORT":
                carBooklet.setComplectations(Arrays.asList(AMBIENTE, TREND, TREND_PLUS, TITANIUM, TITANIUM_PLUS));
                break;
            case "FIESTA":
                carBooklet.setComplectations(Arrays.asList(AMBIENTE, TREND, WHITE_AND_BLACK, TITANIUM));
                break;
            default:
                throw new NoObjectFoundException(String.format("%s model is not found", model));
        }
        return carBooklet;
    }
}
