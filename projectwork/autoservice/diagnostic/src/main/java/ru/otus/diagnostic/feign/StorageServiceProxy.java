package ru.otus.diagnostic.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "storage")
public interface StorageServiceProxy {

    @GetMapping(value = "inventory/get-inventories-on-storage/{article}/{count}")
    String getInventoryOnStorage(@PathVariable(value = "article") String article,
                                 @PathVariable(value = "count") Integer count);

    @GetMapping(value = "parts/get-article-by-params/{partName}/{autoMark}/{autoModel}/{autoYear}")
    String findArticleByParams(@PathVariable String partName,
                               @PathVariable String autoMark,
                               @PathVariable String autoModel,
                               @PathVariable String autoYear);
}
