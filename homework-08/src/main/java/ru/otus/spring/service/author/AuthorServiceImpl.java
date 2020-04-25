package ru.otus.spring.service.author;

import org.springframework.stereotype.Service;
import ru.otus.spring.exception.NoEntityException;
import ru.otus.spring.model.Author;
import ru.otus.spring.service.AbstractService;

import java.util.List;

import static ru.otus.spring.service.Constants.*;

@Service
public class AuthorServiceImpl extends AbstractService implements AuthorService {
    @Override
    public void save() {
        String fullname = getMessage(ENTER_AUTHOR_FULLNAME);
        Author author = new Author( fullname);
        authorRepository.save(author);
        showMessage(AUTHOR_SAVE);
    }

    @Override
    public void update() {
        Author author = findAuthorbyFullName();
        String changeFullName = getMessage(ENTER_NEW_AUTHOR_FULLNAME);
        authorRepository.save(new Author(author.getId(), changeFullName));
        showMessage(AUTHOR_UPDATE + " " + changeFullName);
    }

    @Override
    public Author findByFullname() {
       return findAuthorbyFullName();
    }

    @Override
    public void delete() {
        Author author = findAuthorbyFullName();
        authorRepository.deleteById(author.getId());
        showMessage(AUTHOR_DELETED_SUCCESSFULLY);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    private Author findAuthorbyFullName() {
        String fullname = getMessage(ENTER_AUTHOR_FULLNAME);
        Author author = authorRepository.findByFullName(fullname);
        if(author == null) {
            throw new NoEntityException(AUTHOR_NOT_FOUND);
        }
        return author;
    }

}
