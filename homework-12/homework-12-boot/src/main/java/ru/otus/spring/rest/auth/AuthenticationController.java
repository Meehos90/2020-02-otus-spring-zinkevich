package ru.otus.spring.rest.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.otus.spring.dao.role.RoleRepository;
import ru.otus.spring.dao.user.UserRepository;
import ru.otus.spring.model.*;
import ru.otus.spring.security.jwt.JwtProvider;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    private static final String ADMIN = RoleName.ROLE_ADMIN.name();
    private static final String USER = RoleName.ROLE_USER.name();

    @PostMapping("/api/auth/signin")
    public ResponseEntity<JwtResponse> processLoginForm(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/api/auth/signup")
    public void registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        try {
            userRepository.existsByUsername(signUpRequest.getUsername());
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пользователь с таким именем уже существует", e);
        }

        User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            if (ADMIN.equals(role)) {
                roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN));
            } else if (USER.equals(role))  {
                roles.add(roleRepository.findByName(RoleName.ROLE_USER));
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Такой роли не существует");
            }
        });

        user.setRoles(roles);
        userRepository.save(user);
    }
}
