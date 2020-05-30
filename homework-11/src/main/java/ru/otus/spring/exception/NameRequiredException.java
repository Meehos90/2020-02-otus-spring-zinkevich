package ru.otus.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NameRequiredException extends ResponseStatusException {

    public NameRequiredException(HttpStatus status, String message) {
        super(status, message);
    }
}
