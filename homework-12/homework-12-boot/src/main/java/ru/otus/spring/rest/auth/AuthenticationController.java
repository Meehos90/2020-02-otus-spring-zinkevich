package ru.otus.spring.rest.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @GetMapping("/api/auth")
    public void authenticate() {}
}
