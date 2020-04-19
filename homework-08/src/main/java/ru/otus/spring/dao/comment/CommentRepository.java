package ru.otus.spring.dao.comment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.model.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, Long> {
    long count();

    Comment save(Comment comment);

    void deleteById(long id);

    Comment findById(long id);

    @Query("select c from Comment c where c.content = :content")
    Comment findByContent(@Param("content") String content);

    @Query("select c from Comment c where c.book.title = :title")
    List<Comment> findByBookTitle(@Param("title") String title);

    List<Comment> findAll();
}
