package ru.otus.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;
import ru.otus.spring.rest.book.BookController;
import ru.otus.spring.service.book.BookService;
import ru.otus.spring.service.message.MessageService;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Тесты контроллера книг")
@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;
    @MockBean
    private MessageService messageService;

    private Author author;
    private Genre genre;
    private Book book1;
    private Book book2;
    private Book book3;

    public static final String BOOK_1 = "Оно";
    public static final String BOOK_2 = "Кладбище домашних животных";
    public static final String BOOK_3 = "Мизери";
    public static final String GENRE_NAME = "ужасы";
    public static final String AUTHOR_FULLNAME = "Стивен Кинг";
    public static final String PREVIOUS = "Предыдущая";
    public static final String NEXT = "Следующая";
    public static final String EDIT = "Изменить";
    public static final String BOOK_TITLE = "Название книги";
    public static final String BOOK_ID = "ID книги";
    public static final String ADD_BOOK = "Добавить книгу";
    public static final String DELETE = "Удалить";
    public static final String AUTHOR = "Автор";
    public static final String GENRE = "Жанр";


    @BeforeEach
    void setUp() {
        author = new Author(1, AUTHOR_FULLNAME);
        genre = new Genre(1, GENRE_NAME);
        book1 = new Book(1, BOOK_1, author, genre);
        book2 = new Book(2, BOOK_2, author, genre);
        book3 = new Book(3, BOOK_3, author, genre);
        when(bookService.findById(1L)).thenReturn(book1);
    }

    @DisplayName("Возвращаем страницу с книгами")
    @Test
    void shouldReturnAllBooks() throws Exception {
        List<Book> books = Arrays.asList(book1, book2, book3);
        Pageable pageable = PageRequest.of(1, 1);
        Page<Book> page = new PageImpl<>(books, pageable, books.size());
        when(bookService.findAll(pageable)).thenReturn(page);
        mockMvc.perform(get("/books?page=1&size=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(BOOK_1)))
                .andExpect(content().string(containsString(BOOK_2)))
                .andExpect(content().string(containsString(BOOK_3)))
                .andExpect(content().string(containsString(AUTHOR_FULLNAME)))
                .andExpect(content().string(containsString(GENRE_NAME)))
                .andExpect(content().string(containsString(PREVIOUS)))
                .andExpect(content().string(containsString(NEXT)));
    }

    @DisplayName("Возвращаем страницу с редактированием книги")
    @Test
    public void shouldReturnGetEditBook() throws Exception {
        mockMvc.perform(get("/editBook/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(EDIT)))
                .andExpect(content().string(containsString(BOOK_TITLE)))
                .andExpect(content().string(containsString(AUTHOR)))
                .andExpect(content().string(containsString(GENRE)));
    }

    @DisplayName("Отправляем post-запрос на редактирование книги")
    @Test
    public void shouldReturnPostEditBook() throws Exception {
        MvcResult result = mockMvc.perform(
                post("/editBook/{id}", 1)
                        .content(asJsonString(book1))
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isFound())
                .andReturn();
        assertThat(result.getResponse().getRedirectedUrl()).isEqualTo("/books");
        assertThat(jsonToBook(result.getRequest().getContentAsString())).isEqualTo(book1);
    }

    @DisplayName("Возвращаем страницу с добавлением книги")
    @Test
    public void shouldReturnGetAddBook() throws Exception {
        mockMvc.perform(get("/addBook"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(ADD_BOOK)))
                .andExpect(content().string(containsString(BOOK_TITLE)))
                .andExpect(content().string(containsString(AUTHOR)))
                .andExpect(content().string(containsString(GENRE)));
    }

    @DisplayName("Отправляем post-запрос на добавление книги")
    @Test
    public void shouldReturnPostAddBook() throws Exception {
        MvcResult result = mockMvc.perform(
                post("/addBook"))
                .andDo(print())
                .andExpect(status().isFound())
                .andReturn();
        assertThat(result.getResponse().getRedirectedUrl()).isEqualTo("/books");
    }

    @DisplayName("Возвращаем страницу с удалением книги")
    @Test
    public void shouldReturnGetDeleteBook() throws Exception {
        mockMvc.perform(
                get("/deleteBook/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content().string(containsString(DELETE)))
                .andExpect(content().string(containsString(BOOK_ID)))
                .andExpect(content().string(containsString(BOOK_TITLE)));
    }

    @DisplayName("Отправляем post-запрос на удаление книги")
    @Test
    public void shouldReturnPostDeleteBook() throws Exception {
        MvcResult result = mockMvc.perform(
                post("/deleteBook/{id}", 1)
                        .content(asJsonString(book1))
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isFound())
                .andReturn();
        assertThat(result.getResponse().getRedirectedUrl()).isEqualTo("/books");
        assertThat(jsonToBook(result.getRequest().getContentAsString())).isEqualTo(book1);
    }

    public static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Book jsonToBook(String jsonBook) {
        try {
            return new ObjectMapper().readValue(jsonBook, Book.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}