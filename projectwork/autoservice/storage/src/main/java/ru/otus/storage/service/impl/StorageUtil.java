package ru.otus.storage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.storage.exception.EntityNotFoundException;
import ru.otus.storage.model.*;
import ru.otus.storage.service.*;

import java.util.List;

import static ru.otus.storage.service.impl.InventoryServiceImpl.errorArticlesMap;

@Component
@RequiredArgsConstructor
public class StorageUtil {
    private final MarkService markService;
    private final ModelService modelService;
    private final YearsRangeService yearsRangeService;
    private final PartTypeService partTypeService;

    public Part decodeArticleToPart(String article) {
        YearsRange rangeOfYears = decodeRangeOfYears(article);
        Mark mark = decodeMark(article);
        Model model = decodeModel(article);
        PartType partType = decodePartTypeName(article);

        Part part = new Part(article, partType, mark, model, rangeOfYears);
        if (part.getArticle() != null
                && part.getPartType() != null
                && part.getMark() != null
                && part.getModel() != null
                && part.getRangeOfYears() != null) {
            return part;
        } else {
            return null;
        }
    }

    private PartType decodePartTypeName(String article) {
        String artPartType = article.substring(5);
        List<PartType> partTypes = partTypeService.findAll();
        return partTypes.stream()
                .filter(p -> p.getArticlePartType().startsWith(artPartType))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Too many part types found");
                }).orElseGet(() -> {
                    errorArticlesMap.put(article, "Incorrect part type");
                    return null;
                });
    }

    private Mark decodeMark(String article) {
        String artMark = article.substring(0, 1);
        List<Mark> marks = markService.findAll();
        return marks.stream()
                .filter(m -> m.getName().startsWith(artMark))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Too many marks found");
                }).orElseGet(() -> {
                    errorArticlesMap.put(article, "Incorrect mark");
                    return null;
                });
    }

    private Model decodeModel(String article) {
        String firstSymbol = article.substring(1, 2);
        String lastSymbol = article.substring(2, 3);
        List<Model> models = modelService.findAll();
        return models.stream()
                .filter(m -> m.getName().startsWith(firstSymbol))
                .filter(m -> m.getName().endsWith(lastSymbol))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Too many models found");
                }).orElseGet(() -> {
                    errorArticlesMap.put(article, "Incorrect mark");
                    return null;
                });
    }

    private YearsRange decodeRangeOfYears(String article) {
        String artRangeOfYears = article.substring(3, 5);
        List<YearsRange> yearsRanges = yearsRangeService.findAll();
        return yearsRanges.stream()
                .filter(y -> y.getArticleRange().startsWith(artRangeOfYears))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Too many ranges of years found");
                }).orElseGet(() -> {
                    errorArticlesMap.put(article, "Incorrect range of years");
                    return null;
                });
    }


    public String encodeParamsToArticle(String autoMark, String autoModel, String autoYear, String partName) {
        String artMark = encodeMark(autoMark);
        String artModel = encodeModel(autoModel);
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
        List<PartType> partTypes = partTypeService.findAll();
        PartType partType = partTypes.stream()
                .filter(p -> p.getName().equals(lowCasePartName))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Too many part types found");
                }).orElseThrow(() -> new EntityNotFoundException("Part name was not found"));
        return partType.getArticlePartType();
    }

    private String encodeMark(String autoMark) {
        String artMark = autoMark.toUpperCase().substring(0, 1);
        List<Mark> marks = markService.findAll();
        for (Mark mark : marks) {
            if (mark.getName().startsWith(artMark)) {
                return artMark;
            }
        }
        throw new EntityNotFoundException("Mark was not found");
    }

    private String encodeModel(String autoModel) {
        String firstSymbol = autoModel.toUpperCase().substring(0, 1);
        String lastSymbol = autoModel.toUpperCase().substring(autoModel.length() - 1);
        String artModel = firstSymbol + lastSymbol;
        List<Model> models = modelService.findAll();
        for (Model model : models) {
            if (model.getName().startsWith(firstSymbol) && model.getName().endsWith(lastSymbol)) {
                return artModel;
            }
        }
        throw new EntityNotFoundException("Model was not found");
    }
}
