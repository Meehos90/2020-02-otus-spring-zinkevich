package ru.otus.servicegateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableZuulProxy
public class ServiceGatewayApllication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceGatewayApllication.class, args);
    }

}
