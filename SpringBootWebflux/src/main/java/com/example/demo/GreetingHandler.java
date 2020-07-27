package com.example.demo;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class GreetingHandler {

  @Autowired IntService intService;

  public Mono<ServerResponse> hello(ServerRequest request) {

    return ServerResponse.ok()
        .contentType(MediaType.TEXT_PLAIN)
        .body(BodyInserters.fromValue("Hello, Spring!"));
  }

  public Mono<ServerResponse> getInts(ServerRequest request) {

    intService.getInts().reduce("", (a,b) -> a + "," + b).block();

    return ServerResponse.ok()
        .contentType(MediaType.TEXT_PLAIN)
        .body(
            BodyInserters.fromValue(
                intService.getInts().map(String::valueOf).collect(Collectors.joining(","))));
  }
}
