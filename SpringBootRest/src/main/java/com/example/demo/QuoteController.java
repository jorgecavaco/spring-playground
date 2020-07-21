package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class QuoteController {

  private final HttpClient client;
  private final RestTemplate restTemplate;
  private final String quotationServiceEndpoint;

  public QuoteController(
      RestTemplate restTemplate,
      HttpClient client,
      @Value("quotationServiceEndpoint") String quotationServiceEndpoint) {
    this.restTemplate = restTemplate;
    this.client = client;
    this.quotationServiceEndpoint = quotationServiceEndpoint;
  }

  @GetMapping("/v1/quote")
  public String getQuoteV1() {

    Quote response = restTemplate.getForObject(quotationServiceEndpoint, Quote.class);

    return response.getValue().getQuote();
  }

  @GetMapping("/v2/quote")
  public CompletableFuture<String> getQuoteV2() {

    HttpRequest request =
        HttpRequest.newBuilder(URI.create(quotationServiceEndpoint))
            .header("Content-Type", "application/json")
            .build();

    CompletableFuture<HttpResponse<String>> stringHttpResponse =
        client.sendAsync(request, BodyHandlers.ofString());

    return stringHttpResponse.handle(
        (v, t) -> {
          try {
            Quote response = new ObjectMapper().readValue(v.body(), Quote.class);
            return response.getValue().getQuote();
          } catch (Exception ignore) {
            throw new NotFoundException("Quote not found");
          }
        });
  }
}
