package ru.otus.diagnostic.rest;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.diagnostic.model.Order;
import ru.otus.diagnostic.service.OrderService;

@RestController
@RequiredArgsConstructor
@Api(tags = "order", protocols = "HTTP")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/order/get-order/{id}")
    public Order findOrder(@PathVariable(value = "id") Long orderId) {
        return orderService.findOrderById(orderId);
    }

    @PostMapping("/order/add-order/{article}/{count}")
    public Order addOrder(
            @PathVariable String article,
            @PathVariable Integer count) {
        return orderService.addOrder(article, count);
    }

    @GetMapping("/order/find-article-on-storage/{partName}/{autoMark}/{autoModel}/{autoYear}")
    public String findArticleByParams(@PathVariable String partName,
                                      @PathVariable String autoMark,
                                      @PathVariable String autoModel,
                                      @PathVariable String autoYear) {
        return orderService.findArticleByParams(partName, autoMark, autoModel, autoYear);
    }

}
