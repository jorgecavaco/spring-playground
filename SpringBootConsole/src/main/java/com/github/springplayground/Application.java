package com.github.springplayground;

import com.github.springplayground.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

  final HelloService helloService;

  public Application(@Autowired @Qualifier("otherHelloService") HelloService helloService) {
    this.helloService = helloService;
  }

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.run(args);
  }

  @Override
  public void run(String... args) {
    helloService.hello();
  }
}
