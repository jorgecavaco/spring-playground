package com.github.SpringBootTests.controller;

import com.github.SpringBootTests.entities.User;
import com.github.SpringBootTests.repository.UserRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class UserController {

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
  public User get(@PathVariable Long id) {
    return userRepository.findById(id).get();
  }
}
