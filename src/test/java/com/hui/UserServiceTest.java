package com.hui;

import com.springbootredis.service.UserService;
import com.springbootredis.SpringbootredisApplication;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootredisApplication.class)
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void userTest(){
//        Cache cache = new PerpetualCache();
        userService.findAll().forEach(user -> System.out.println("user -> " + user));
        System.out.println("=====================================================");
        userService.findAll().forEach(user -> System.out.println("user -> " + user));
    }

    @Test
    public void testMd5(){
        String key = "-448784778:4123651866:com.springbootredis.dao.UserDao.findAll:0:2147483647:select id ,name , bir , age from `user`:SqlSessionFactoryBean";

        //利用Spring框架提供MD5工具类
        String s = DigestUtils.md5DigestAsHex(key.getBytes());
        System.out.println(s);
    }

}
