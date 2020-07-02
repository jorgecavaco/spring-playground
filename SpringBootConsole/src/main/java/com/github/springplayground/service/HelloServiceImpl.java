package com.github.springplayground.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

  private final String message;

  @Value("${message.postfix}")
  private String messagePostfix;

  public HelloServiceImpl() {
    message = "Hello World";
  }

  public HelloServiceImpl(String message) {
    this.message = message;
  }

  @Override
  public void hello() {
    System.out.println(message + " " + messagePostfix);
  }
}
