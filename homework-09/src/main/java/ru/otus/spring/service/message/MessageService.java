package ru.otus.spring.service.message;

public interface MessageService {
    String getLocaleMessage(String message, Object... objects);
}
