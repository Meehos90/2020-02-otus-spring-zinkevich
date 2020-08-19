package ru.otus.storage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.storage.dao.PartsRepository;
import ru.otus.storage.exception.EntityNotFoundException;
import ru.otus.storage.model.Part;
import ru.otus.storage.service.PartsService;

@Service
@RequiredArgsConstructor
public class PartsServiceImpl implements PartsService {
    private final PartsRepository partsRepository;
    private final StorageUtil storageUtil;

    @Transactional
    @Override
    public boolean existsPartByArticle(String article) {
        return partsRepository.existsByArticle(article);
    }

    @Transactional
    @Override
    public Part findByArticle(String article) {
        return partsRepository.findByArticle(article)
                .orElseThrow(() -> new EntityNotFoundException("Part was not found by article '" + article + " '"));
    }

    @Transactional
    @Override
    public Part savePart(Part part) {
        return partsRepository.save(part);
    }

    @Transactional
    @Override
    public Part findById(Long id) {
        return partsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Part was not found by id '" + id + "'"));
    }

    @Transactional
    @Override
    public String findArticleByParams(String name, String mark, String model, String year) {
        return findByArticle(storageUtil.encodeParamsToArticle(mark, model, year, name)).getArticle();
    }

    @Transactional
    @Override
    public void deletePart(Long partId) {
        Part part = findById(partId);
        partsRepository.deleteById(part.getId());
    }
}
