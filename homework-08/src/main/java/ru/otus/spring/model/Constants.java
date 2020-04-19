package ru.otus.spring.model;

public interface Constants {

    interface Books {
        String ENTER_BOOK_TITLE = "Введите название книги:";
        String BOOK_DELETED_SUCCESSFULLY = "Книга успешно удалена";
        String BOOK_UPDATE = "Книга успешно обновлена";
        String BOOK_SAVE = "Книга успешно добавлена";
        String BOOK_NOT_FOUND = "Книга не найдена";
    }

    interface Authors {
        String ENTER_AUTHOR_FULLNAME = "Введите полное имя автора:";
        String ENTER_NEW_AUTHOR_FULLNAME = "Введите новое полное имя автора";
        String AUTHOR_DELETED_SUCCESSFULLY = "Автор успешно удален вместе с книгами";
        String AUTHOR_UPDATE = "Имя автора успешно изменено";
        String AUTHOR_SAVE = "Автор успешно добавлен";
        String AUTHOR_NOT_FOUND = "Автор не найден";
    }

    interface Genres {
        String ENTER_GENRE_NAME = "Введите название жанра:";
        String GENRE_DELETED_SUCCESSFULLY = "Жанр успешно удален вместе с книгами";
        String GENRE_NOT_FOUND = "Жанр не найден";
    }

    interface Comments {
        String ENTER_COMMENT_CONTENT = "Введите ваш комментарий";
        String ENTER_ID_COMMENT = "Введите id коммента";
        String COMMENT_DELETED_SUCCESSFULLY = "Комментарий успешно удален";
        String COMMENT_UPDATE = "Комментарий успешно изменен";
        String COMMENT_SAVE = "Комментарий успешно добавлен";
        String COMMENT_NOT_FOUND = "Комментарий не найден";
    }
}
