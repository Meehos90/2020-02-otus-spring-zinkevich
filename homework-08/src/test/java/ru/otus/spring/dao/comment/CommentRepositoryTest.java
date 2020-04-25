package ru.otus.spring.dao.comment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.dao.AbstractRepositoryTest;
import ru.otus.spring.dao.book.BookRepository;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.otus.spring.Constants.*;

@DisplayName("Dao для работы с комментариями")
public class CommentRepositoryTest extends AbstractRepositoryTest {
    
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BookRepository bookRepository;
    
    @DisplayName("возвращать ожидаемое количество комментариев")
    @Test
    void shouldReturnExpectedCommentCount() {
        long count = commentRepository.count();
        assertThat(count).isEqualTo(1);
    }
    
    @DisplayName("добавлять комментарий к книге в БД")
    @Test
    void shouldSaveComment() {
        Comment expected = getComment(NEW_COMMENT);
        commentRepository.save(expected);
        Comment actual = commentRepository.findByContent(NEW_COMMENT);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }
    
    @DisplayName("обновить комментарий по ID")
    @DirtiesContext
    @Test
    void shouldUpdateCommentById() {
        Comment expected = getComment(getCommentId(TEST_COMMENT));
        expected.setContent(EXPECTED_COMMENT);
        commentRepository.save(expected);
        Comment actual = commentRepository.findById(getCommentId(EXPECTED_COMMENT));
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }
    
    @DisplayName("Удалить комментарий по ID")
    @Test
    void shouldDeleteCommentById() {
        commentRepository.deleteById(TEST_COMMENT);
        Comment comment = commentRepository.findById(TEST_COMMENT);
        assertNull(comment);
    }
    
    @DisplayName("Найти комментарий по ID")
    @Test
    void shouldFindCommentById() {
        Comment comment = commentRepository.findById(getCommentId(TEST_COMMENT));
        assertThat(comment.getId()).isEqualTo(getCommentId(TEST_COMMENT));
    }
    
    @DisplayName("найти комментарий по контенту")
    @Test
    void shouldReturnCommentByContent() {
        Comment comment = commentRepository.findByContent(TEST_COMMENT);
        assertThat(comment.getContent()).isEqualTo(TEST_COMMENT);
    }
    
    @DisplayName("получить комментарии БД по названию книги")
    @Test
    void shouldFindCommentsByBookTitle() {
        List<Comment> comments = commentRepository.findByBookTitle(TEST_BOOK);
        for (Comment comment : comments) {
            assertThat(comment.getBook().getTitle()).isEqualTo(TEST_BOOK);
        }
    }
    
    private String getCommentId(String content) {
        Comment comment = commentRepository.findByContent(content);
        return comment.getId();
    }
    private Comment getComment(String content) {
        Book book = bookRepository.findByTitle(TEST_BOOK);
        return new Comment(content, book);
    }
}
