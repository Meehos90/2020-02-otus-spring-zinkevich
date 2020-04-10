package ru.otus.spring.dao.book;

import org.springframework.stereotype.Repository;
import ru.otus.spring.exception.NoEntityException;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
@Transactional
public class BookDaoJpa implements BookDao {

    @PersistenceContext
    private EntityManager em;

    private final static String SELECT_BOOKS = "select * from books b join authors a on b.author_id = a.id join genres g on b.genre_id = g.id";

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
        Query query = em.createQuery("update Book b " +
                "set b.title = :title, " +
                "b.author = :author, " +
                "b.genre = :genre " +
                "where b.id = :id");
        query.setParameter("title", book.getTitle());
        query.setParameter("author", book.getAuthor());
        query.setParameter("genre", book.getGenre());
        query.setParameter("id", book.getId());
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Book b " +
                "where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Book findByTitle(String title) {
        TypedQuery<Book> query = em.createQuery("select b " +
                        "from Book b " +
                        "where b.title = :title",
                Book.class);
        query.setParameter("title", title);
        return query.getSingleResult();
    }

    @Override
    public List<Book> findByAuthor(String fullname) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.author.fullName = :fullname", Book.class);
        query.setParameter("fullname", fullname);
        return query.getResultList();
    }

    @Override
    public List<Book> findByGenre(String name) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.genre.name = :name", Book.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }
}
