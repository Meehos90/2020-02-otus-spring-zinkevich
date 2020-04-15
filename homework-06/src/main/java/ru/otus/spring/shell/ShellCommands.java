package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.dao.author.AuthorDao;
import ru.otus.spring.dao.book.BookDao;
import ru.otus.spring.dao.comment.CommentDao;
import ru.otus.spring.dao.genre.GenreDao;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.IOService;

import java.util.List;

import static ru.otus.spring.shell.Constants.*;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final CommentDao commentDao;
    private final IOService messageService;

    /* START CRUD BOOKS
    create book - crb
    search book by name - sbn
    search book by author - sba
    search book by genre - sbn
    update book - upb
    delete book - deb
    all books - library
    */
    @ShellMethod(value = "Create book", key = {"crb", "book create"})
    public void createBook() {
        String title = getMessage(ENTER_BOOK_TITLE);
        Author author = getAuthor();
        bookDao.save(new Book(0, title, author, getGenre()));
        messageService.showMessage("Успешно добавлена книга: " + title);
    }

    @ShellMethod(value = "Search books by name", key = {"sbn", "search book by name"})
    public Book searchBookByName() {
        messageService.showMessage(ENTER_BOOK_TITLE);
        String value = messageService.getMessage();
        return bookDao.findByTitle(value);
    }

    @ShellMethod(value = "Search books by author", key = {"sba", "search book by author"})
    public List<Book> searchBookByAuthor() {
        messageService.showMessage(ENTER_AUTHOR_FULLNAME);
        String value = messageService.getMessage();
        return bookDao.findByAuthor(value);
    }

    @ShellMethod(value = "Search books by genre", key = {"sbg", "search book by genre"})
    public List<Book> searchBookByGenre() {
        messageService.showMessage(ENTER_GENRE_NAME);
        String value = messageService.getMessage();
        return bookDao.findByGenre(value);
    }

    @ShellMethod(value = "Update book", key = {"upb", "update book"})
    public void updateBook() {
        String title = getMessage(ENTER_BOOK_TITLE);
        Book book = bookDao.findByTitle(title);
        bookDao.updateBookById(new Book(book.getId(), title, getAuthor(), getGenre()));
        messageService.showMessage("Книга успешно изменена: " + title);
    }

    @ShellMethod(value = "Delete book by title", key = {"deb", "delete book"})
    public void deleteBook() {
        messageService.showMessage(ENTER_DELETE_BOOK_TITLE);
        String title = messageService.getMessage();
        Book book = bookDao.findByTitle(title);
        bookDao.deleteById(book.getId());
        messageService.showMessage("Книга \"" + title + "\" успешно удалена.");
    }

    @ShellMethod(value = "View all books", key = {"lib", "library"})
    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    /* END CRUD BOOKS */
    /* START CRUD AUTHORS
    create author - cra
    update author - upa
    search authors by fullname - saf
    delete author - dea
    all authors - authors
    */
    @ShellMethod(value = "Insert author", key = {"cra"})
    private void createAuthor() {
        String fullname = getMessage(ENTER_AUTHOR_FULLNAME);
        Author author = new Author(0, fullname);
        authorDao.save(author);
    }

    @ShellMethod(value = "Update author", key = {"upa", "update author"})
    public void updateAuthor() {
        String fullname = getMessage(ENTER_AUTHOR_FULLNAME);
        Author author = authorDao.findByFullName(fullname);
        String changeFullName = getMessage(ENTER_NEW_AUTHOR_FULLNAME);
        authorDao.updateFullName(new Author(author.getId(), changeFullName));
        messageService.showMessage("Автор успешно изменен: " + changeFullName);
    }

    @ShellMethod(value = "Search authors by fullname", key = {"saf", "author fullname"})
    public Author getAuthorByName() {
        messageService.showMessage(ENTER_AUTHOR_FULLNAME);
        String value = messageService.getMessage();
        return authorDao.findByFullName(value);
    }

    @ShellMethod(value = "Delete author", key = {"dea", "delete author", "del auth"})
    private void deleteAuthor() {
        messageService.showMessage(ENTER_AUTHOR_FULLNAME);
        String value = messageService.getMessage();
        Author author = authorDao.findByFullName(value);
        authorDao.deleteById(author.getId());
        messageService.showMessage("Автор \"" + value + "\" " + DELETED_SUCCESSFULLY);
    }

    @ShellMethod(value = "View all authors", key = {"authors"})
    public List<Author> getAllAuthors() {

        return authorDao.findAll();
    }
    /* END CRUD AUTHORS */

    /* START CRUD GENRES
    search genre by name - sgn
    delete genre - deg
    all genres - genres
    */
    @ShellMethod(value = "Search genres by name", key = {"sgn", "genre name"})
    public Genre getGenreByName() {
        messageService.showMessage(ENTER_GENRE_NAME);
        String value = messageService.getMessage();
        return genreDao.findByName(value);
    }

    @ShellMethod(value = "Delete genre", key = {"deg", "delete genre", "del genre"})
    public void deleteGenre() {
        messageService.showMessage(ENTER_GENRE_NAME);
        String value = messageService.getMessage();
        Genre genre = genreDao.findByName(value);
        genreDao.deleteById(genre.getId());
        messageService.showMessage("Жанр \"" + value + "\" " + DELETED_SUCCESSFULLY);
    }

    @ShellMethod(value = "View all genres", key = {"genres"})
    public List<Genre> getAllGenres() {
        return genreDao.findAll();
    }
    /* END CRUD GENRES */

    /* START CRUD COMMENTS
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
        messageService.showMessage(ENTER_BOOK_TITLE);
        Book book = bookDao.findByTitle(messageService.getMessage());
        messageService.showMessage(ENTER_COMMENT_CONTENT);
        commentDao.save(new Comment(0, messageService.getMessage(), book));
        messageService.showMessage("Комментарий успешно сохранен");
    }

    @ShellMethod(value = "Update comment by Id", key = {"upc"})
    public void updateComment() {
        messageService.showMessage(ENTER_ID_COMMENT);
        long id = Long.parseLong(messageService.getMessage());
        Comment comment = commentDao.findById(id);
        messageService.showMessage(ENTER_COMMENT_CONTENT);
        String content = messageService.getMessage();
        comment.setContent(content);
        commentDao.updateContentById(comment);
        messageService.showMessage("Комментарий успешно обновлен : " + content);
    }

    @ShellMethod(value = "Delete comment by Id", key = {"dec"})
    public void deleteComment() {
        messageService.showMessage(ENTER_ID_COMMENT);
        long id = Long.parseLong(messageService.getMessage());
        commentDao.deleteById(id);
        messageService.showMessage("Комментарий успешно удален : " + id);
    }

    @ShellMethod(value = "Search comment by Id", key = {"sci"})
    public Comment findComment() {
        messageService.showMessage(ENTER_ID_COMMENT);
        long id = Long.parseLong(messageService.getMessage());
        return commentDao.findById(id);
    }

    @ShellMethod(value = "Search comment by content", key = {"scc"})
    public Comment findCommentByContent() {
        messageService.showMessage(ENTER_COMMENT_CONTENT);
        String content = messageService.getMessage();
        return commentDao.findByContent(content);
    }

    @ShellMethod(value = "Search comment by book title", key = {"fcb"})
    public List<Comment> findCommentsByBookTitle() {
        messageService.showMessage(ENTER_BOOK_TITLE);
        String title = messageService.getMessage();
        return commentDao.findByBookTitle(title);
    }

    @ShellMethod(value = "View all comments", key = {"comments"})
    public List<Comment> getAllComments() {
        return commentDao.findAll();
    }

    /* END CRUD COMMENTS */

    /* UTILS */
    private String getMessage(String message) {
        messageService.showMessage(message);
        return messageService.getMessage();
    }

    private Genre getGenre() {
        String name = getMessage(ENTER_GENRE_NAME);
        Genre genre;
        try {
            genre = genreDao.findByName(name);
        } catch (EmptyResultDataAccessException e) {
            return insertAndReturnGenre(name);
        }
        return genre;
    }

    private Author getAuthor() {
        String fullname = getMessage(ENTER_AUTHOR_FULLNAME);
        Author author;
        try {
            author = authorDao.findByFullName(fullname);
        } catch (EmptyResultDataAccessException e) {
            return insertAndReturnAuthor(fullname);
        }
        return author;
    }

    private Genre insertAndReturnGenre(String name) {
        genreDao.save(new Genre(0, name));
        return genreDao.findByName(name);
    }

    private Author insertAndReturnAuthor(String fullname) {
        authorDao.save(new Author(0, fullname));
        return authorDao.findByFullName(fullname);
    }
}
