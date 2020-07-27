package com.example.demo;

import java.time.Duration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class GreetingWebClient {

  private WebClient client = WebClient.create("http://localhost:8080");

  public String getResult() {

    return client
        .get()
        .uri("/hello")
        .accept(MediaType.TEXT_PLAIN)
        .retrieve()
        .onStatus(
            HttpStatus::isError,
            clientResponse -> {
              throw new RuntimeException("Unable to retrieve information!!");
            })
        .bodyToMono(String.class)
        .timeout(Duration.ofMillis(1000))
        .doOnError(
            error -> {
              throw new RuntimeException(error);
            })
        .map(res -> res)
        .block();
  }
}
