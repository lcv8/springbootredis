package com.hui;

import com.springbootredis.SpringbootredisApplication;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.TimeUnit;

//启动springboot应用
@SpringBootTest(classes = SpringbootredisApplication.class)
@RunWith(SpringRunner.class)
public class SpringbootRedis {

    //注入 StringRedisTemplate
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //操作redis字符串  opsForValue()  实际操作的就是string类型
    @Test
    public void testString(){
        stringRedisTemplate.opsForValue().set("age","18");  //set 用来设置一个key value
        String age = stringRedisTemplate.opsForValue().get("age");
        System.out.println("age -> " + age);
        stringRedisTemplate.opsForValue () .set ( "code" , "2357",120, TimeUnit.SECONDS);//设置一个key 超时时间
        stringRedisTemplate.opsForValue ( ) .append ( " name", "他是是一个好人,单纯少年!");//追加
    }

    //操作key
    @Test
    public void testStringkey(){
        stringRedisTemplate.delete("name");//删除一个key
        Boolean age1 = stringRedisTemplate.hasKey("age");//判断某个key是否存在
        DataType age = stringRedisTemplate.type("age");//value的类型
        Set<String> keys = stringRedisTemplate.keys("*");//获得所有的key
        keys.forEach(key -> System.out.println("key -> " + key));

        Long expire = stringRedisTemplate.getExpire ( "age");//获取key超时时间-1永不超时-2 key不存在>=0过期时间
        System.out.println (expire) ;
        stringRedisTemplate.randomKey();//在redis中随机获取一个key
        stringRedisTemplate.rename ( "age" , "age1"); //修改key名字要求key必须存在不存在报错//
        stringRedisTemplate.renameIfAbsent ( "name" , "name1");//修改key名字―判断key是否存在
        stringRedisTemplate.move ( "name1" ,1);//移动key到指定库
    }

    //操作list类型
    @Test
    public void tetsList(){
        stringRedisTemplate.opsForList().leftPush("names","xiaoc");//创建一个列表，放入一个元素
        stringRedisTemplate.opsForList().leftPushAll("names","xiaoc","xiaoz","xiaod");//创建一个列表，放入多个元素

        List<String> lists = new ArrayList<>();
        lists.add("xiaoxiao");
        lists.add("skjskf");
        stringRedisTemplate.opsForList().leftPushAll("names",lists);//创建一个列表，放入多个元素
        List<String> names = stringRedisTemplate.opsForList().range("names", 0, -1);
        names.forEach(name -> System.out.println("name -> " + name));
        stringRedisTemplate.opsForList().trim("names",1,3);//截取指定区间的list

    }

    //操作set类型
    @Test
    public void testSet(){
        stringRedisTemplate.opsForSet().add("sets","zhans","lis","wnagw");//创建set并放入多个元素
        Set<String> sets = stringRedisTemplate.opsForSet().members("sets"); //查看元素
        sets.forEach(set -> System.out.println("set -> " + set));
        Long size = stringRedisTemplate.opsForSet().size ("sets");//获取set集合元素个数
    }

    //操作zset类型
    @Test
    public void tsetzset(){
        stringRedisTemplate.opsForZSet().add("zsets","zhangs",10); //创建并放入元素
        Set<String> zsets = stringRedisTemplate.opsForZSet().range("zsets", 0, -1);//指定范围查询
        zsets.forEach(zset -> System.out.println("zset -> " + zset));
        Set<ZSetOperations.TypedTuple<String>> zsets1 = stringRedisTemplate.opsForZSet().rangeByScoreWithScores("zsets", 0, 150);//获取指定元素以及分数
        zsets1.forEach(typedTuple -> {
            System.out.println(typedTuple.getScore());
            System.out.println(typedTuple.getValue());
        });
    }

    //操作hash类型
    @Test
    public void tetsHash(){
        stringRedisTemplate.opsForHash().put("maps","name","zhangs");//创建一个hash类型，并放入key  value
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("age","12");
        hashMap.put("bir","2020-12-12");
        stringRedisTemplate.opsForHash().putAll("maps",hashMap);
        stringRedisTemplate.opsForHash().multiGet("maps", Arrays.asList("names","age"));//获取多个key的值
        Object o = stringRedisTemplate.opsForHash().get("maps", "name"); //获取hash中某个key的值
        stringRedisTemplate.opsForHash().values("maps"); //获取所有的值
        stringRedisTemplate.opsForHash().keys("maps"); //获取所有的key
    }
}
