package ru.otus.spring.service.author;

import org.springframework.stereotype.Service;
import ru.otus.spring.exception.NoEntityException;
import ru.otus.spring.model.Author;
import ru.otus.spring.service.AbstractService;

import java.util.List;

import static ru.otus.spring.service.Constants.AUTHOR_NOT_FOUND;

@Service
public class AuthorServiceImpl extends AbstractService implements AuthorService {
    @Override
    public Author edit(Long id, String fullName) {
        if (existsById(id)) {
            Author author = findById(id);
           return authorRepository.save(new Author(author.getId(), fullName));
        } else {
           return authorRepository.save(new Author(0, fullName));
        }
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new NoEntityException(AUTHOR_NOT_FOUND));
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
