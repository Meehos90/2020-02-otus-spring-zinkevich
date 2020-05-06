package ru.otus.spring.service.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.otus.spring.model.Comment;

public interface CommentService {
    void add(String content, String bookTitle);

    Comment update(Long id, String content, String bookTitle);

    void deleteById(Long id);

    Page<Comment> findAll(Pageable pageable);

    Comment findById(Long id);
}
