package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @GetMapping("/greeting")
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    return new Greeting(counter.incrementAndGet(), String.format(template, name));
  }

  @DeleteMapping("/greeting")
  public String deleteGreeting() {
    return "DELETE";
  }

  @PostMapping(
      value = "/greeting",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String postGreeting(@RequestBody Greeting greeting) {

    System.out.println(greeting);
    return "POST";
  }

  @PutMapping("/greeting")
  public ResponseEntity<Void> putGreeting(@RequestBody Greeting greeting) {

    throw new RuntimeException("ERROR!!!!");

    // System.out.println(greeting);
    // return ResponseEntity.status(404).build();
  }
}
