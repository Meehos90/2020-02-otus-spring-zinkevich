package ru.otus.spring.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.genre.GenreService;

import java.util.List;

@ShellComponent
public class GenreCommands {
    @Autowired
    private GenreService genreService;

    /*
    search genre by name - sgn
    delete genre - deg
    all genres - genres
    */

    @ShellMethod(value = "Search genres by name", key = {"sgn", "genre name"})
    public Genre getGenreByName() {
        return genreService.fingGenreByName();
    }

    @ShellMethod(value = "Delete genre", key = {"deg", "delete genre", "del genre"})
    public void deleteGenre() {
        genreService.delete();
    }

    @ShellMethod(value = "View all genres", key = {"genres"})
    public List<Genre> getAllGenres() {
        return genreService.findAll();
    }
}
