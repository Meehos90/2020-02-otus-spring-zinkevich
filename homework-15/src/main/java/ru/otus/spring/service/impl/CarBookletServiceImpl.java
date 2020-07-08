package ru.otus.spring.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.exception.NoObjectFoundException;
import ru.otus.spring.model.CarBooklet;
import ru.otus.spring.service.CarBookletService;

import java.util.concurrent.atomic.AtomicLong;


@Slf4j
@Service
public class CarBookletServiceImpl implements CarBookletService {
    private AtomicLong orderId = new AtomicLong(1);

    @Override
    public CarBooklet initCarBooklet(String model) {
        try {
            return new CarBooklet(orderId.incrementAndGet(), model);
        } catch (NoObjectFoundException ex) {
            log.error("This model is not found", ex);
            throw ex;
        }
    }
}
