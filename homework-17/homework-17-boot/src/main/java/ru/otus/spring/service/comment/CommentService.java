package ru.otus.spring.service.comment;

import ru.otus.spring.model.Comment;

import java.util.List;

public interface CommentService {

    Comment save(Comment comment);

    Comment update(Comment comment);

    void deleteById(Long id);

    List<Comment> findAll();

    Comment findById(Long id);

    boolean existsById(Long id);
}
