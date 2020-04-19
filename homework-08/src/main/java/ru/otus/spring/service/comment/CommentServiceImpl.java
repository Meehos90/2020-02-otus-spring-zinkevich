package ru.otus.spring.service.comment;

import org.springframework.stereotype.Service;
import ru.otus.spring.exception.NoEntityException;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;
import ru.otus.spring.service.AbstractService;

import java.util.List;

import static ru.otus.spring.model.Constants.Comments.*;

@Service
public class CommentServiceImpl extends AbstractService implements CommentService {
    @Override
    public void save() {
        Book book = getBook();
        String content = getMessage(ENTER_COMMENT_CONTENT);
        commentRepository.save(new Comment(0, content, book));
        showMessage(COMMENT_SAVE);
    }

    @Override
    public void update() {
        long id = existCommentById();
        Comment comment = commentRepository.findById(id);
        String content = getMessage(ENTER_COMMENT_CONTENT);
        comment.setContent(content);
        commentRepository.save(comment);
        showMessage(COMMENT_UPDATE + " " + content);
    }

    @Override
    public void delete() {
        long id = existCommentById();
        commentRepository.deleteById(id);
        showMessage(COMMENT_DELETED_SUCCESSFULLY + " " + id);
    }

    @Override
    public Comment findCommentById() {
        long id = existCommentById();
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

    private long existCommentById() {
        long id = Long.parseLong(getMessage(ENTER_ID_COMMENT));
        if (!commentRepository.existsById(id)) {
            throw new NoEntityException(COMMENT_NOT_FOUND);
        }
        return id;
    }
}
