package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.config.AppProps;

import java.time.Instant;

import static ru.otus.spring.config.JobConfig.*;

@RequiredArgsConstructor
@ShellComponent
public class BatchCommands {
    private final AppProps appProps;
    private final JobOperator jobOperator;

    @SneakyThrows
    @ShellMethod(value = "startMigrationJobWithJobOperator", key = "sm-jo")
    public void startMigrationJobWithJobOperator() {
        Long executionId = jobOperator.start(IMPORT_LIBRARY_JOB_NAME,
                INPUT_DB_NAME + "=" + appProps.getInputDBName() + "\n" +
                        OUTPUT_DB_NAME + "=" + appProps.getOutputDBName() + "\n" +
                        "Date=" + Instant.now());
    }
}
