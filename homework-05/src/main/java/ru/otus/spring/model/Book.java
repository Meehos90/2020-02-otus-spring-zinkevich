package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Book {
    private long id;
    private String title;
    private String author;
    private String genre;
}
