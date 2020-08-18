package ru.otus.diagnostic.feign;

import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.Map;

@Component
public class StorageFallbackService implements StorageService {

    public static final String STORAGE_IS_NOT_AVAILABLE = "Microservice 'Storage' is not available! Try again later";

    @Override
    public String findArticleByParams(String partName, String autoMark, String autoModel, String autoYear) {
        return STORAGE_IS_NOT_AVAILABLE;
    }

    @Override
    public String checkInventoriesOnStorage(@Valid Map<String, Integer> partsAndCount) {
        return STORAGE_IS_NOT_AVAILABLE;
    }

    @Override
    public String setInventoriesToOrder(@Valid Map<String, Integer> partsAndCount) {
        return STORAGE_IS_NOT_AVAILABLE;
    }
}
