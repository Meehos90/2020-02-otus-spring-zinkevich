package ru.otus.spring.rest.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.model.MyUserPrincipal;
import ru.otus.spring.model.User;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    @PostMapping("/api/auth")
    public MyUserPrincipal authenticate(@RequestBody User user) {
        String login = user.getName();
        String password = user.getPassword();
        Authentication authRequest = new UsernamePasswordAuthenticationToken(login, password);
        /*Authentication auth = authenticationManager.authenticate(authRequest);*/
        MyUserPrincipal userDetails = new MyUserPrincipal(user);
        /*rememberMeServices.loginSuccess(request, response, auth);*/
        return  userDetails;
    }
}
