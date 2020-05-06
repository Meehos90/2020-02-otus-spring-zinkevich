package ru.otus.spring.rest.comment;

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
import ru.otus.spring.model.Comment;
import ru.otus.spring.rest.AbstractController;
import ru.otus.spring.service.book.BookService;
import ru.otus.spring.service.comment.CommentService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@SuppressWarnings("all")
public class CommentController extends AbstractController {
    private final CommentService commentService;
    private final BookService bookService;

    @GetMapping("/comments")
    public String listBooks(@PageableDefault(size = 1) Pageable pageable, Model model) {
        Page<Comment> page = commentService.findAll(pageable);
        model.addAttribute("page", page);
        model.addAttribute("title", messageService.getLocaleMessage("localized.comments"));
        return "comments";
    }

    @GetMapping("/addComment")
    public String getAddComment(Model model) {
        CommentForm commentForm = new CommentForm();
        model.addAttribute("commentForm", commentForm);
        model.addAttribute("title", messageService.getLocaleMessage("localized.add"));
        return "addComment";
    }

    @GetMapping("/editComment/{id}")
    public String getEditComment(Model model, @PathVariable("id") Long id) {
        Comment comment = commentService.findById(id);
        CommentForm commentForm = new CommentForm();
        commentForm.setId(comment.getId());
        model.addAttribute("comment", comment);
        model.addAttribute("commentForm", commentForm);
        model.addAttribute("title", messageService.getLocaleMessage("localized.edit"));
        return "editComment";
    }

    @PostMapping(path = {"/editComment/{id}", "/addComment"})
    public String postEditComment(Model model, @ModelAttribute("commentForm") CommentForm commentForm, @PathVariable Optional<Long> id) {
        String content = commentForm.getContent();
        String bookTitle = commentForm.getBook();
        if(!bookService.existsByTitle(bookTitle)) {
            model.addAttribute("errorMessage", messageService.getLocaleMessage("localized.bookNotExists"));
            return "error";
        }
        if(id.isPresent()) {
            Comment comment = commentService.findById(id.get());
            model.addAttribute("comment", comment);
            commentService.update(comment.getId(), content, bookTitle);
        } else {
            commentService.add(content, bookTitle);
        }
        return "redirect:/comments";
    }

    @GetMapping("/deleteComment/{id}")
    public String getDeleteComment(Model model, @PathVariable("id") Long id) {
        Comment comment = commentService.findById(id);
        model.addAttribute("comment", comment);
        model.addAttribute("title", messageService.getLocaleMessage("localized.delete"));
        return "deleteComment";
    }

    @PostMapping("deleteComment/{id}")
    public String postDeleteComment(Model model, @PathVariable Long id) {
        commentService.deleteById(id);
        model.addAttribute("title", messageService.getLocaleMessage("localized.delete"));
        return "redirect:/comments";
    }
}
