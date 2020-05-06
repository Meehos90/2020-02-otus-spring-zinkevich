package ru.otus.spring.service.author;

import org.springframework.stereotype.Service;
import ru.otus.spring.exception.NoEntityException;
import ru.otus.spring.model.Author;
import ru.otus.spring.service.AbstractService;

import java.util.List;

import static ru.otus.spring.model.Constants.Authors.AUTHOR_NOT_FOUND;

@Service
public class AuthorServiceImpl extends AbstractService implements AuthorService {
    @Override
    public void add(String fullName) {
        Author author = new Author(0, fullName);
        authorRepository.save(author);
    }

    @Override
    public Author update(Long id, String fullName) {
        Author author = findById(id);
        return authorRepository.save(new Author(author.getId(), fullName));
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
    public void deleteById(Long id) {
        Author author = findById(id);
        authorRepository.deleteById(author.getId());
    }
}
