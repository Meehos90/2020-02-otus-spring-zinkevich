package ru.otus.spring.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ru.otus.spring.logging.Logger;

import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

@Component
@ConfigurationProperties("localization")
@Slf4j
@Data
public class LocalizationProperties {
    private Locale locale;
    private Map<String, String> languages;
    private String csvFileName;
    private final Locale defLocale = Locale.ENGLISH;

    @Logger
    public Locale getLanguageLocale() {
        if(locale != null) {
            log.info("Locale is {}", locale);
            return locale;
        }
        log.info("Locale is empty, couse was called default locale = {}", defLocale);
        return defLocale;
    }

    @Logger
    public String getCsvFile() {
        for (Entry<String, String> entry : languages.entrySet()) {
            String k = entry.getKey();
            if (locale != null && locale.toString().equals(k)) {
                String currentCsvFileName = csvFileName.replace(".csv", "_" + k + ".csv");
                log.info("Csv file is {}", currentCsvFileName);
                return currentCsvFileName;
            }
        }
        return csvFileName.replace(".csv", "_en_US.csv");
    }
}
