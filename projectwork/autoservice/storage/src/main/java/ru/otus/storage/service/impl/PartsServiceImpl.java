package ru.otus.storage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.storage.dao.PartsRepository;
import ru.otus.storage.exception.EntityNotFoundException;
import ru.otus.storage.model.Part;
import ru.otus.storage.service.PartsService;

@Service
@RequiredArgsConstructor
public class PartsServiceImpl implements PartsService {
    private final PartsRepository partsRepository;
    private final StoragUtil storagUtil;

    @Override
    public boolean existsPartByArticle(String article) {
        return partsRepository.existsByArticle(article);
    }

    @Override
    public Part findByArticle(String article) {
        return partsRepository.findByArticle(article)
                .orElseThrow(() -> new EntityNotFoundException("Part was not found by article '" + article + " '"));
    }

    @Override
    public Part savePart(Part part) {
        return partsRepository.save(part);
    }

    @Override
    public Part findById(Long id) {
        return partsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Part was not found by id '" + id + "'"));
    }

    @Override
    public String findByParameters(String name, String mark, String model, String year) {
        return findByArticle(storagUtil.encodeParamsToArticle(mark, model, year, name)).getArticle();
    }

    @Override
    public void deletePart(Long partId) {
        Part part = findById(partId);
        partsRepository.deleteById(part.getId());
    }
}
