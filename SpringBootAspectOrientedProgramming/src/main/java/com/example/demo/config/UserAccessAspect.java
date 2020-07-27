package com.example.demo.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAccessAspect {

  private static Logger LOGGER = LoggerFactory.getLogger(UserAccessAspect.class);

  @Before("execution(* com.example.demo.service.*.*(..))")
  public void before(JoinPoint joinPoint) {
    LOGGER.info("Execute Before {}", joinPoint);
  }

  @After("execution(* com.example.demo.service.*.*(..))")
  public void after(JoinPoint joinPoint) {
    LOGGER.info("Execute After {}", joinPoint);
  }

  @AfterReturning(value = "execution(* com.example.demo.service.*.*(..))", returning = "result")
  public void after(JoinPoint joinPoint, Object result) {
    LOGGER.info("Execute After Returning {} {}", joinPoint, result);
  }

  @Around(value = "com.example.demo.config.CommonJoinPointConfig.dataLayerExecution()")
  public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    long start = System.currentTimeMillis();

    try {

      LOGGER.info("ARGS :: {}", proceedingJoinPoint.getArgs());

      return proceedingJoinPoint.proceed().toString().toUpperCase();
    } finally {

      LOGGER.info("Took {} ms", System.currentTimeMillis() - start);
    }
  }

  @Around(value = "@annotation(com.example.demo.TrackTime)")
  public Object trackTimeAnnotation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    long start = System.currentTimeMillis();

    try {
      return proceedingJoinPoint.proceed().toString().toUpperCase();
    } finally {
      LOGGER.info("Took {} ms", System.currentTimeMillis() - start);
    }
  }
}
