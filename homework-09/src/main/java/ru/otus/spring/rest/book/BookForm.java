package ru.otus.spring.rest.book;

import lombok.Data;

@Data
public class BookForm {
    private Long id;
    private String title;
    private String author;
    private String genre;
}
