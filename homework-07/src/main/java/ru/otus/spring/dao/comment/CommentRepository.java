package ru.otus.spring.dao.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findById(long id);

    Comment findByContent(String content);

    List<Comment> findByBookTitle(String title);
}
