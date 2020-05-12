package ru.otus.spring.rest.comment;

import lombok.Data;

@Data
public class CommentForm {
    private Long id;
    private String content;
    private String book;
}
