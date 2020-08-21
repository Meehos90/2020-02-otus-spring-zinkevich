package ru.otus.storage.feign;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DiagnosticFallbackService implements DiagnosticService {
    public static final String DIAGNOSTIC_IS_NOT_AVAILABLE = "Microservice 'Diagnostic' is not available! Try again later";

    @Override
    public Map<String, String> findOrderDetails() {
        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put("errorMessage", DIAGNOSTIC_IS_NOT_AVAILABLE);
        return errorMessage;
    }
}
