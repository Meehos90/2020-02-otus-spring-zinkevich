package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.spring.security.jwt.JwtProvider;

@SpringBootApplication
@EnableConfigurationProperties(JwtProvider.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
