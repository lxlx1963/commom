package com.du.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Repository(value = "redisCacheDao")
public class RedisCacheDao {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private static long REDIS_DEFAULT_EXPIRE = 24 * 60 * 60 * 1000L;

    /**
     * 为了共享redis,默认使用项目名作为前缀
     */
    @Value("${spring.application.name}-")
    private String prefix;

    /**
     * 添加缓存
     *
     * @param cachename 缓存名
     * @param map       Map<String, Object>变量
     */
    public void putAllCache(String cachename, Map<Object, Object> map) {
        redisTemplate.opsForHash().putAll(prefix + cachename, map);
    }

    public void setCache(String key, Object value, long expireTime) {
        redisTemplate.opsForValue().set(prefix + key, value, expireTime, TimeUnit.MILLISECONDS);
    }

    public void setCache(String key, Object value) {
        setCache(key, value, REDIS_DEFAULT_EXPIRE);
    }

    public Object getCache(String key) {
        return redisTemplate.opsForValue().get(prefix + key);
    }

    /**
     * 添加缓存
     *
     * @param cachename 缓存名
     * @param key       缓存Key
     * @param val       缓存Value
     */
    public void putCache(String cachename, String key, Object val) {
        redisTemplate.opsForHash().put(prefix + cachename, key, val);
    }

    /**
     * 获取Value
     *
     * @param cachename 缓存名
     * @param key       缓存Key
     * @return Object
     */
    public Object getCache(String cachename, String key) {
        return redisTemplate.opsForHash().get(prefix + cachename, key);
    }

    /**
     * 获取缓存长度
     *
     * @param cachename 缓存名
     * @return 缓存Size
     */
    public Long getSize(String cachename) {
        return redisTemplate.opsForHash().size(prefix + cachename);
    }

    /**
     * 根据key删除缓存
     *
     * @param cachename 缓存名
     * @param key       缓存Key
     */
    public void deleteByKey(String cachename, Object key) {
        redisTemplate.opsForHash().delete(prefix + cachename, key);
    }

    /**
     * 根据key删除缓存
     *
     * @param key 缓存Key
     */
    public void deleteBykey(Object key) {
        redisTemplate.delete(prefix + key);
    }

    /**
     * 删除所有缓存
     *
     * @param cachename 缓存名
     */
    public void deleteAll(String cachename) {
        Set<Object> set = redisTemplate.opsForHash().keys(prefix + cachename);
        for (Object key : set) {
            redisTemplate.opsForHash().delete(prefix + cachename, key);
        }
    }

    /**
     * 获取缓存中的所有数据 不排序
     *
     * @param cachename 缓存名
     * @return Map<Object, Object>
     */
    public Map<Object, Object> getAllCache(String cachename) {
        return redisTemplate.opsForHash().entries(prefix + cachename);
    }

    /**
     * 获取缓存中的所有key值
     *
     * @param cachename
     * @return
     */
    public Set<Object> getAllKeys(String cachename) {
        return redisTemplate.opsForHash().keys(prefix + cachename);
    }

    /**
     * 获取所有缓存（已排序）
     *
     * @param cachename 缓存名
     * @return Map<Object, Object>类型返回值
     */
    public Map<Object, Object> getAllCacheOrder(String cachename) {
        Set<Object> keys = getAllKeys(prefix + cachename);
        if (keys.size() == 0) {
            return null;
        }
        Map<Object, Object> map = new TreeMap<Object, Object>();
        for (Object key : keys) {
            map.put(key, getCache(prefix + cachename, String.valueOf(key)));
        }
        return map;
    }

    /**
     * 设置缓存在多长时间之后过期
     *
     * @param cacheName
     * @param timeout
     * @param timeUnit
     * @return
     */
    public Boolean expire(String cacheName, Long timeout, TimeUnit timeUnit) {
        return redisTemplate.expire(prefix + cacheName, timeout, timeUnit);
    }

    /**
     * 缓存在某个固定时间点过期
     *
     * @param cacheName
     * @param date
     * @return
     */
    public Boolean expireAt(String cacheName, Date date) {
        return redisTemplate.expireAt(prefix + cacheName, date);
    }

    /**
     * 批量查询缓存for hash
     *
     * @param cacheName 缓存名
     * @param hashKeys  keys
     * @return
     */
    public List<Object> multiGetForHash(String cacheName, Collection<Object> hashKeys) {
        return redisTemplate.opsForHash().multiGet(prefix + cacheName, hashKeys);
    }

