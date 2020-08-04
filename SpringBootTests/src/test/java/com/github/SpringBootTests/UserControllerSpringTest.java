package com.github.SpringBootTests;

import com.github.SpringBootTests.controller.UserController;
import com.github.SpringBootTests.entities.User;
import com.github.SpringBootTests.repository.UserRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserControllerSpringTest {

  @Spy
  UserRepository userRepository;

  @InjectMocks
  UserController userController;

  @Test
  public void shouldReturnJohnDoe2WhenId1() {

    User u = new User(1L, "John Doe2");

    Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(u));

    User result = userController.get(1L);

    Assertions.assertThat(result.getName()).isEqualTo("John Doe2");
  }

  @Test
  public void shouldReturnJaneDoeWhenId2() {
    User u = new User(2L, "Jane Doe");

    Mockito.when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(u));

    User result = userController.get(2L);

    Assertions.assertThat(result.getName()).isEqualTo("Jane Doe");
  }
}
