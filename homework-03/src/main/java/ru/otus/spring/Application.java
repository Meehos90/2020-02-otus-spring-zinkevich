package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.spring.config.LocalizationProperties;
import ru.otus.spring.service.TestingService;

@SpringBootApplication
@EnableConfigurationProperties(LocalizationProperties.class)
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        TestingService testingService = ctx.getBean(TestingService.class);
        testingService.startTesting();
        ctx.close();
    }
}
