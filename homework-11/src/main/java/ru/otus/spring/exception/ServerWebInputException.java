package ru.otus.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ServerWebInputException extends ResponseStatusException {

    public ServerWebInputException(HttpStatus status, String message) {
        super(status, message);
    }
}
