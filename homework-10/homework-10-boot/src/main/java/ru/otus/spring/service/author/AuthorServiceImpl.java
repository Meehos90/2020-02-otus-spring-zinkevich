package ru.otus.spring.service.author;

import org.springframework.stereotype.Service;
import ru.otus.spring.exception.EntityNotFoundException;
import ru.otus.spring.model.Author;
import ru.otus.spring.service.AbstractService;

import java.util.List;


@Service
public class AuthorServiceImpl extends AbstractService implements AuthorService {

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author update(Author authorDetails) {
        Author author = findById(authorDetails.getId());
        author.setFullName(authorDetails.getFullName());
        return authorRepository.save(authorDetails);
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public boolean existsByFullName(String fullName) {
        return authorRepository.existsByFullName(fullName);
    }

    @Override
    public boolean existsById(Long id) {
        if (id != null) {
            return authorRepository.existsById(id);
        }
        return false;
    }

    @Override
    public void deleteById(Long id) {
        Author author = findById(id);
        authorRepository.deleteById(author.getId());
    }
}
