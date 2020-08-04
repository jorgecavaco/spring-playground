package com.github.SpringBootTests.controller;

import com.github.SpringBootTests.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

  private final UserRepository userRepository;

  public UserHandler(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Mono<ServerResponse> get(ServerRequest request) {

    Long id = Long.valueOf(request.pathVariable("id"));

    return ServerResponse.ok().body(BodyInserters.fromValue(userRepository.findById(id).get()));
  }

  public Mono<ServerResponse> delete(ServerRequest request) {

    Long id = Long.valueOf(request.pathVariable("id"));
    userRepository.findById(id).ifPresent(userRepository::delete);

    return ServerResponse.ok().body(BodyInserters.fromValue(id));
  }
}
