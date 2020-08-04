package com.github.SpringBootTests;

import com.github.SpringBootTests.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerMvcTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void shouldReturnJohnDoeWhenId1() throws JsonProcessingException {
    ResponseEntity<String> result =
        restTemplate.getForEntity("http://localhost:" + port + "/api/v1/user/1", String.class);

    ObjectMapper mapper = new ObjectMapper();

    Assertions.assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
    Assertions.assertThat(mapper.readValue(result.getBody(), User.class))
        .isEqualTo(new User(1L, "John Doe"));
  }
}
