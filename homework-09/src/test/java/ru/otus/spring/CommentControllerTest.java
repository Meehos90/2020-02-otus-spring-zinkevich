package ru.otus.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.dao.comment.CommentRepository;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;
import ru.otus.spring.rest.comment.CommentController;
import ru.otus.spring.service.book.BookService;
import ru.otus.spring.service.comment.CommentService;
import ru.otus.spring.service.message.MessageService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
public class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CommentService commentService;
    @MockBean
    private BookService bookService;
    @MockBean
    private MessageService messageService;
    @MockBean
    private CommentRepository commentRepository;

    @Test
    void shouldReturnAllComments() throws Exception {
        Pageable pageable = PageRequest.of(10, 1);
        List<Comment> comments = Arrays.asList(new Comment(), new Comment());
        Page<Comment> page = new PageImpl<>(comments, pageable, comments.size());
        given(commentRepository.findAll(pageable)).willReturn(page);

        mockMvc.perform(get("/comments?page=1&size=1"))
                .andExpect(status().isOk());
    }
}
