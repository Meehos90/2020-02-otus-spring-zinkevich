package ru.otus.spring.dao;

public interface Constants {

    interface Books {
        long DEFAULT_BOOKS_COUNT = 6L;
        long TEST_AUTHOR_ID = 1L;
        long TEST_GENRE_ID = 1L;
        String TEST_BOOK_TITLE = "TEST_BOOK_TITLE";
        long UPDATE_TEST_BOOK = 3L;
        String TEST_AUTHOR_FULLNAME = "Говард Лавкрафт";
        String EXPECTED_TEST_BOOK_TITLE = "Зов Ктулху";
        String TEST_GENRE_NAME = "Ужасы";
        long DELETE_BOOK_ID = 6L;
        long DEFAULT_COUNT_AFTER_DELETE = 5L;
    }

    interface Authors {
        long DEFAULT_AUTHORS_COUNT = 3L;
        String EXPECTED_AUTHOR_FULLNAME = "EXPECTED_AUTHOR_FULLNAME";
        long TEST_AUTHOR_ID = 3L;
        long DEFAULT_COUNT_AFTER_DELETE = 2L;
        String TEST_AUTHOR_FULLNAME = "Говард Лавкрафт";
    }

    interface Genres {
        long DEFAULT_GENRES_COUNT = 3L;
        String EXPECTED_GENRE_NAME = "EXPECTED_GENRE_NAME";
        long TEST_GENRE_ID = 3L;
        long DEFAULT_COUNT_AFTER_DELETE = 2L;
        String TEST_GENRE_NAME = "Ужасы";
    }

    interface Comments {
        String TEST_NEW_CONTENT = "тестовый комментарий";
        long DEFAULT_COMMENTS_COUNT = 12L;
        long TEST_COMMENT_ID = 6L;
        long DEFAULT_COUNT_AFTER_DELETE = 11L;
        String TEST_CONTENT = "Страшная книга";
        String TEST_BOOK_TITLE = "Хребты безумия";
    }

}
