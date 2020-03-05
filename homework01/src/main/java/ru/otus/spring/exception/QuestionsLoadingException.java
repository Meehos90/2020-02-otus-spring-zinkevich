package ru.otus.spring.exception;

public class QuestionsLoadingException extends RuntimeException {

    public QuestionsLoadingException(String message) {
        super(message);
    }

}
