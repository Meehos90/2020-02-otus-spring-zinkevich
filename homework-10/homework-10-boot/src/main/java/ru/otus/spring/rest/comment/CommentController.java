package ru.otus.spring.rest.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.model.Comment;
import ru.otus.spring.service.comment.CommentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@SuppressWarnings("all")
public class CommentController {

    private final CommentService commentService;

    @GetMapping(value = "/api/comments")
    public List<Comment> listComments() {
        return commentService.findAll();
    }

    @GetMapping("/api/comments/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable(value = "id") Long id) {
        Comment comment = commentService.findById(id);
        return ResponseEntity.ok().body(comment);
    }

    @PostMapping("/api/comments")
    public Comment createComment(@Valid @RequestBody Comment comment) {
        return commentService.save(comment);
    }

    @PutMapping("/api/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable(value = "id") Long id, @Valid @RequestBody Comment commentDetails) {
        final Comment updatedComment = commentService.update(commentDetails);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity deleteComment(@PathVariable(value = "id") Long id) {
        commentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
