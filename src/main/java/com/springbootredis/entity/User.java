package com.springbootredis.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class User implements Serializable {
    private String id;
    private Integer age;
    private String name;
    private Date bir;
}
