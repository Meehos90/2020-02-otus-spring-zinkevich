package ru.otus.storage.rest;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.storage.model.Part;
import ru.otus.storage.service.PartsService;

@RestController
@RequiredArgsConstructor
@Api(tags = "parts", protocols = "HTTP")
public class PartsController {
    private final PartsService partsService;

    @GetMapping("parts/get-by-article/{article}")
    public Part findByArticle(@PathVariable String article) {
        return partsService.findByArticle(article);
    }

    @GetMapping("parts/get-article-by-params/{partName}/{autoMark}/{autoModel}/{autoYear}")
    public String findArticleByParams(@PathVariable String partName,
                                      @PathVariable String autoMark,
                                      @PathVariable String autoModel,
                                      @PathVariable String autoYear) {
        return partsService.findArticleByParams(partName, autoMark, autoModel, autoYear);
    }

    @DeleteMapping("/parts/delete-part-from-storage/{article}")
    public void deletePart(@PathVariable String article) {
        partsService.deletePartFromStorageToOrder(article);
    }
}
