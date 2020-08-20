package ru.otus.storage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.storage.dao.ModelRepository;
import ru.otus.storage.model.Model;
import ru.otus.storage.service.ModelService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;

    @Transactional
    @Override
    public List<Model> findAll() {
        return modelRepository.findAll();
    }
}
