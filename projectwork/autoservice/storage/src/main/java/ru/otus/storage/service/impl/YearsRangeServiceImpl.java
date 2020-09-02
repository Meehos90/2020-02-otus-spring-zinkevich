package ru.otus.storage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.storage.dao.YearsRangeRepository;
import ru.otus.storage.model.YearsRange;
import ru.otus.storage.service.YearsRangeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class YearsRangeServiceImpl implements YearsRangeService {
    private final YearsRangeRepository yearsRangeRepository;

    @Transactional
    @Override
    public List<YearsRange> findAll() {
        return yearsRangeRepository.findAll();
    }
}
