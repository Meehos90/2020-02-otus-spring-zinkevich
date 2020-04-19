package ru.otus.spring.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.model.Author;
import ru.otus.spring.service.author.AuthorService;

import java.util.List;

@ShellComponent
public class AuthorCommands {
    @Autowired
    private AuthorService authorService;

    /*
    create author - cra
    update author - upa
    search authors by fullname - saf
    delete author - dea
    all authors - authors
    */

    @ShellMethod(value = "Insert author", key = {"cra"})
    private void createAuthor() {
        authorService.save();
    }

    @ShellMethod(value = "Update author", key = {"upa", "update author"})
    private void updateAuthor() {
        authorService.update();
    }

    @ShellMethod(value = "Search authors by fullname", key = {"saf", "author fullname"})
    private Author getAuthorByName() {
        return authorService.findByFullname();
    }

    @ShellMethod(value = "Delete author", key = {"dea", "delete author", "del auth"})
    private void deleteAuthor() {
        authorService.delete();
    }

    @ShellMethod(value = "View all authors", key = {"authors"})
    private List<Author> getAllAuthors() {
        return authorService.findAll();
    }
}
