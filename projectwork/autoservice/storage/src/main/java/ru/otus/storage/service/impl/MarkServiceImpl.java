package ru.otus.storage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.storage.dao.MarkRepository;
import ru.otus.storage.model.Mark;
import ru.otus.storage.service.MarkService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarkServiceImpl implements MarkService {
    private final MarkRepository markRepository;

    @Transactional
    @Override
    public List<Mark> findAll() {
        return markRepository.findAll();
    }
}
