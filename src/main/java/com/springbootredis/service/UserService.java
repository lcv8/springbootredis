package com.springbootredis.service;

import com.springbootredis.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
}
