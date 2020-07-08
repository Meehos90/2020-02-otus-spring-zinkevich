package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.exception.NoObjectFoundException;
import ru.otus.spring.model.CarBooklet;
import ru.otus.spring.service.ColorService;

import java.util.Arrays;

import static ru.otus.spring.model.Color.*;

@Service
public class ColorServiceImpl implements ColorService {

    @Override
    public CarBooklet initColor(CarBooklet carBooklet) {
        String model = carBooklet.getModel();
        switch (model) {
            case "ECO_SPORT":
                carBooklet.setColors(Arrays.asList(FROZEN_WHITE, RACE_RED, COPPER_PULSE, SHADOW_BLACK, MOONDUST_SILVER, DEEP_IMPACT_BLUE, TIGER_EYE));
                break;
            case "FIESTA":
                carBooklet.setColors(Arrays.asList(FROZEN_WHITE, RACE_RED, COPPER_PULSE, SHADOW_BLACK, MOONDUST_SILVER, DEEP_IMPACT_BLUE, MARS_RED, MAGNETIC));
                break;
            case "FOCUS":
                carBooklet.setColors(Arrays.asList(FROZEN_WHITE, CANDY_RED, SHADOW_BLACK, MOONDUST_SILVER, DEEP_IMPACT_BLUE, MAGNETIC, BLAZER_BLUE, LUNAR_SKY, TECTONIC_SILVER));
                break;
            default:
                throw new NoObjectFoundException(String.format("%s model is not found", model));
        }
        return carBooklet;
    }

}
