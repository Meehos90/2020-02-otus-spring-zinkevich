package ru.otus.spring.dao.comment;

import ru.otus.spring.model.Comment;

import java.util.List;

public interface CommentDao {
    long count();
    void save(Comment comment);
    void updateContentById(Comment comment);
    void deleteById(long id);
    Comment findById(long id);
    Comment findByContent(String content);
    Comment findByBookTitle(String title);
    List<Comment> findAll();
}
