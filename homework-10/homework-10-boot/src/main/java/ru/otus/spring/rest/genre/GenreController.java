package ru.otus.spring.rest.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.genre.GenreService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@SuppressWarnings("all")
public class GenreController {

    private final GenreService genreService;

    @GetMapping(value = "/api/genres")
    public List<Genre> listGenres() {
        return genreService.findAll();
    }

    @GetMapping("/api/genres/{id}")
    public ResponseEntity<Genre> getAuthorById(@PathVariable(value = "id") Long id) {
        Genre genre = genreService.findById(id);
        return ResponseEntity.ok().body(genre);
    }

    @PostMapping("/api/genres")
    public Genre createGenre(@Valid @RequestBody Genre genre) {
        return genreService.save(genre);
    }

    @PutMapping("/api/genres/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable(value = "id") Long id, @Valid @RequestBody Genre genreDetails) {
        final Genre updatedGenre = genreService.update(genreDetails);
        return ResponseEntity.ok(updatedGenre);
    }

    @DeleteMapping("/api/genres/{id}")
    public ResponseEntity deleteGenre(@PathVariable(value = "id") Long id) {
        genreService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
