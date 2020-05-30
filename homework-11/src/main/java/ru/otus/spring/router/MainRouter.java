package ru.otus.spring.router;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.spring.dao.author.AuthorRepository;
import ru.otus.spring.handlers.AuthorHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class MainRouter {

    @Bean
    public RouterFunction<ServerResponse> functionalRoute(AuthorRepository repository, RequestHandler requestHandler) {
        AuthorHandler authorHandler = new AuthorHandler(repository);
        RouterFunction<ServerResponse> route = route()
                .GET("/api/authors", accept(APPLICATION_JSON), authorHandler::findAllAuthors)
                .GET("/api/author/find", queryParam("fullName", StringUtils::isNotEmpty), authorHandler::findAuthorByFullName)
                .GET("/api/author/find", queryParam("id", StringUtils::isNotEmpty), authorHandler::findAuthorById)
                .GET("/api/author/{id}", accept(APPLICATION_JSON), authorHandler::getAuthorById)
                .PUT("/api/author/put/{id}", accept(APPLICATION_JSON), authorHandler::putAuthor)
                .POST("/api/author/post", authorHandler::addAuthor)
                .DELETE("/api/author/delete/{id}", accept(APPLICATION_JSON), authorHandler::deleteAuthor)
                .build();
        return route;
    }
}
