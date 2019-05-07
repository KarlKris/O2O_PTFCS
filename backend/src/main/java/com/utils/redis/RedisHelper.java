package com.utils.redis;

/**
 * Redis缓存辅助类
 * @author:liyuanwen
 * @date: 2019/1/17 14:17
 * 基于Spring的RedisTemplate实现CacheManager接口，
 * 主要用于对缓存的基本操作，不用于分布式锁作用，
 * 此处的分布式锁实现不严谨
 **/

import org.apache.log4j.Logger;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public final class RedisHelper implements CacheManager {
    private static final Logger logger = Logger.getLogger(RedisHelper.class);
    private RedisSerializer<String> keySerializer;
    private RedisSerializer<Object> valueSerializer;
    private RedisTemplate<Serializable, Serializable> redisTemplate;
    private final Integer EXPIRE = 100;

    @SuppressWarnings("unchecked")
    public void setRedisTemplate(RedisTemplate<Serializable, Serializable> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.keySerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
        this.valueSerializer = (RedisSerializer<Object>) redisTemplate.getValueSerializer();
        CacheUtil.setCacheManager(this);
    }

    @Override
    public final Object get(final String key) {
        // 先过期
        expire(key, EXPIRE);
        // 后取值
        return redisTemplate.boundValueOps(key).get();
    }

    @Override
    public final Set<Object> getAll(final String pattern) {
        Set<Object> values = new HashSet<Object>();
        Set<Serializable> keys = redisTemplate.keys(pattern);
        for (Serializable key : keys) {
            expire(key.toString(), EXPIRE);
            values.add(redisTemplate.opsForValue().get(key));
        }
        return values;
    }

    @Override
    public final void set(final String key, final Serializable value, int seconds) {
        redisTemplate.boundValueOps(key).set(value);
        expire(key, seconds);
    }

    @Override
    public final void set(final String key, final Serializable value) {
        redisTemplate.boundValueOps(key).set(value);
        expire(key, EXPIRE);
    }

    @Override
    public final Boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public final void del(final String key) {
        redisTemplate.delete(key);
    }

    @Override
    public final void delAll(final String pattern) {
        redisTemplate.delete(redisTemplate.keys(pattern));
    }

    @Override
    public final String type(final String key) {
        expire(key, EXPIRE);
        return redisTemplate.type(key).getClass().getName();
    }

    @Override
    public final Boolean expire(final String key, final int seconds) {
        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }


    @Override
    public final Boolean expireAt(final String key, final long unixTime) {
        return redisTemplate.expireAt(key, new Date(unixTime));
    }

    @Override
    public final Long ttl(final String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    @Override
    public final void setrange(final String key, final long offset, final String value) {
        redisTemplate.boundValueOps(key).set(value, offset);
        expire(key, EXPIRE);
    }

    @Override
    public final String getrange(final String key, final long startOffset, final long endOffset) {
        expire(key, EXPIRE);
        return redisTemplate.boundValueOps(key).get(startOffset, endOffset);
    }

    @Override
    public final Object getSet(final String key, final Serializable value) {
        expire(key, EXPIRE);
        return redisTemplate.boundValueOps(key).getAndSet(value);
    }

    @Override
    public boolean setnx(String key, Serializable value) {
        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
        RedisConnection redisConnection = null;
        try {
            redisConnection = RedisConnectionUtils.getConnection(factory);
            if (redisConnection == null) {
                return redisTemplate.boundValueOps(key).setIfAbsent(value);
            }
            logger.info(keySerializer);
            logger.info(valueSerializer);
            return redisConnection.setNX(keySerializer.serialize(key), valueSerializer.serialize(value));
        } finally {
            RedisConnectionUtils.releaseConnection(redisConnection, factory);
        }
    }

    @Override
    public boolean lock(String key) {
        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
        RedisConnection redisConnection = null;
        try {
            redisConnection = RedisConnectionUtils.getConnection(factory);
            if (redisConnection == null) {
                return redisTemplate.boundValueOps(key).setIfAbsent("0");
            }
            return redisConnection.setNX(keySerializer.serialize(key), valueSerializer.serialize("0"));
        } finally {
            RedisConnectionUtils.releaseConnection(redisConnection, factory);
        }
    }

    @Override
    public void unlock(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void hset(String key, Serializable field, Serializable value) {
        redisTemplate.boundHashOps(key).put(field, value);
    }

    @Override
    public Object hget(String key, Serializable field) {
        return redisTemplate.boundHashOps(key).get(field);
    }

    @Override
    public void hdel(String key, Serializable field) {
        redisTemplate.boundHashOps(key).delete(field);
    }

    @Override
    public void sadd(String key, Serializable value) {
        redisTemplate.boundSetOps(key).add(value);
    }

    @Override
    public Set<?> sall(String key) {
        return redisTemplate.boundSetOps(key).members();
    }

    @Override
    public boolean sdel(String key, Serializable value) {
        return redisTemplate.boundSetOps(key).remove(value) == 1;
    }

    @Override
    public Long incr(String key) {
        return redisTemplate.boundValueOps(key).increment(1L);
    }

    /**
     *  以下是自己写的方法
     *
     **/
    @Override
    public void setList(String key, List list) {
        logger.info("正在存list。。。。。。。。。。");
        redisTemplate.opsForList().leftPushAll(key,list);
        expire(key, EXPIRE);
    }

    @Override
    public List getList(String key) {
        logger.info("正在取list...........");
        expire(key, EXPIRE);
        return redisTemplate.opsForList().range(key,0,-1);
    }
}