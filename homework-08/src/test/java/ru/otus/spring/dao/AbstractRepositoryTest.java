package ru.otus.spring.dao;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.spring.config", "ru.otus.spring.dao"})
public class AbstractRepositoryTest  {
}
