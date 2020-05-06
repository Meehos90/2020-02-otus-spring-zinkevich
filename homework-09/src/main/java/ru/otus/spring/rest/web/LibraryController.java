package ru.otus.spring.rest.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
public class LibraryController {
    public static Locale locale;

    @RequestMapping("/")
    String index(HttpServletRequest request, Model model) {
        locale = request.getLocale();
        model.addAttribute("title", "Библиотека");
        return "library";
    }
}
