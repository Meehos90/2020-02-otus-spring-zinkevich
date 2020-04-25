package ru.otus.spring.service.genre;

import org.springframework.stereotype.Service;
import ru.otus.spring.exception.NoEntityException;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.AbstractService;

import java.util.List;

import static ru.otus.spring.service.Constants.*;

@Service
public class GenreServiceImpl extends AbstractService implements GenreService {
    @Override
    public Genre fingGenreByName() {
        return findGenreByName();
    }

    @Override
    public void delete() {
        Genre genre = findGenreByName();
        genreRepository.deleteById(genre.getId());
        showMessage(GENRE_DELETED_SUCCESSFULLY);
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    private Genre findGenreByName() {
        String name = getMessage(ENTER_GENRE_NAME);
        Genre genre = genreRepository.findByName(name);
        if (genre == null) {
            throw new NoEntityException(GENRE_NOT_FOUND);
        }
        return genre;
    }
}
