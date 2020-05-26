package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.spring.dao.author.AuthorRepository;
import ru.otus.spring.rest.author.AuthorHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class FunctionalEndpoints {

    @Bean
    public RouterFunction<ServerResponse> composedRoutes(AuthorRepository repository) {

        AuthorHandler authorHandler = new AuthorHandler(repository);

        RouterFunction<ServerResponse> route = route()
                .GET("/api/authors", accept(APPLICATION_JSON), authorHandler::findAllAuthors)
                .GET("/api/author/{id}", accept(APPLICATION_JSON), authorHandler::getAuthorById)
                .GET("/api/author/findByName", queryParam("name", StringUtils::isNotEmpty), authorHandler::getAuthorByFullName2)
                .POST("/api/author", authorHandler::addAuthor)
                .build();
        return route;
    }
}
