package com.github.SpringBootTests;

import com.github.SpringBootTests.controller.UserController;
import com.github.SpringBootTests.entities.User;
import com.github.SpringBootTests.repository.UserRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserControllerTest {

  @Test
  public void shouldReturnJohnDoe2WhenId1() {

    User u = new User(1L, "John Doe 2");

    UserRepository userRepository = Mockito.mock(UserRepository.class);
    Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(u));

    UserController userController = new UserController(userRepository);

    User result = userController.get(1L);

    Assertions.assertThat(result.getName()).isEqualTo("John Doe 2");
  }
}
