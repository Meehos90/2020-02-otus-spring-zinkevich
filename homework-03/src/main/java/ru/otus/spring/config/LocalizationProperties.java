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
@Data
public class LocalizationProperties {

    private Locale locale;
    private Map<String, String> languages;
    private String csvFileName;

    @Logger
    public Locale getLanguageLocale() {
        if(locale != null) {
            return locale;
        }
        return Locale.ENGLISH;
    }

    @Logger
    public String getCsvFile() {
        System.out.println(csvFileName);
        for (Entry<String, String> entry : languages.entrySet()) {
            String k = entry.getKey();
            if (locale != null && locale.toString().equals(k)) {
                return csvFileName.replace(".csv", "_" + k + ".csv");
            }
        }
        return csvFileName.replace(".csv", "_en_US.csv");
    }
}
