package com.example.demo.repositories;

import com.example.demo.entity.User;

public interface UserRepository {

  User getUserByUsername(String username);

}
