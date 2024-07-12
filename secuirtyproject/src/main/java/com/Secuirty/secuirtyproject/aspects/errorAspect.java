package com.Secuirty.secuirtyproject.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(2)
@Component
public class errorAspect {

    private static final Logger logger = LoggerFactory.getLogger(errorAspect.class);

    @AfterThrowing(pointcut = "execution(* com.example.yourpackage..*.*(..))", throwing = "exception")
    public void logApiError(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        String errorMessage = exception.getMessage();

        logger.error("API method {} in class {} threw an exception: {}", methodName, className, errorMessage);
    }
}
