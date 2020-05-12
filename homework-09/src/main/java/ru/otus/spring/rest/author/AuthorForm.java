package ru.otus.spring.rest.author;

import lombok.Data;

@Data
public class AuthorForm {
    private Long id;
    private String fullName;
}
