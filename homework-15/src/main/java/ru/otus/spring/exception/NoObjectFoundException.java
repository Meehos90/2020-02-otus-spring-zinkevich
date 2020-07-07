package ru.otus.spring.exception;

public class NoObjectFoundException extends RuntimeException {
    public NoObjectFoundException(String message) {
        super(message);
    }
}
