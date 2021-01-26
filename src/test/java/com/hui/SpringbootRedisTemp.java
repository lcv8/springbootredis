package com.hui;

import com.springbootredis.entity.User;
import com.springbootredis.SpringbootredisApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;


//启动springboot应用
@SpringBootTest(classes = SpringbootredisApplication.class)
@RunWith(SpringRunner.class)
public class SpringbootRedisTemp {

    //注入 RedisTemplate  key object  value object   序列化
    @Autowired
    private RedisTemplate redisTemplate;

    //opsFor***
    @Test
    public void testRedisTemp(){

        /**
         * RedisTemplate 对象中 key 和 value 的序列化都是 JdkSerializationRedisserializer
         *  但是我们希望
         *  key ：String
         *  value ： object
         *  就需要修改默认的序列化方案
         *  key ： StringRedisserializer
         * */


        //必须序列化
        User user = new User();
        //修改key 的序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        //修改 hash里面 key 的序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        user.setId(UUID.randomUUID().toString()).setName("xioac").setAge(23).setBir(new Date());
        redisTemplate.opsForValue().set("user",user);
        //hash的key序列化调正2个
        redisTemplate.opsForHash().put("mapssss","name",user);

        User user1 = (User) redisTemplate.opsForValue().get("user");
        System.out.println(user1);
    }

}
