package ru.otus.spring.service;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Constants class");
    }

    public static final String BOOK_NOT_FOUND = "Книга не найдена";
    public static final String AUTHOR_NOT_FOUND = "Автор не найден";
    public static final String GENRE_NOT_FOUND = "Жанр не найден";
    public static final String COMMENT_NOT_FOUND = "Комментарий не найден";
}
