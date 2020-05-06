package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.dao.author.AuthorRepository;
import ru.otus.spring.dao.book.BookRepository;
import ru.otus.spring.dao.comment.CommentRepository;
import ru.otus.spring.dao.genre.GenreRepository;
import ru.otus.spring.service.message.MessageService;

public class AbstractService {
    @Autowired
    public BookRepository bookRepository;
    @Autowired
    public AuthorRepository authorRepository;
    @Autowired
    public GenreRepository genreRepository;
    @Autowired
    public CommentRepository commentRepository;
}
