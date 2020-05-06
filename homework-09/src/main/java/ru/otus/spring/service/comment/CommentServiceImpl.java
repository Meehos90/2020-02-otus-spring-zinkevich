package ru.otus.spring.service.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.otus.spring.exception.NoEntityException;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;
import ru.otus.spring.service.AbstractService;

import static ru.otus.spring.model.Constants.Books.BOOK_NOT_FOUND;
import static ru.otus.spring.model.Constants.Comments.COMMENT_NOT_FOUND;

@Service
public class CommentServiceImpl extends AbstractService implements CommentService {

    @Override
    public void add(String content, String bookTitle) {
        Comment comment = new Comment(0, content, getBook(bookTitle));
        commentRepository.save(comment);
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new NoEntityException(COMMENT_NOT_FOUND));
    }

    @Override
    public Comment update(Long id, String content, String bookTitle) {
        Comment comment = findById(id);
        return commentRepository.save(new Comment(comment.getId(), content, getBook(bookTitle)));
    }

    @Override
    public void deleteById(Long id) {
        Comment comment = findById(id);
        commentRepository.deleteById(comment.getId());
    }

    @Override
    public Page<Comment> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    public Book getBook(String title) {
        Book book = bookRepository.findByTitle(title);
        if(book == null) {
            throw new NoEntityException(BOOK_NOT_FOUND);
        }
        return book;
    }
}
