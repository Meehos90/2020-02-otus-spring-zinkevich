package ru.otus.spring.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* ru.otus.spring.service.impl.ConsoleTestingService.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Прокси : " + joinPoint.getThis().getClass().getName());
        System.out.println("Класс : " + joinPoint.getTarget().getClass().getName());
        System.out.println("Вызов метода : " + joinPoint.getSignature().getName());

    }

    @After("execution(* ru.otus.spring.service.impl.ConsoleTestingService.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("После вызова метода : " + joinPoint.getSignature().getName());
    }
}
