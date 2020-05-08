package ru.otus.spring.rest.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.spring.model.Author;
import ru.otus.spring.rest.AbstractController;
import ru.otus.spring.service.author.AuthorService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@SuppressWarnings("all")
public class AuthorController extends AbstractController {
    private final AuthorService authorService;

    @GetMapping(value = "/authors")
    public String listAuthors(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "authors";
    }

    @GetMapping("/addAuthor")
    public String getAddAuthor(Model model) {
        AuthorForm authorForm = new AuthorForm();
        model.addAttribute("authorForm", authorForm);
        return "addAuthor";
    }

    @GetMapping("/editAuthor/{id}")
    public String getEditAuthor(Model model, @PathVariable("id") Long id) {
        Author author = authorService.findById(id);
        AuthorForm authorForm = new AuthorForm();
        authorForm.setId(author.getId());
        model.addAttribute("author", author);
        model.addAttribute("authorForm", authorForm);
        return "editAuthor";
    }

    @PostMapping(path = {"/editAuthor/{id}", "/addAuthor"})
    public String postEditAuthor(Model model, @ModelAttribute("authorForm") AuthorForm authorForm, @PathVariable(required = false) Long id) {
        String fullName = authorForm.getFullName();
        if (!authorService.existsByFullName(fullName)) {
            Author author = authorService.edit(id, fullName);
            model.addAttribute("author", author);
            return "redirect:/authors";
        }
        model.addAttribute("errorMessage", messageService.getLocaleMessage("localized.authorExists"));
        return "error";
    }

    @GetMapping("/deleteAuthor/{id}")
    public String getDeleteAuthor(Model model, @PathVariable("id") Long id) {
        Author author = authorService.findById(id);
        model.addAttribute("author", author);
        return "deleteAuthor";
    }

    @PostMapping("deleteAuthor/{id}")
    public String postDeleteBook(Model model, @PathVariable Long id) {
        authorService.deleteById(id);
        return "redirect:/authors";
    }
}
