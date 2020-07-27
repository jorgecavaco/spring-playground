package com.example.demo.service;

import com.example.demo.repository.Dao1;
import com.example.demo.TrackTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Business1 {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private Dao1 dao1;

  @TrackTime
  public String calculateSomething() throws InterruptedException {

    String value = dao1.retrieveSomething();

    Thread.sleep(5);
    logger.info("In Business - {}", value);

    return value;
  }
}