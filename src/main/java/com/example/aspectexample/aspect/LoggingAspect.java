package com.example.aspectexample.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.example.aspectexample.service.UserServiceImpl.createUser(..))")
    public void before(JoinPoint joinPoint) {
        logger.info("Kullanıcı bilgilerini giriniz.");
    }

    @After("execution(* com.example.aspectexample.service.UserServiceImpl.createUser(..))")
    public void after(JoinPoint joinPoint) {
        logger.info("Kullanıcı oluşturuldu.");
    }

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Annotation kullanıldı.");
        return joinPoint.proceed();
    }
    @AfterThrowing("execution(* com.example.aspectexample.service.UserServiceImpl.getUser(..))")
    public void logAfterThrowing() {
        log.error("Error: User Not Found!");
    }
}
