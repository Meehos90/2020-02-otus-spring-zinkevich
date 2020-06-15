package ru.otus.spring.rest.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.model.Book;
import ru.otus.spring.service.book.BookService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@SuppressWarnings("all")
public class BookController {

    private final BookService bookService;

    @GetMapping(value = "/api/books")
    public List<Book> lisBooks() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println(userDetails.getUsername());
        return bookService.findAll();
    }

    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Long id) {
        Book book = bookService.findById(id);
        return ResponseEntity.ok().body(book);
    }

    @PostMapping("/api/books")
    public Book createBook(@Valid @RequestBody Book book) {
        return bookService.save(book);
    }

    @PutMapping("/api/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable(value = "id") Long id, @Valid @RequestBody Book bookDetails) {
        final Book updateBook = bookService.update(bookDetails);
        return ResponseEntity.ok(updateBook);
    }

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity deleteBook(@PathVariable(value = "id") Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
