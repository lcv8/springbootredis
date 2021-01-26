package com.springbootredis.cache;

import com.springbootredis.util.ApplicationContextUtils;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.DigestUtils;

//自定义redis缓存实现类
public class RedisCache implements Cache {

    //当前放入缓存的mapper.xml中的namespace
    private final String id;

    public RedisCache(String id){
        System.out.println("id========================" + id);//com.springbootredis.dao.UserDao
        this.id = id;
    }

    //返回cache的唯一标识
    @Override
    public String getId() {
        return this.id;
    }

    //缓存放入
    @Override
    public void putObject(Object key, Object value) {
        System.out.println( "key=======================>" +key);
        RedisTemplate redisTemplate = this.getRedisTemplate();

        //使用hash类型作为缓存模型  key -> namespace 也就是id， hashkey  ->  方法明 key    value   ->  返回值value
        redisTemplate.opsForHash().put(id , getKeyToMd5(key.toString()) , value);

    }

    //缓存中获取
    @Override
    public Object getObject(Object key) {
        RedisTemplate redisTemplate = this.getRedisTemplate();
        return redisTemplate.opsForHash().get(id,getKeyToMd5(key.toString()));
    }

    //根据指定的key删除缓存  这个为mybatis的保留方法  默认没有实现 。
    @Override
    public Object removeObject(Object key) {
        return null;
    }


    //清空缓存  只要是增删改就会默认调用这个方法
    @Override
    public void clear() {
        RedisTemplate redisTemplate = this.getRedisTemplate();
        //清空缓存
        redisTemplate.delete(id);
    }

    //计算缓存数量
    @Override
    public int getSize() {
        RedisTemplate redisTemplate = this.getRedisTemplate();
        //获取hash中key value的数量
        return redisTemplate.opsForHash().size(id).intValue();
    }

    //封装RedisTemplate
    private RedisTemplate getRedisTemplate(){
        //获得RedisTemplate
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        //序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    //对key进行MD5处理
    public String getKeyToMd5(String key){
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}
