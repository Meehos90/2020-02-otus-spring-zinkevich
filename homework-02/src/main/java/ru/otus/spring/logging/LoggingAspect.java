package ru.otus.spring.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* ru.otus.spring.service.impl.ConsoleTestingService.*(..))")
    public Object logBefore(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("Прокси : " + joinPoint.getThis().getClass().getName());
        System.out.println("Класс : " + joinPoint.getTarget().getClass().getName());
        System.out.println("Вызов метода : " + joinPoint.getSignature().getName());

        Object res = joinPoint.proceed();
        System.out.println("После вызова метода : " + joinPoint.getSignature().getName());
        return res;
    }
}
