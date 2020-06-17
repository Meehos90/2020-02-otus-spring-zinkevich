package ru.otus.spring.rest.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.config.CustomAuthentication;
import ru.otus.spring.model.User;
import ru.otus.spring.service.users.MyDatabaseUserDetailsService;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final CustomAuthentication  authenticationManager;

    @GetMapping("/api/auth")
    public void authenticate() {}

    @PostMapping("/api/login")
    public MyDatabaseUserDetailsService processLoginForm(@RequestBody User user)
    {
        String username = user.getUsername();
        String password = user.getPassword();
        Authentication authRequest = new UsernamePasswordAuthenticationToken(username, password);
        Authentication auth = authenticationManager.authenticate(authRequest);
        return (MyDatabaseUserDetailsService) auth.getPrincipal();
    }
}
