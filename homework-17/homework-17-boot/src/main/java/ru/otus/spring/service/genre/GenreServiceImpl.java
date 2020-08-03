package ru.otus.spring.service.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.genre.GenreRepository;
import ru.otus.spring.model.Genre;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public void deleteById(Long id) {
        Genre genre = findById(id);
        genreRepository.deleteById(genre.getId());
    }

    @Override
    public Genre findById(Long id) {
        return genreRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
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
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre update(Genre genreDetails) {
        Genre genre = findById(genreDetails.getId());
        genre.setName(genreDetails.getName());
        return genreRepository.save(genreDetails);
    }

    @Override
    public boolean existsById(Long id) {
        if (id != null) {
            return genreRepository.existsById(id);
        }
        return false;
    }
}
