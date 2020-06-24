package ru.otus.spring.rest.author;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.model.Author;
import ru.otus.spring.service.author.AuthorService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@SuppressWarnings("all")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping(value = "/api/authors")
    public List<Author> listAuthors() {
        return authorService.findAll();
    }

    @GetMapping("/api/authors/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable(value = "id") Long id) {
        Author author = authorService.findById(id);
        return ResponseEntity.ok().body(author);
    }

    @PostMapping("/api/authors")
    public Author createAuthor(@Valid @RequestBody Author author) {
        return authorService.save(author);
    }

    @PutMapping("/api/authors/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable(value = "id") Long id, @Valid @RequestBody Author authorDetails) {
        final Author updatedAuthor = authorService.update(authorDetails);
        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("/api/authors/{id}")
    public ResponseEntity deleteAuthor(@PathVariable(value = "id") Long id) {
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
