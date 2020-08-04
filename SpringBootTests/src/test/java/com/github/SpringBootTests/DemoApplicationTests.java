package com.github.SpringBootTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DemoApplicationTests {

  @Test
  void contextLoads() {

    // (Given) some context
    Calculator calculator = new Calculator();

    // (When) some action is carried out
    int val = calculator.calc(1, 1);

    // (Then) a particular set of observable consequences should obtain
    Assertions.assertThat(val).isEqualTo(2);
  }

}