    /**
     * 批量查询缓存for hash 【使用管道，批量查询时公用一个连接】
     *
     * @param cacheName 缓存名
     * @param hashKeys  keys
     * @return
     */
    public List<Object> multiGetForHashWithPipelined(String cacheName, List<String> hashKeys) {
        return redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            for (String key : hashKeys) {
                final byte[] rawKey = rawKey(prefix + cacheName);
                final byte[] rawHashKey = rawKey(key);
                connection.hGet(rawKey, rawHashKey);
            }
            // 这里必须返回null
            return null;
        });
    }

    /**
     * 批量查询缓存for hash 【使用管道，批量查询时公用一个连接】
     *
     * @param cacheName 缓存名
     * @param dataMap   数据
     * @return
     */
    public List<Object> multiSetForHashWithPipelined(String cacheName, Map<String, Object> dataMap) {
        return redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                final byte[] rawKey = rawKey(prefix + cacheName);
                final byte[] rawHashKey = rawKey(entry.getKey());
                final byte[] rawValue = rawValue(entry.getValue());
                connection.hSet(rawKey, rawHashKey, rawValue);
            }
            // 这里必须返回null
            return null;
        });
    }

    @SuppressWarnings("unchecked")
    byte[] rawKey(Object key) {

        Assert.notNull(key, "non null key required");

        if (keySerializer() == null && key instanceof byte[]) {
            return (byte[]) key;
        }

        return keySerializer().serialize(key);
    }

    @SuppressWarnings("rawtypes")
    RedisSerializer keySerializer() {
        return redisTemplate.getKeySerializer();
    }

    @SuppressWarnings("unchecked")
    byte[] rawValue(Object value) {

        Assert.notNull(value, "value null key required");

        if (keySerializer() == null && value instanceof byte[]) {
            return (byte[]) value;
        }

        return ValueSerializer().serialize(value);
    }

    @SuppressWarnings("rawtypes")
    RedisSerializer ValueSerializer() {
        return redisTemplate.getValueSerializer();
    }

    /**
     * redis队列消费
     *
     * @param key
     * @param batchSize
     * @return
     */
    public List<Object> consumer(String key, int batchSize) {
        return redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            final byte[] rawKey = rawKey(prefix + key);
            for (int i = 0; i < batchSize; i++) {
                connection.rPop(rawKey);
            }
            return null;
        });
    }

    /**
     * 队列生产
     *
     * @param key
     * @param value
     */
    public void produce(String key, Object value) {
        redisTemplate.opsForList().leftPush(prefix + key, value);
    }

    /**
     * 批量生产数据
     *
     * @param key
     * @param values
     */
    public void produces(String key, List<?> values) {
        redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            final byte[] rawKey = rawKey(prefix + key);
            for (Object data : values) {
                byte[] rawValue = rawValue(data);
                connection.lPush(rawKey, rawValue);
            }

            return null;
        });
    }

    /**
     * 批量添加zset
     *
     * @param key    redis-key
     * @param tuples 需要添加的zset集合
     */
    public void addZset(String key, Set<ZSetOperations.TypedTuple<Object>> tuples) {
        redisTemplate.opsForZSet().add(prefix + key, tuples);
    }

    /**
     * 获取指定分数闭区间的zset
     *
     * @param key   redis-key
     * @param start 分数开始
     * @param end   分数结束
     * @return
     */
    public Set<Object> getZset(String key, Long start, Long end) {
        return redisTemplate.opsForZSet().rangeByScore(prefix + key, start, end);
    }

    /**
     * 根据关键字模糊查询所有Key
     *
     * @param pattern 关键字
     * @return
     */
    public Set<Object> getAllKeysByPattern(String pattern) {
        return redisTemplate.keys(prefix + pattern);
    }

    /**
     * 自增计数器，设置计数器的同时，且设置一个默认的超时时间。
     *
     * @param key
     * @param growthLength 步长，每次递增多少
     * @return
     */
    public Object setIncr(String key, Long growthLength) {
        return setIncr(prefix + key, growthLength, REDIS_DEFAULT_EXPIRE, TimeUnit.MILLISECONDS);
    }

    /**
     * 自增计数器
     * ** 防止key一直存在，这里设置了key的过期时间。
     * ** 程序调用方需要确保自身逻辑不受以下两点影响
     * ** 1、首次调用方法时，key不存在则会自动创建该key,计数设置完毕后，设置key传入的超时时间，
     * 这里不是一个【事务操作】，可能会出现计数成功，超时设置不成功（即缓存一直存在）的情况
     * ** 2、并发调用时可能导致多次设置超时时间
     *
     * @param key
     * @param growthLength 步长，每次递增多少
     * @return
     */
    public Object setIncr(String key, Long growthLength, Long timeout, TimeUnit timeUnit) {
        String cacheKey = prefix + key;
        // 判断是否已存在该key
        boolean hasKey = redisTemplate.hasKey(cacheKey);
        // 计数器
        Long increment = redisTemplate.opsForValue().increment(cacheKey, growthLength);
        if (!hasKey) {
            // 之前不存在该key(首次调用)，则设置一个超时时间
            expire(cacheKey, timeout, timeUnit);
        }
        return increment;
    }

    /**
     * 根据指定key删除缓存（任何数据类型）
     *
     * @param key
     * @return
     */
    public Boolean deleteByKey(String key) {
        return redisTemplate.delete(prefix + key);
    }

}
