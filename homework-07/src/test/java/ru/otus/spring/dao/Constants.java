package ru.otus.spring.dao;

public interface Constants {
    int EXPECTED_QERIES_COUNT = 1;

    interface Books {
        long DEFAULT_BOOKS_COUNT = 6L;
        long TEST_GENRE_ID = 1L;
        String TEST_BOOK_TITLE = "test_book_title";
        long UPDATE_TEST_BOOK = 3L;
        String TEST_AUTHOR_FULLNAME = "говард лавкрафт";
        String EXPECTED_TEST_BOOK_TITLE = "зов ктулху";
        String TEST_GENRE_NAME = "ужасы";
        long TEST_BOOK_ID = 3L;
        int EXPECTED_NUMBER_OF_BOOKS = 6;
    }

    interface Authors {
        long DEFAULT_AUTHORS_COUNT = 3L;
        int EXEPECTED_NUMBER_OF_AUTHORS = 3;
        String EXPECTED_AUTHOR_FULLNAME = "expected_author_fullname";
        long TEST_AUTHOR_ID = 3L;
        String TEST_AUTHOR_FULLNAME = "говард лавкрафт";
    }

    interface Genres {
        long DEFAULT_GENRES_COUNT = 3L;
        String EXPECTED_GENRE_NAME = "expected_genre_name";
        long TEST_GENRE_ID = 3L;
        long DEFAULT_COUNT_AFTER_DELETE = 2L;
        String TEST_GENRE_NAME = "ужасы";
        int EXEPECTED_NUMBER_OF_GENRES = 3;
    }

    interface Comments {
        String TEST_NEW_CONTENT = "тестовый комментарий";
        long DEFAULT_COMMENTS_COUNT = 12L;
        long TEST_COMMENT_ID = 6L;
        String TEST_CONTENT = "страшная книга";
        String TEST_BOOK_TITLE = "хребты безумия";
    }

}
