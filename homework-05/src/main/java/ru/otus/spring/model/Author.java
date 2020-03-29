package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Author {
    private long id;
    private String fullName;
}
