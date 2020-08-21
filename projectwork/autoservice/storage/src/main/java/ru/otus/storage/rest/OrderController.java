package ru.otus.storage.rest;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.storage.feign.DiagnosticService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Api(tags = "orders", protocols = "HTTP")
public class OrderController {
    private final DiagnosticService diagnosticService;

    @GetMapping("order/find-all-order-details")
    public Map<String, String> findOrderDetails() {
       return diagnosticService.findOrderDetails();
    }
}
