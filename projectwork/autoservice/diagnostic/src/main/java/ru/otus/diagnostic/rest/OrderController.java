package ru.otus.diagnostic.rest;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.diagnostic.model.Order;
import ru.otus.diagnostic.model.PreOrder;
import ru.otus.diagnostic.service.OrderService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Api(tags = "order", protocols = "HTTP")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/order/get-order/{id}")
    public Order findOrder(@PathVariable(value = "id") Long orderId) {
        return orderService.findOrderById(orderId);
    }

    @GetMapping("/order/find-article-on-storage/{partName}/{autoMark}/{autoModel}/{autoYear}")
    public String findArticleByParams(@PathVariable String partName,
                                      @PathVariable String autoMark,
                                      @PathVariable String autoModel,
                                      @PathVariable String autoYear) {
        return orderService.findArticleByParams(partName, autoMark, autoModel, autoYear);
    }

    @PostMapping("/order/check-inventories-on-storage")
    public String checkInventoriesOnStorage(@Valid @RequestBody Map<String, Integer> partsAndCount) {
        return orderService.checkInventoriesOnStorage(partsAndCount);
    }

    @PostMapping("/order/add-order")
    public Order addOrder(@Valid @RequestBody PreOrder preOrder) {
        return orderService.addOrder(preOrder);
    }

    @GetMapping("/order/all-orders")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/order/find-all-order-details")
    public Map<String, String> findOrderDetails() {
        return orderService.findOrderDetails();
    }

}
