package com.example.spring_boot_sample_project.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;
@Aspect
@EnableAspectJAutoProxy
@Component
public class LoggingAspect {

    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //  get the arguments
        Object[] args = joinPoint.getArgs();

        logger.info("method called with: ");
        for (Object a : args) {
            logger.info("Argument: " + a);
        }

        Object greeterReturnValue = joinPoint.proceed();

        logger.info("method finished with result: " + greeterReturnValue);

        return greeterReturnValue;
    }
}
