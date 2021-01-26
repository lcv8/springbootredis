package com.springbootredis.service.impl;

import com.springbootredis.dao.UserDao;
import com.springbootredis.entity.User;
import com.springbootredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
