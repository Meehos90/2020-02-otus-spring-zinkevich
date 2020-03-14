package ru.otus.spring.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.logging.Logger;
import ru.otus.spring.service.IOService;

import java.util.Scanner;

@Slf4j
@Service
public class ConsoleIOService implements IOService {
    Scanner scanner = new Scanner(System.in);

    @Logger
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Logger
    @Override
    public String getMessage() {
        return scanner.nextLine();
    }
}
