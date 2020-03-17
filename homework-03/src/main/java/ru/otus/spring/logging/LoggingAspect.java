package ru.otus.spring.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("@annotation(ru.otus.spring.logging.Logger)")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Прокси : " + joinPoint.getThis().getClass().getName());
        log.info("Класс : " + joinPoint.getTarget().getClass().getName());
        log.info("Вызов метода : " + joinPoint.getSignature().getName());
    }
}
