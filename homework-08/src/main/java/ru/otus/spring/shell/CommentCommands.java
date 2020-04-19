package ru.otus.spring.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.model.Comment;
import ru.otus.spring.service.comment.CommentService;

import java.util.List;

@ShellComponent
public class CommentCommands {
    @Autowired
    private CommentService commentService;

    /*
    update comment by id - upc
    create comment by book title - scb
    delete comment by id - dec
    search comment by id - sci
    search comment by content - scc
    search comments by book title - fcb
    view all comments - comments
    */

    @ShellMethod(value = "Save comment by book", key = {"scb"})
    public void createComment() {
        commentService.save();
    }

    @ShellMethod(value = "Update comment by Id", key = {"upc"})
    public void updateComment() {
        commentService.update();
    }

    @ShellMethod(value = "Delete comment by Id", key = {"dec"})
    public void deleteComment() {
        commentService.delete();
    }

    @ShellMethod(value = "Search comment by Id", key = {"sci"})
    public Comment findComment() {
        return commentService.findCommentById();
    }

    @ShellMethod(value = "Search comment by content", key = {"scc"})
    public Comment findCommentByContent() {
        return commentService.findCommentByContent();
    }

    @ShellMethod(value = "Search comment by book title", key = {"fcb"})
    public List<Comment> findCommentsByBookTitle() {
        return commentService.findCommentByBookTitle();
    }

    @ShellMethod(value = "View all comments", key = {"comments"})
    public List<Comment> getAllComments() {
        return commentService.findAll();
    }
}
