package com.bench.bms.infra.residserviceimpl;

import com.bench.bms.infra.redisservice.RedisService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author bench
 * @Date 2024/06/20 15:50
 **/
@Service
public class RedisServiceImpl<T> implements RedisService<T> {

    @Resource
    private RedisTemplate<String, T> redisTemplate;

    @Override
    // set key value
    public void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    // setex key seconds value
    public void set(String key, T value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    @Override
    // get key
    public T get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    // del key

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    // del key1 key2 ...
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    @Override
    // expire key seconds
    public boolean expire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    @Override
    // ttl key
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    @Override  // exists key, 查询key值是否存在
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    // incr key
    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    // decr key
    public Long decrement(String key, long delta) {
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    @Override
    // sadd key value
    public void addSet(String key, T value) {
        redisTemplate.opsForSet().add(key, value);
    }

    @Override
    // smembers key
    public Set<T> getSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    // srem key value
    public void deleteSet(String key, T value) {
        redisTemplate.opsForSet().remove(key, value);
    }

    @Override
    // lpush key value
    public void lPush(String key, T value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    // lpop key
    public T lPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public void rPush(String key, T value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    @Override
    public T rPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    @Override
    // lrange key start stop
    public List<T> lRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    @Override
    // hset key field value
    public void hSet(String key, String hashKey, T value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    // hget key field
    public T hGet(String key, String hashKey) {
        return (T)redisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    // hdel key field1 [field2]
    public void hDelete(String key, String... hashKeys) {
        redisTemplate.opsForHash().delete(key, hashKeys);
    }

    @Override
    // hgetall key
    public Map<Object, Object> hGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    // hexists key field
    public boolean hHasKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    @Override
    // zadd key score member
    public void zAdd(String key, T value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    @Override
    // zrange key start stop
    public Set<T> zRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    @Override
    // zrem key member
    public void zRemove(String key, T value) {
        redisTemplate.opsForZSet().remove(key, value);
    }

    @Override
    // zcard key
    public Long zCard(String key) {
        return redisTemplate.opsForZSet().zCard(key);
    }

    @Override
    // rename key newkey
    public boolean renameKey(String oldKey, String newKey) {
        try {
            redisTemplate.rename(oldKey, newKey);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    // persist key
    public boolean persistKey(String key) {
        try {
            return redisTemplate.persist(key);
        }catch (Exception e){
            return false;
        }
    }

    @Override
    // set key value ex timeout unit
    public void setWithTimeout(String key, T value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    @Override
    // setnx key value
    public boolean setIfAbsent(String key, T value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    @Override
    // 执行Redis回调操作
    public T execute(RedisCallback<T> redisCallback) {
        return redisTemplate.execute(redisCallback);
    }
}
