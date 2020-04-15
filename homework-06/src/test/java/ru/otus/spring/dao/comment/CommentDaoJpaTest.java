package ru.otus.spring.dao.comment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.book.BookDaoJpa;
import ru.otus.spring.exception.NoEntityException;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.otus.spring.dao.Constants.Authors.TEST_AUTHOR_ID;
import static ru.otus.spring.dao.Constants.Comments.*;

@DisplayName("Dao для работы с комментариями")
@DataJpaTest
@Import({CommentDaoJpa.class, BookDaoJpa.class})
class CommentDaoJpaTest {

    @Autowired
    private CommentDaoJpa commentDao;
    @Autowired
    private BookDaoJpa bookDao;

    @DisplayName("возвращать ожидаемое количество комментариев")
    @Test
    void shouldReturnExpectedCommentCount() {
        long count = commentDao.count();
        assertThat(count).isEqualTo(DEFAULT_COMMENTS_COUNT);
    }

    @DisplayName("добавлять комментарий к книге в БД")
    @Test
    void shouldSaveComment() {
        Comment expected = getComment(0, TEST_NEW_CONTENT);
        commentDao.save(expected);
        Comment actual = commentDao.findByContent(TEST_NEW_CONTENT);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("обновить комментарий по ID")
    @Test
    void shouldUpdateCommentById() {
        Comment expected = getComment(TEST_COMMENT_ID, TEST_CONTENT);
        commentDao.updateContentById(expected);
        Comment actual = commentDao.findById(TEST_COMMENT_ID);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("Удалить комментарий по ID")
    @Test
    void shouldDeleteCommentById() {
        commentDao.deleteById(TEST_COMMENT_ID);
        Throwable thrown = assertThrows(NoEntityException.class, () -> {
            commentDao.findById(TEST_COMMENT_ID);
        });
        assertNotNull(thrown.getMessage());
    }

    @DisplayName("Найти комментарий по ID")
    @Test
    void shouldFindCommentById() {
        Comment comment = commentDao.findById(TEST_COMMENT_ID);
        assertThat(comment.getId()).isEqualTo(TEST_COMMENT_ID);
    }

    @DisplayName("найти комментарий по контенту")
    @Test
    void shouldReturnCommentByContent() {
        Comment comment = commentDao.findByContent(TEST_CONTENT);
        assertThat(comment.getContent()).isEqualTo(TEST_CONTENT);
    }

    @DisplayName("получить комментарии БД по названию книги")
    @Test
    void shouldFindCommentsByBookTitle() {
        List<Comment> comments = commentDao.findByBookTitle(TEST_BOOK_TITLE);
        for(Comment comment : comments) {
            assertThat(comment.getBook().getTitle()).isEqualTo(TEST_BOOK_TITLE);
        }
    }

    private Comment getComment(long id, String content) {
        Book book = bookDao.findByTitle(TEST_BOOK_TITLE);
        return new Comment(id, content, book);
    }

}