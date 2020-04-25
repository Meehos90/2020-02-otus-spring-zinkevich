package ru.otus.spring.service.comment;

import org.springframework.stereotype.Service;
import ru.otus.spring.exception.NoEntityException;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;
import ru.otus.spring.service.AbstractService;

import java.util.List;

import static ru.otus.spring.service.Constants.*;

@Service
public class CommentServiceImpl extends AbstractService implements CommentService {
    @Override
    public void save() {
        Book book = getBook();
        String content = getMessage(ENTER_COMMENT_CONTENT);
        commentRepository.save(new Comment(content, book));
        showMessage(COMMENT_SAVE);
    }

    @Override
    public void update() {
        String id = existCommentById();
        Comment comment = commentRepository.findById(id);
        String content = getMessage(ENTER_COMMENT_CONTENT);
        comment.setContent(content);
        commentRepository.save(comment);
        showMessage(COMMENT_UPDATE + " " + content);
    }

    @Override
    public void delete() {
        String id = existCommentById();
        commentRepository.deleteById(id);
        showMessage(COMMENT_DELETED_SUCCESSFULLY + " " + id);
    }

    @Override
    public Comment findCommentById() {
        String id = existCommentById();
        return commentRepository.findById(id);
    }

    @Override
    public Comment findCommentByContent() {
        String content = getMessage(ENTER_COMMENT_CONTENT);
        Comment comment = commentRepository.findByContent(content);
        if (comment == null) {
            throw new NoEntityException(COMMENT_NOT_FOUND);
        }
        return comment;
    }

    @Override
    public List<Comment> findCommentByBookTitle() {
        Book book = getBook();
        List<Comment> comments = commentRepository.findByBookTitle(book.getTitle());
        if ((long) comments.size() == 0) {
            throw new NoEntityException(COMMENT_NOT_FOUND);
        }
        return comments;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    private String existCommentById() {
        String id = getMessage(ENTER_ID_COMMENT);
        if (!commentRepository.existsById(id)) {
            throw new NoEntityException(COMMENT_NOT_FOUND);
        }
        return id;
    }
}
