package ru.otus.servicegateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Autowired
    private ZuulProperties properties;

    @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider() {
        return () -> {
            List<SwaggerResource> resources = new ArrayList<>();
            properties.getRoutes().values()
                    .forEach(route -> resources
                    .add(createResource(route.getServiceId())));
            return resources;
        };
    }

    private SwaggerResource createResource(String name) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation("/autoservice/" + name + "/v2/api-docs");
        return swaggerResource;
    }
}
