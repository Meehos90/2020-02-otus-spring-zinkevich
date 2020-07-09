package ru.otus.spring.actuators;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Endpoint(id="custom-health")
public class CustomHealthEndpoint {

    @ReadOperation
    public CustomHealth health() {
        return new CustomHealth(Collections.singletonMap("CustomHealthStatus", "Everything looks good"));
    }
}
