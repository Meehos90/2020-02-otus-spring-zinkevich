package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.FordDealer;
import ru.otus.spring.service.OrderGeneratorService;

@Service
@RequiredArgsConstructor
public class OrderGeneratorServiceIml implements OrderGeneratorService {
    private final FordDealer fordDealer;
    private static final String[] MODEL_NAME = {"FOCUS", "FIESTA", "ECO_SPORT"};

    @Scheduled(initialDelay = 2000, fixedRate = 1000)
    public void clientChoiceModel() {
            fordDealer.selectModel(MODEL_NAME[RandomUtils.nextInt(0, MODEL_NAME.length)]);
    }
}
