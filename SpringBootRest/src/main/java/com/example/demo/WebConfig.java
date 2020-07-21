package com.example.demo;

import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

  @Bean
  public HttpClient client() {
    return HttpClient.newBuilder().version(Version.HTTP_2).build();
  }
}
