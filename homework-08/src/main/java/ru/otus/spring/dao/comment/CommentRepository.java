package ru.otus.spring.dao.comment;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.model.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, Long> {
    long count();

    Comment save(Comment comment);

    void deleteById(String id);

    Comment findById(String id);
    
    boolean existsById(String id);

    Comment findByContent(String content);

    List<Comment> findByBookTitle(String title);

    List<Comment> findAll();
}
