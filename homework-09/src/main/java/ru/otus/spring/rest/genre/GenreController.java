package ru.otus.spring.rest.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.spring.model.Genre;
import ru.otus.spring.rest.AbstractController;
import ru.otus.spring.service.genre.GenreService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@SuppressWarnings("all")
public class GenreController extends AbstractController {
    private final GenreService genreService;

    @GetMapping(value = "/genres")
    public String listGenres(Model model) {
        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        return "genres";
    }

    @GetMapping("/addGenre")
    public String getAddGenre(Model model) {
        GenreForm genreForm = new GenreForm();
        model.addAttribute("genreForm", genreForm);
        return "addGenre";
    }

    @GetMapping("/editGenre/{id}")
    public String getEditGenre(Model model, @PathVariable("id") Long id) {
        Genre genre = genreService.findById(id);
        GenreForm genreForm = new GenreForm();
        genreForm.setId(genre.getId());
        model.addAttribute("genre", genre);
        model.addAttribute("genreForm", genreForm);
        return "editGenre";
    }

    @PostMapping(path = {"/editGenre/{id}", "/addGenre"})
    public String postEditGenre(Model model, @ModelAttribute("genreForm") GenreForm genreForm, @PathVariable(required = false) Long id) {
        String name = genreForm.getName();
        if (!genreService.existsByName(name)) {
            Genre genre = genreService.edit(id, name);
            model.addAttribute("genre", genre);
            return "redirect:/genres";
        }
        model.addAttribute("errorMessage", messageService.getLocaleMessage("localized.genreExists"));
        return "error";
    }

    @GetMapping("/deleteGenre/{id}")
    public String getDeleteGenre(Model model, @PathVariable("id") Long id) {
        Genre genre = genreService.findById(id);
        model.addAttribute("genre", genre);
        return "deleteGenre";
    }

    @PostMapping("deleteGenre/{id}")
    public String postDeleteGenre(Model model, @PathVariable Long id) {
        genreService.deleteById(id);
        return "redirect:/genres";
    }
}
