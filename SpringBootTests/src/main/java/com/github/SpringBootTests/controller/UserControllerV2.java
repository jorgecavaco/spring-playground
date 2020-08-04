package com.github.SpringBootTests.controller;

import com.github.SpringBootTests.entities.User;
import com.github.SpringBootTests.repository.UserRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v2/")
public class UserControllerV2 {

  private final UserRepository userRepository;

  public UserControllerV2(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping(value = "user/{id}")
  public Mono<User> get(@PathVariable Long id) {
    return userRepository.findById(id).map(Mono::just).orElseThrow();
  }

  @DeleteMapping(value = "user/{id}")
  public Mono<Long> delete(@PathVariable Long id) {

    userRepository.findById(id).ifPresent(userRepository::delete);

    return Mono.just(id);
  }
}
