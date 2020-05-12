package ru.otus.spring.rest.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.spring.model.Book;
import ru.otus.spring.rest.AbstractController;
import ru.otus.spring.service.book.BookService;

@Controller
@RequiredArgsConstructor
@SuppressWarnings("all")
public class BookController extends AbstractController {
    private final BookService bookService;

    @GetMapping("/books")
    public String findAll(@PageableDefault(size = 1) Pageable pageable, Model model) {
        Page<Book> page = bookService.findAll(pageable);
        model.addAttribute("page", page);
        return "books";
    }

    @GetMapping("/addBook")
    public String getAddBook(Model model) {
        BookForm bookForm = new BookForm();
        model.addAttribute("bookForm", bookForm);
        return "addBook";
    }

    @GetMapping("/editBook/{id}")
    public String getEditBook(Model model, @PathVariable("id") Long id) {
        Book book = bookService.findById(id);
        BookForm bookForm = new BookForm();
        bookForm.setId(book.getId());
        model.addAttribute("book", book);
        model.addAttribute("bookForm", bookForm);
        return "editBook";
    }

    @PostMapping(path = {"/editBook/{id}", "/addBook"})
    public String postEditBook(Model model, @ModelAttribute("bookForm") BookForm bookForm, @PathVariable(required = false) Long id) {
        String title = bookForm.getTitle();
        String fullName = bookForm.getAuthor();
        String name = bookForm.getGenre();
        if (!bookService.existsByTitle(title)) {
            Book book = bookService.edit(id, title, fullName, name);
            model.addAttribute("book", book);
            return "redirect:/books";
        }
        model.addAttribute("errorMessage", messageService.getLocaleMessage("localized.bookExists"));
        return "error";
    }

    @GetMapping("/deleteBook/{id}")
    public String getDeleteBook(Model model, @PathVariable("id") Long id) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "deleteBook";
    }

    @PostMapping("deleteBook/{id}")
    public String postDeleteBook(Model model, @PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }

}
