package ru.otus.spring.rest.author;

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
import ru.otus.spring.service.author.AuthorService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@SuppressWarnings("all")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping(value = "/authors")
    public List<Author> listAuthors(Model model) {
        return authorService.findAll();
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable(value = "id") Long id) {
        Author author = authorService.findById(id);
        return ResponseEntity.ok().body(author);
    }

    @PostMapping("/authors")
    public Author createAuthor(@Valid @RequestBody Author author) {
        return authorService.save(author);
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable(value = "id") Long id, @Valid @RequestBody Author authorDetails) {
        final Author updatedAuthor = authorService.update(authorDetails);
        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("/authors/{id}")
    public Map<String, Boolean> deleteAuthor(@PathVariable(value = "id") Long id) {
        authorService.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
