package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.spring.service.TestingService;
import ru.otus.spring.service.UserService;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {
    private final UserService userService;
    private final TestingService testingService;
    private String userInfo;

    @ShellMethod(value = "Greeting command", key = {"g", "greeting"})
    public void greeting() {
        this.userInfo = userService.getUserInfo();
    }

    @ShellMethod(value = "Start student's testing", key = {"t", "test", "testing"})
    @ShellMethodAvailability(value = "isUserSetInfo")
    public void testing() {
        testingService.startTesting();
    }

    private Availability isUserSetInfo() {
        return userInfo == null? Availability.unavailable("Сначала представьтесь!"): Availability.available();
    }
}
