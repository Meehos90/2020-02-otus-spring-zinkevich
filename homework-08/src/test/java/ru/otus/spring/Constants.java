package ru.otus.spring;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Constants class");
    }
    
    public static final String TEST_AUTHOR = "стивен кинг";
    public static final String EXPECTED_AUTHOR = "expected_author";
    public static final String NEW_AUTHOR = "new_author";
    
    public static final String TEST_BOOK = "оно";
    public static final String EXPECTED_BOOK = "expected_book";
    public static final String NEW_BOOK = "new_book";
    
    public static final String TEST_GENRE = "ужасы";
    public static final String EXPECTED_GENRE = "expected_genre";
    public static final String NEW_GENRE = "new_genre";
    
    public static final String TEST_COMMENT = "страшная книга";
    public static final String EXPECTED_COMMENT = "expected_comment";
    public static final String NEW_COMMENT = "new_comment";
    
}
