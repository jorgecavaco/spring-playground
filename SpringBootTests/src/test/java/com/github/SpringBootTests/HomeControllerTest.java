package com.github.SpringBootTests;

import com.github.SpringBootTests.controller.HomeController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HomeControllerTest {

  @Test
  public void homeEndpointShouldReturnSuccess() {

    HomeController homeController = new HomeController();

    String result = homeController.home();

    Assertions.assertThat(result).isNotBlank();
    Assertions.assertThat(result).isEqualTo("Hello World!");
  }
}
