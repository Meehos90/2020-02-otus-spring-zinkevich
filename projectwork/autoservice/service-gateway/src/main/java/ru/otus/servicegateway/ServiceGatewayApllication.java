package ru.otus.servicegateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
@EnableHystrix
@EnableCircuitBreaker
public class ServiceGatewayApllication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceGatewayApllication.class, args);
    }

}
