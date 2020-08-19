package ru.otus.storage.service.impl;

import org.springframework.stereotype.Component;
import ru.otus.storage.exception.EntityNotFoundException;
import ru.otus.storage.model.*;

import java.util.Map;

import static ru.otus.storage.service.impl.InventoryServiceImpl.errorArticlesMap;

@Component
public class StorageUtil {
    private final RangeOfYearsMap rangeOfYearsMap = RangeOfYearsMap.getInstance();
    private final PartTypeMap partTypeMap = PartTypeMap.getInstance();
    private final MarkAndModelMap markAndModelMap = MarkAndModelMap.getInstance();

    public Part decodeArticleToPart(String article) {
        String rangeOfYears = decodeRangeOfYears(article);
        String mark = decodeMark(article);
        String model = null;
        String name = decodePartName(article);
        if (mark != null) {
            model = decodeModel(article);
        }
        Part part = new Part(article, name, mark, model, rangeOfYears);
        if (part.getArticle() != null
                && part.getName() != null
                && part.getMark() != null
                && part.getModel() != null
                && part.getRangeOfYears() != null) {
            return part;
        } else {
            return null;
        }
    }

    public String encodeParamsToArticle(String autoMark, String autoModel, String autoYear, String partName) {
        String artMark = encodeMark(autoMark);
        String artModel = encodeModel(artMark, autoModel);
        String artRangeOfYears = encodeRangeOfYears(autoYear);
        String artPartName = encodePartName(partName);

        return artMark + artModel + artRangeOfYears + artPartName;
    }

    private String encodeRangeOfYears(String autoYear) {
        String century = autoYear.substring(0, 2);
        String decade = autoYear.substring(2, 3);
        int year = Integer.parseInt(autoYear.substring(3, 4));

        if (century.equals("19") && decade.equals("7") || decade.equals("8") || decade.equals("9")) {
            return encodeRangeOfYears(decade, year);
        }

        if (century.equals("20") && decade.equals("0") || decade.equals("1")) {
            return encodeRangeOfYears(decade, year);
        }
        throw new EntityNotFoundException("Range of years was not found");
    }

    private String encodeRangeOfYears(String decade, int year) {
        if (year >= 0 && year <= 5) {
            return decade + 0;
        }
        if (year >= 6 && year <= 9) {
            return decade + 6;
        } else {
            throw new EntityNotFoundException("Range of years was not found");
        }
    }

    private String encodePartName(String partName) {
        String lowCasePartName = partName.toLowerCase();
        return partTypeMap
                .entrySet()
                .stream()
                .filter(entry -> lowCasePartName.equals(entry.getValue().getName()))
                .map(Map.Entry::getKey).reduce((a, b) -> {
                    throw new IllegalStateException("Too many values with part name '" + partName + "'' found");
                })
                .orElseThrow(() -> new EntityNotFoundException("Part name was not found"));
    }

    private String encodeMark(String autoMark) {
        String artMark = autoMark.toUpperCase().substring(0, 1);
        if (markAndModelMap.containsKey(artMark)) {
            return artMark;
        } else {
            throw new EntityNotFoundException("Mark was not found");
        }
    }

    private String encodeModel(String autoMark, String autoModel) {
        String firstSymbol = autoModel.toUpperCase().substring(0, 1);
        String lastSymbol = autoModel.toUpperCase().substring(autoModel.length() - 1);
        String artModel = firstSymbol + lastSymbol;
        Map<String, ModelType> modelTypes = markAndModelMap.get(autoMark).getModelTypes();
        if (modelTypes.containsKey(artModel)) {
            return artModel;
        } else {
            throw new EntityNotFoundException("Model was not found");
        }
    }

    private String decodeModel(String article) {
        String mark = article.substring(0, 1);
        String model = article.substring(1, 3);
        MarkAndModelMap.MarkValue markValue = markAndModelMap.get(mark);
        if (markValue.getModelTypes().containsKey(model)) {
            return markValue.getModelTypes().get(model).name();
        } else {
            errorArticlesMap.put(article, "Incorrect model");
            return null;
        }
    }

    private String decodeRangeOfYears(String article) {
        String artRangeOfYears = article.substring(3, 5);
        if (rangeOfYearsMap.containsKey(artRangeOfYears)) {
            return rangeOfYearsMap.get(artRangeOfYears).getRange();
        } else {
            errorArticlesMap.put(article, "Incorrect range of years");
            return null;
        }
    }

    private String decodePartName(String article) {
        String artPartType = article.substring(5);
        if (partTypeMap.containsKey(artPartType)) {
            return partTypeMap.get(artPartType).getName();
        } else {
            errorArticlesMap.put(article, "Incorrect part name");
            return null;
        }
    }

    private String decodeMark(String article) {
        String mark = article.substring(0, 1);
        if (markAndModelMap.containsKey(mark)) {
            return markAndModelMap.get(mark).getMarkType().name();
        } else {
            errorArticlesMap.put(article, "Incorrect mark");
            return null;
        }
    }
}
