package ru.otus.spring.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class AppProps {
    @Value("${app.input-database:''}")
    private String inputDBName;

    @Value("${app.output-database:''}")
    private String outputDBName;
}
