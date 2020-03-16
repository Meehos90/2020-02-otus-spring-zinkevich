package ru.otus.spring.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ru.otus.spring.logging.Logger;

import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

@Slf4j
@Component
@ConfigurationProperties("application")
public class LocalizationProperties {

    private final Locale locale;
    private final Map<String, String> languages;
    private final String csvFileName;

    public LocalizationProperties(Locale locale,
                                  Map<String, String> languages,
                                  String csvFileName) {
        this.locale = locale;
        this.languages = languages;
        this.csvFileName = csvFileName;
    }

    @Logger
    public Locale getLanguageLocale() {
        if(locale != null) {
            log.info("return current locale '{}'", locale);
            return locale;
        }
        log.info("return default locale '{}'", Locale.ENGLISH);
        return Locale.ENGLISH;
    }

    @Logger
    public String getCsvFile() {
        for (Entry<String, String> entry : languages.entrySet()) {
            String k = entry.getKey();
            if (locale != null && locale.toString().equals(k)) {
                return csvFileName.replace(".csv", "_" + k + ".csv");
            }
        }
        return csvFileName.replace(".csv", "_en_US.csv");
    }
}
