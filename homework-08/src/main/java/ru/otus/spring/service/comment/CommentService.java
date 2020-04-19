package ru.otus.spring.service.comment;

import ru.otus.spring.model.Comment;

import java.util.List;

public interface CommentService {
    void save();
    void update();
    void delete();
    Comment findCommentById();
    Comment findCommentByContent();
    List<Comment> findCommentByBookTitle();
    List<Comment> findAll();
}
