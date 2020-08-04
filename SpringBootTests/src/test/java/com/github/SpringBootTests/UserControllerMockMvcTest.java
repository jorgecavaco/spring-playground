package com.github.SpringBootTests;

import com.github.SpringBootTests.controller.UserController;
import com.github.SpringBootTests.entities.User;
import com.github.SpringBootTests.repository.UserRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserController.class)
public class UserControllerMockMvcTest {

  @MockBean UserRepository userRepository;
  @Autowired private MockMvc mockMvc;

  @Test
  public void shouldReturnJohnDoe2WhenId1() throws Exception {
    User u = new User(1L, "John Doe2");
    Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(u));

    MvcResult result =
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/v1/user/{Number}", 1).param("a", "1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            // .andExpect(MockMvcResultMatchers.content().string(ArgumentMatchers.contains("John
            // Doe2")))
            .andReturn();

    Assertions.assertThat(result.getResponse().getContentAsString()).contains("John Doe2");
  }
}
