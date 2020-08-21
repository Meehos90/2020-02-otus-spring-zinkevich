package ru.otus.storage.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;


@FeignClient(name = "diagnostic", fallback = DiagnosticFallbackService.class)
public interface DiagnosticService {

    @GetMapping("/order/find-all-order-details")
    Map<String, String> findOrderDetails();

}
