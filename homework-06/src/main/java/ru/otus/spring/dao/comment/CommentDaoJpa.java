package ru.otus.spring.dao.comment;

import org.springframework.stereotype.Repository;
import ru.otus.spring.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CommentDaoJpa implements CommentDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void save(Comment comment) {

    }

    @Override
    public void updateContentById(Comment comment) {

    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public Comment findById(long id) {
        return null;
    }

    @Override
    public Comment findByContent(String content) {
        return null;
    }

    @Override
    public Comment findByBookTitle(String title) {
        return null;
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }
}
