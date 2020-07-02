package com.github.springplayground.config;

import com.github.springplayground.service.HelloService;
import com.github.springplayground.service.HelloServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

  @Bean
  public HelloService otherHelloService() {
    return new HelloServiceImpl("Hey World");
  }

  @Bean("aohs")
  public HelloService anotherHelloService() {
    return new HelloServiceImpl("Bye Bye World");
  }
}
