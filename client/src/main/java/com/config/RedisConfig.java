package com.config;

import com.util.redis.Client;
import com.util.redis.RedisHelper;
import com.util.redis.RedissonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author:liyuanwen
 * @date: 2019/1/17 9:51
 **/
@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")  //读取属性文件
public class RedisConfig {

    @Autowired
    PropertiesConfig propertiesConfig;

    /**
     * jedis 配置
     **/
    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(propertiesConfig.getRedisMaxTotal());
        //最大空闲数
        jedisPoolConfig.setMaxIdle(propertiesConfig.getRedisMaxIdle());
        //最大建立连接等待时间
        jedisPoolConfig.setMaxWaitMillis(propertiesConfig.getRedisMaxWait());
        //是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        jedisPoolConfig.setTestOnBorrow(propertiesConfig.getRedisTestOnBorrow());
        return jedisPoolConfig;
    }

    /**
     * redis连接工厂
     **/
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        JedisConnectionFactory jedisConnectionFactory =
                new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(propertiesConfig.getRedisHost());
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig());
        jedisConnectionFactory.setPassword(propertiesConfig.getRedisPsw());
        jedisConnectionFactory.setPort(propertiesConfig.getRedisPort());
        jedisConnectionFactory.setTimeout(propertiesConfig.getRedisTimeOut());
        return jedisConnectionFactory;
    }

    /**
     * 缓存序列化方
     **/
    @Bean
    public StringRedisSerializer keySerializer(){
        return new StringRedisSerializer();
    }

    @Bean
    public GenericJackson2JsonRedisSerializer valueSerializer(){
        return new GenericJackson2JsonRedisSerializer();
    }

    @Bean
    public RedisTemplate redisTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setHashKeySerializer(keySerializer());
        redisTemplate.setHashValueSerializer(valueSerializer());
        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setValueSerializer(valueSerializer());
        return redisTemplate;
    }

    @Bean
    public RedisHelper redisHelper(){
        RedisHelper redisHelper=new RedisHelper();
        redisHelper.setRedisTemplate(redisTemplate());
        return redisHelper;
    }

    @Bean
    public RedisCacheManager redisCacheManager(){
        RedisCacheManager redisCacheManager =
                new RedisCacheManager(redisTemplate());
        redisCacheManager.setTransactionAware(true);
        redisCacheManager.setDefaultExpiration(propertiesConfig.getRedisExpiration());
        return redisCacheManager;

    }

    /**
     * 以下是分布式锁Redisson的配置（自定义类）
     * 适用场景：秒杀商品
     * 这个项目不适合  就是做下笔记
     **/
    @Bean
    public Client redisClient(PropertiesConfig propertiesConfig){
        Client client = new Client();
        client.setAddress("redis://"+propertiesConfig.getRedisHost()+":"+propertiesConfig.getRedisPort());
        client.setPassword(propertiesConfig.getRedisPsw());
        return client;
    }

    @Bean
    public RedissonHelper redissonHelper(PropertiesConfig propertiesConfig){
        RedissonHelper redissonHelper = new RedissonHelper();
        redissonHelper.setRedissonClient(redisClient(propertiesConfig));
        return redissonHelper;
    }

}
