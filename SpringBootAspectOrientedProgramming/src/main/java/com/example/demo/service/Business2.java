package com.example.demo.service;

import com.example.demo.repository.Dao2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Business2 {

  @Autowired
  private Dao2 dao2;

  public String calculateSomething(String prefix) {
    //Business Logic
    return prefix + " " + dao2.retrieveSomething();
  }
}