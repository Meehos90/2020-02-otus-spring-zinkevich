package ru.otus.spring.service.genre;

import org.springframework.stereotype.Service;
import ru.otus.spring.exception.NoEntityException;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.AbstractService;

import java.util.List;

import static ru.otus.spring.service.Constants.GENRE_NOT_FOUND;

@Service
public class GenreServiceImpl extends AbstractService implements GenreService {


    @Override
    public void deleteById(Long id) {
        Genre genre = findById(id);
        genreRepository.deleteById(genre.getId());
    }

    @Override
    public Genre findById(Long id) {
        return genreRepository.findById(id).orElseThrow(() -> new NoEntityException(GENRE_NOT_FOUND));
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public boolean existsByName(String name) {
        return genreRepository.existsByName(name);
    }

    @Override
    public Genre edit(Long id, String name) {
        if (existsById(id)) {
            Genre genre = findById(id);
            return genreRepository.save(new Genre(genre.getId(), name));
        } else {
            return genreRepository.save(new Genre(0, name));
        }
    }

    @Override
    public boolean existsById(Long id) {
        if (id != null) {
            return genreRepository.existsById(id);
        }
        return false;
    }
}
