package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.exception.NoObjectFoundException;
import ru.otus.spring.model.CarBooklet;


@Slf4j
@Service
public class CarBookletService {
    private Long orderId = 1L;

    public CarBooklet initCarBooklet(String model) {
        try {
            return new CarBooklet(orderId++, model);
        } catch (NoObjectFoundException ex) {
            log.error("This model is not found", ex);
            throw ex;
        }
    }
}
