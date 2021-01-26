package com.springbootredis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.springbootredis.dao")
public class SpringbootredisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootredisApplication.class, args);
    }

}
