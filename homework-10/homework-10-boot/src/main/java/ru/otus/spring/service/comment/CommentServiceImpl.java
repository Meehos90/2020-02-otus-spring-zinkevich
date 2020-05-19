package ru.otus.spring.service.comment;

import org.springframework.stereotype.Service;
import ru.otus.spring.exception.EntityNotFoundException;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;
import ru.otus.spring.service.AbstractService;

import java.util.List;

@Service
public class CommentServiceImpl extends AbstractService implements CommentService {

    public Comment save(Comment comment) {
        return commentRepository.save(new Comment(0, comment.getContent(), getBook(comment.getBook().getTitle())));
    }

    @Override
    public Comment update(Comment comment) {
        return commentRepository.save(new Comment(comment.getId(), comment.getContent(), getBook(comment.getBook().getTitle())));
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public boolean existsById(Long id) {
        if (id != null) {
            return commentRepository.existsById(id);
        }
        return false;
    }

    @Override
    public void deleteById(Long id) {
        Comment comment = findById(id);
        commentRepository.deleteById(comment.getId());
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Book getBook(String title) {
        Book book = bookRepository.findByTitle(title);
        if (book == null) {
            throw new EntityNotFoundException();
        }
        return book;
    }
}
