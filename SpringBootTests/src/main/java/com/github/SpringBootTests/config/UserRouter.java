package com.github.SpringBootTests.config;

import com.github.SpringBootTests.controller.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

public class UserRouter {

  @Bean
  public RouterFunction<ServerResponse> userRoutes(UserHandler userhandler) {
    return RouterFunctions.route()
        .path(
            "/api/v3/user",
            builder -> builder.GET("/{id}", userhandler::get).DELETE("/{id}", userhandler::delete))
        .build();
  }
}
