package ru.otus.spring.service;

public class Constants {
    
    private Constants() {
        throw new IllegalStateException("Constants class");
    }
    
    public static final String ENTER_BOOK_TITLE = "Введите название книги:";
    public static final String BOOK_DELETED_SUCCESSFULLY = "Книга успешно удалена";
    public static final String BOOK_UPDATE = "Книга успешно обновлена";
    public static final String BOOK_SAVE = "Книга успешно добавлена";
    public static final String BOOK_NOT_FOUND = "Книга не найдена";
    
    public static final String ENTER_AUTHOR_FULLNAME = "Введите полное имя автора:";
    public static final String ENTER_NEW_AUTHOR_FULLNAME = "Введите новое полное имя автора";
    public static final String AUTHOR_DELETED_SUCCESSFULLY = "Автор успешно удален вместе с книгами";
    public static final String AUTHOR_UPDATE = "Имя автора успешно изменено";
    public static final String AUTHOR_SAVE = "Автор успешно добавлен";
    public static final String AUTHOR_NOT_FOUND = "Автор не найден";
    
    public static final String ENTER_GENRE_NAME = "Введите название жанра:";
    public static final String GENRE_DELETED_SUCCESSFULLY = "Жанр успешно удален вместе с книгами";
    public static final String GENRE_NOT_FOUND = "Жанр не найден";
    
    public static final String ENTER_COMMENT_CONTENT = "Введите ваш комментарий";
    public static final String ENTER_ID_COMMENT = "Введите id коммента";
    public static final String COMMENT_DELETED_SUCCESSFULLY = "Комментарий успешно удален";
    public static final String COMMENT_UPDATE = "Комментарий успешно изменен";
    public static final String COMMENT_SAVE = "Комментарий успешно добавлен";
    public static final String COMMENT_NOT_FOUND = "Комментарий не найден";
    
}
