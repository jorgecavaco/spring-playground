package com.example.demo.controller;

import com.example.demo.service.Business1;
import com.example.demo.service.Business2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExecutorController {

  @Autowired
  Business1 business1;

  @Autowired
  Business2 business2;

  @GetMapping("/exec/1")
  public String execute1() throws InterruptedException {
    return business1.calculateSomething();
  }

  @GetMapping("/exec/2")
  public String execute2() {
    return business2.calculateSomething("XXX");
  }

}
