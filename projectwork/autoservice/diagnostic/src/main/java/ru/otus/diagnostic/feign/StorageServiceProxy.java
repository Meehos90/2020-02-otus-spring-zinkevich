package ru.otus.diagnostic.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Map;

@FeignClient(name = "storage")
public interface StorageServiceProxy {

    @GetMapping(value = "parts/get-article-by-params/{partName}/{autoMark}/{autoModel}/{autoYear}")
    String findArticleByParams(@PathVariable String partName,
                               @PathVariable String autoMark,
                               @PathVariable String autoModel,
                               @PathVariable String autoYear);

    @PostMapping("/inventory/action/check-inventories-on-storage")
    String checkInventoriesOnStorage(@Valid @RequestBody Map<String, Integer> partsAndCount);

    @PostMapping("/inventory/action/set-inventories-to-order")
    String setInventoriesToOrder(@Valid @RequestBody Map<String, Integer> partsAndCount);

}
