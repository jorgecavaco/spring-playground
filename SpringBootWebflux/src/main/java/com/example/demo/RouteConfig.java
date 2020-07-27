package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouteConfig {

  @Bean
  public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {
    return RouterFunctions
        .route(RequestPredicates.GET("/hello"), greetingHandler::hello)
        .andRoute(RequestPredicates.GET("/getInts"), greetingHandler::getInts);
  }
}
