package ru.otus.spring.config;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import ru.otus.spring.service.impl.localization.LocalizationServiceImpl;

@PropertySource("classpath:application.properties")
@Configuration
public class AppConfig {

    @Bean
    public LocalizationServiceImpl localizationService(@Value("${locale}") Locale locale,
                                                       @Value("#{${locale.lang.mapping}}") Map<String, String> languages) {
        return new LocalizationServiceImpl(locale, languages);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle/messages");
        ms.setDefaultEncoding("UTF-8");
        ms.setFallbackToSystemLocale(false);
        return ms;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
