package ru.otus.spring.logging;

import org.slf4j.event.Level;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Logger {
    Level value() default Level.DEBUG;
}
