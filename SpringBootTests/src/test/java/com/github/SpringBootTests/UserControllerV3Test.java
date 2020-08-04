package com.github.SpringBootTests;


import com.github.SpringBootTests.config.UserRouter;
import com.github.SpringBootTests.controller.UserHandler;
import com.github.SpringBootTests.entities.User;
import com.github.SpringBootTests.repository.UserRepository;
import java.util.Optional;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;


@ActiveProfiles("local")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={UserRouter.class, UserHandler.class})
@WebFluxTest
public class UserControllerV3Test {

  @MockBean
  UserRepository userRepository;

  @Autowired WebTestClient webTestClient;

  @Test
  public void testGetUserV2() {

    User user = new User(1, "user 1");

    Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

    webTestClient
        .get()
        .uri("/api/v3/user/1")
        // .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(User.class)
        .value(User::getName, IsEqual.equalTo("user 1"));
  }

  @Test
  public void testDeleteV2() {

    User user = new User(1, "user 1");
    Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

    Mockito.doNothing().when(userRepository).delete(user);
    // Mockito.doNothing().when(userRepository).delete(ArgumentMatchers.isA(User.class));

    webTestClient
        .delete()
        .uri("/api/v3/user/1")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(Long.class)
        .isEqualTo(1L);

    Mockito.verify(userRepository, Mockito.times(1)).delete(user);
  }
}
