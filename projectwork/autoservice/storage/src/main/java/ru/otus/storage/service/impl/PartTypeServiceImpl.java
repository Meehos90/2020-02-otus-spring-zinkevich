package ru.otus.storage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.storage.dao.PartTypeRepository;
import ru.otus.storage.model.PartType;
import ru.otus.storage.service.PartTypeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartTypeServiceImpl implements PartTypeService {
    private final PartTypeRepository partTypeRepository;

    @Transactional
    @Override
    public List<PartType> findAll() {
        return partTypeRepository.findAll();
    }
}
