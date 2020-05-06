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
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@SuppressWarnings("all")
public class GenreController extends AbstractController {
    private final GenreService genreService;

    @GetMapping(value = "/genres")
    public String listGenres(Model model){
        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        model.addAttribute("title", messageService.getLocaleMessage("localized.genres"));
        return "genres";
    }

    @GetMapping("/addGenre")
    public String getAddGenre(Model model) {
        GenreForm genreForm = new GenreForm();
        model.addAttribute("genreForm", genreForm);
        model.addAttribute("title", messageService.getLocaleMessage("localized.add"));
        return "addGenre";
    }

    @GetMapping("/editGenre/{id}")
    public String getEditGenre(Model model, @PathVariable("id") Long id) {
        Genre genre = genreService.findById(id);
        GenreForm genreForm = new GenreForm();
        genreForm.setId(genre.getId());
        model.addAttribute("genre", genre);
        model.addAttribute("genreForm", genreForm);
        model.addAttribute("title", messageService.getLocaleMessage("localized.edit"));
        return "editGenre";
    }

    @PostMapping(path = {"/editGenre/{id}", "/addGenre"})
    public String postEditGenre(Model model, @ModelAttribute("genreForm") GenreForm genreForm, @PathVariable Optional<Long> id) {
        String name = genreForm.getName();
        if (genreService.existsByName(name)) {
            model.addAttribute("errorMessage", messageService.getLocaleMessage("localized.genreExists"));
            return "error";
        }
        if(id.isPresent()) {
            Genre genre = genreService.findById(id.get());
            model.addAttribute("genre", genre);
            genreService.update(genre.getId(), name);
        } else {
            genreService.add(name);
        }
        return "redirect:/genres";
    }

    @GetMapping("/deleteGenre/{id}")
    public String getDeleteGenre(Model model, @PathVariable("id") Long id) {
        Genre genre = genreService.findById(id);
        model.addAttribute("genre", genre);
        model.addAttribute("title", messageService.getLocaleMessage("localized.delete"));
        return "deleteGenre";
    }

    @PostMapping("deleteGenre/{id}")
    public String postDeleteGenre(Model model, @PathVariable Long id) {
        genreService.deleteById(id);
        model.addAttribute("title", messageService.getLocaleMessage("localized.delete"));
        return "redirect:/genres";
    }
}
