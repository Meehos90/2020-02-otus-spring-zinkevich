package ru.otus.spring.dao.book;

import org.springframework.stereotype.Repository;
import ru.otus.spring.exception.NoEntityException;
import ru.otus.spring.model.Book;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
@Transactional
public class BookDaoJpa implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery("select count(b) from Book b", Long.class);
        return query.getSingleResult();
    }

    @Override
    public void save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
        } else {
            em.merge(book);
        }
    }

    @Override
    public void updateBookById(Book book) {
        em.merge(book);
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Book findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id)).orElseThrow(() -> new NoEntityException(String.valueOf(id)));
    }

    @Override
    public Book findByTitle(String title) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.title = :title", Book.class);
        query.setParameter("title", title);
        return query.getSingleResult();
    }

    @Override
    public List<Book> findByAuthor(String fullname) {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-entity-graph");
        return em.createQuery("select b from Book b where b.author.fullName = :fullname", Book.class)
                .setParameter("fullname", fullname)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getResultList();
    }

    @Override
    public List<Book> findByGenre(String name) {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-entity-graph");
        return em.createQuery("select b from Book b where b.genre.name = :name", Book.class)
                .setParameter("name", name)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getResultList();
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-entity-graph");
        return em.createQuery("select b from Book b", Book.class)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getResultList();
    }

}
