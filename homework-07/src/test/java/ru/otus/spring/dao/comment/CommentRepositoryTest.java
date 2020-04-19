package ru.otus.spring.dao.comment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.spring.dao.book.BookRepository;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.otus.spring.dao.Constants.Comments.*;

@DisplayName("Dao для работы с комментариями")
@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BookRepository bookRepository;

    @DisplayName("возвращать ожидаемое количество комментариев")
    @Test
    void shouldReturnExpectedCommentCount() {
        long count = commentRepository.count();
        assertThat(count).isEqualTo(DEFAULT_COMMENTS_COUNT);
    }

    @DisplayName("добавлять комментарий к книге в БД")
    @Test
    void shouldSaveComment() {
        Comment expected = getComment(0, TEST_NEW_CONTENT);
        commentRepository.save(expected);
        Comment actual = commentRepository.findByContent(TEST_NEW_CONTENT);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("обновить комментарий по ID")
    @Test
    void shouldUpdateCommentById() {
        Comment expected = getComment(TEST_COMMENT_ID, TEST_CONTENT);
        commentRepository.save(expected);
        Comment actual = commentRepository.findById(TEST_COMMENT_ID);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("Удалить комментарий по ID")
    @Test
    void shouldDeleteCommentById() {
        commentRepository.deleteById(TEST_COMMENT_ID);
        Comment comment = commentRepository.findById(TEST_COMMENT_ID);
        assertNull(comment);
    }

    @DisplayName("Найти комментарий по ID")
    @Test
    void shouldFindCommentById() {
        Comment comment = commentRepository.findById(TEST_COMMENT_ID);
        assertThat(comment.getId()).isEqualTo(TEST_COMMENT_ID);
    }

    @DisplayName("найти комментарий по контенту")
    @Test
    void shouldReturnCommentByContent() {
        Comment comment = commentRepository.findByContent(TEST_CONTENT);
        assertThat(comment.getContent()).isEqualTo(TEST_CONTENT);
    }

    @DisplayName("получить комментарии БД по названию книги")
    @Test
    void shouldFindCommentsByBookTitle() {
        List<Comment> comments = commentRepository.findByBookTitle(TEST_BOOK_TITLE);
        for (Comment comment : comments) {
            assertThat(comment.getBook().getTitle()).isEqualTo(TEST_BOOK_TITLE);
        }
    }

    private Comment getComment(long id, String content) {
        Book book = bookRepository.findByTitle(TEST_BOOK_TITLE);
        return new Comment(id, content, book);
    }

}