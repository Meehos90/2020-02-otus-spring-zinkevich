package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.service.IOService;
import java.util.Scanner;

@Service
public class ConsoleIOService implements IOService {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public String getMessage() {
        return scanner.nextLine();
    }
}
