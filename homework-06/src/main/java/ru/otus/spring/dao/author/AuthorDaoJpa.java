package ru.otus.spring.dao.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class AuthorDaoJpa implements AuthorDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery("select count(a) from Author a", Long.class);
        return query.getSingleResult();
    }

    @Override
    public void save(Author author) {
        if (author == null) {
            em.persist(author);
        } else {
            System.out.println(author.getId());
            em.merge(author);
        }
    }

    @Override
    public void updateFullNameById(Author author) {
        Query query = em.createQuery("update Author a " +
                "set a.fullName = :fullName " +
                "where a.id = :id");
        query.setParameter("fullName", author.getFullName());
        query.setParameter("id", author.getId());
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Author a " +
                "where a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public Author findByFullName(String fullName) {
        TypedQuery<Author> query = em.createQuery("select a " +
                        "from Author a " +
                        "where a.fullName = :fullName",
                Author.class);
        query.setParameter("fullName", fullName);
        return query.getSingleResult();
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("select a from authors a", Author.class);
        return query.getResultList();
    }
}
