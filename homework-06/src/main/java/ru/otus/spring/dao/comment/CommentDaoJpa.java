package ru.otus.spring.dao.comment;

import org.springframework.stereotype.Repository;
import ru.otus.spring.exception.NoEntityException;
import ru.otus.spring.model.Comment;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CommentDaoJpa implements CommentDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery("select count(c) from Comment c", Long.class);
        return query.getSingleResult();
    }

    @Override
    public void save(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
        } else {
            em.merge(comment);
        }
    }

    @Override
    public void updateContentById(Comment comment) {
        em.merge(comment);
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Comment findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id)).orElseThrow(() -> new NoEntityException(String.valueOf(id)));
    }

    @Override
    public Comment findByContent(String content) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.content = :content", Comment.class);
        query.setParameter("content", content);
        return query.getSingleResult();
    }

    @Override
    public List<Comment> findByBookTitle(String title) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.book.title = :title", Comment.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c", Comment.class);
        return query.getResultList();
    }
}
