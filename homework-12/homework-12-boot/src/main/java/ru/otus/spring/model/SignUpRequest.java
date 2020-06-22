package ru.otus.spring.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class SignUpRequest {
    @NotBlank
    private String username;

    private Set<String> role;

    @NotBlank
    private String password;
}
