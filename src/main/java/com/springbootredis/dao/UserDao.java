package com.springbootredis.dao;

import com.springbootredis.entity.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
}
