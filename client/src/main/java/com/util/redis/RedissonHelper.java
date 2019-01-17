package com.util.redis;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.redisson.api.RBucket;
import org.redisson.api.RType;
import org.redisson.api.RedissonClient;

/**
 * Redis缓存辅助类
 * @author:liyuanwen
 * @date: 2019/1/17 14:23
 * 基于Redisson实现CacheManager接口，主要用于实现基于Redis的分布式锁
 **/
public class RedissonHelper implements CacheManager {
    private RedissonClient redissonClient;
    private final Integer EXPIRE = 600;
;
    public void setRedissonClient(Client client) {
        this.redissonClient = client.getRedissonClient();
        CacheUtil.setLockManager(this);
    }

    private RBucket<Object> getRedisBucket(String key) {
        return redissonClient.getBucket(key);
    }

    @Override
    public final Object get(final String key) {
        RBucket<Object> temp = getRedisBucket(key);
        expire(temp, EXPIRE);
        return temp.get();
    }

    @Override
    public final void set(final String key, final Serializable value) {
        RBucket<Object> temp = getRedisBucket(key);
        temp.set(value);
        expire(temp, EXPIRE);
    }

    @Override
    public final void set(final String key, final Serializable value, int seconds) {
        RBucket<Object> temp = getRedisBucket(key);
        temp.set(value);
        expire(temp, seconds);
    }

    public final void multiSet(final Map<String, Object> temps) {
        redissonClient.getBuckets().set(temps);
    }

    @Override
    public final Boolean exists(final String key) {
        RBucket<Object> temp = getRedisBucket(key);
        return temp.isExists();
    }

    @Override
    public final void del(final String key) {
        redissonClient.getKeys().delete(key);
    }

    @Override
    public final void delAll(final String pattern) {
        redissonClient.getKeys().deleteByPattern(pattern);
    }

    @Override
    public final String type(final String key) {
        RType type = redissonClient.getKeys().getType(key);
        if (type == null) {
            return null;
        }
        return type.getClass().getName();
    }

    /**
     * 在某段时间后失效
     *
     * @return
     */
    private final void expire(final RBucket<Object> bucket, final int seconds) {
        bucket.expire(seconds, TimeUnit.SECONDS);
    }

    /**
     * 在某个时间点失效
     *
     * @param key
     * @param unixTime
     * @return
     *
     */
    @Override
    public final Boolean expireAt(final String key, final long unixTime) {
        return redissonClient.getBucket(key).expireAt(new Date(unixTime));
    }

    @Override
    public final Long ttl(final String key) {
        RBucket<Object> rBucket = getRedisBucket(key);
        return rBucket.remainTimeToLive();
    }

    @Override
    public final Object getSet(final String key, final Serializable value) {
        RBucket<Object> rBucket = getRedisBucket(key);
        return rBucket.getAndSet(value);
    }

    @Override
    public Set<Object> getAll(String pattern) {
        Set<Object> set = new HashSet<Object>();
        Iterable<String> keys = redissonClient.getKeys().getKeysByPattern(pattern);
        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
            String key = iterator.next();
            set.add(getRedisBucket(key).get());
        }
        return set;
    }

    @Override
    public Boolean expire(String key, int seconds) {
        RBucket<Object> bucket = getRedisBucket(key);
        expire(bucket, seconds);
        return true;
    }

    @Override
    public void hset(String key, Serializable field, Serializable value) {
        redissonClient.getMap(key).put(field, value);
    }

    @Override
    public Object hget(String key, Serializable field) {
        return redissonClient.getMap(key).get(field);
    }

    @Override
    public void hdel(String key, Serializable field) {
        redissonClient.getMap(key).remove(field);
    }

    @Override
    public void sadd(String key, Serializable value) {
        redissonClient.getSet(key).add(value);
    }

    @Override
    public Set<Object> sall(String key) {
        return redissonClient.getSet(key).readAll();
    }

    @Override
    public boolean sdel(String key, Serializable value) {
        return redissonClient.getSet(key).remove(value);
    }

    @Override
    public boolean lock(String key) {
        return redissonClient.getLock(key).tryLock();
    }

    @Override
    public void unlock(String key) {
        redissonClient.getLock(key).unlock();
    }

    @Override
    public boolean setnx(String key, Serializable value) {
        try {
            return redissonClient.getLock(key).tryLock(Long.valueOf(value.toString()), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override
    public Long incr(String key) {
        return null;
    }

    @Override
    public void setrange(String key, long offset, String value) {
    }

    @Override
    public String getrange(String key, long startOffset, long endOffset) {
        return null;
    }


    /**
     *  以下自己写的方法
     *  分布式锁redisson还不懂  就不写
     **/
    @Override
    public void setList(String key, List list) {

    }

    @Override
    public List getList(String key) {
        return null;
    }
}
