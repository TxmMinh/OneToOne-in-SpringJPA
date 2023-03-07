package com.example.onetoonerestfulapi.service;

import com.example.onetoonerestfulapi.entity.User;

import java.util.List;

public interface UserService {
    User save(User user);
    User findById(Long id);

    List<User> findAll();

    void remove(Long id);

    void deleteAll();

}
