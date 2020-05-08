package ru.otus.spring.rest.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.rest.AbstractController;

@Controller
public class LibraryController extends AbstractController {

    @GetMapping("/")
    public String index(Model model) {
        return "library";
    }
}
