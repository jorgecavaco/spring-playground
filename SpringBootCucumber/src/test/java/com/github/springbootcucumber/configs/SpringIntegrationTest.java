package com.github.springbootcucumber.configs;

import io.cucumber.spring.CucumberContextConfiguration;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringIntegrationTest {
  static ResponseResults latestResponse = null;

  protected RestTemplate restTemplate = new RestTemplate();

  void executeGet(String url) {
    final Map<String, String> headers = new HashMap<>();
    headers.put("Accept", "application/json");
    final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
    final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();

    restTemplate.setErrorHandler(errorHandler);
    latestResponse =
        restTemplate.execute(
            url,
            HttpMethod.GET,
            requestCallback,
            response -> {
              if (errorHandler.hadError) {
                return (errorHandler.getResults());
              } else {
                return (new ResponseResults(response));
              }
            });
  }

  void executePost(String url) {
    final Map<String, String> headers = new HashMap<>();
    headers.put("Accept", "application/json");
    final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
    final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();

    if (restTemplate == null) {
      restTemplate = new RestTemplate();
    }

    restTemplate.setErrorHandler(errorHandler);
    latestResponse =
        restTemplate.execute(
            url,
            HttpMethod.POST,
            requestCallback,
            response -> {
              if (errorHandler.hadError) {
                return (errorHandler.getResults());
              } else {
                return (new ResponseResults(response));
              }
            });
  }

  private static class ResponseResultErrorHandler implements ResponseErrorHandler {
    private ResponseResults results = null;
    private Boolean hadError = false;

    private ResponseResults getResults() {
      return results;
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
      hadError = response.getRawStatusCode() >= 400;
      return hadError;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
      results = new ResponseResults(response);
    }
  }
}
