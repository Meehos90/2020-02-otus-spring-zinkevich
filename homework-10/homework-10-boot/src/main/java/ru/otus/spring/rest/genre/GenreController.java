package ru.otus.spring.rest.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.author.AuthorService;
import ru.otus.spring.service.genre.GenreService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@SuppressWarnings("all")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping(value = "/genres")
    public List<Genre> listGenres(Model model) {
        return genreService.findAll();
    }

    @GetMapping("/genres/{id}")
    public ResponseEntity<Genre> getAuthorById(@PathVariable(value = "id") Long id) {
        Genre genre = genreService.findById(id);
        return ResponseEntity.ok().body(genre);
    }

    @PostMapping("/genres")
    public Genre createGenre(@Valid @RequestBody Genre genre) {
        return genreService.save(genre);
    }

    @PutMapping("/genres/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable(value = "id") Long id, @Valid @RequestBody Genre genreDetails) {
        final Genre updatedGenre = genreService.update(genreDetails);
        return ResponseEntity.ok(updatedGenre);
    }

    @DeleteMapping("/genres/{id}")
    public Map<String, Boolean> deleteGenre(@PathVariable(value = "id") Long id) {
        genreService.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
