package com.example.demo.config;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPointConfig {

  @Pointcut("execution(* com.example.demo.service.Business2.*(..))")
  public void dataLayerExecution(){}
}