package com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 读取application.properties属性文件的类
 * 也可以通过Environment的实例（@Autowired）getProperty()方法获取属性值
 * 用占位符读取配置文件一定要配置PropertyPlaceholderConfigurer
 * 或PropertySourcesPlaceholderConfigurer（Spring 3.1之后，现在用的就是这个）
 * @Value  ${}
 **/
@Configuration
@PropertySource("classpath:application.properties")
public class PropertiesConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.user}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver}")
    private String driver;

    @Value("${mybatis.type.alias.package}")
    private String mybatisTypeAliasPackage;

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private int redisPort;

    @Value("${redis.password}")
    private String redisPsw;

    @Value("${redis.timeout}")
    private int redisTimeOut;

    @Value("${redis.maxIdle}")
    private int redisMaxIdle;

    @Value("${redis.maxTotal}")
    private int redisMaxTotal;

    @Value("${redis.maxWait}")
    private Long redisMaxWait;

    @Value("${redis.testOnBorrow}")
    private Boolean redisTestOnBorrow;

    @Value("${redis.expiration}")
    private int redisExpiration;

    public int getRedisExpiration() {
        return redisExpiration;
    }

    public String getUrl() {
        return url;
    }

    public String getPassword() {
        return password;
    }

    public String getMybatisTypeAliasPackage() {
        return mybatisTypeAliasPackage;
    }

    public String getDriver() {
        return driver;
    }

    public String getUsername() {
        return user;
    }

    public String getRedisHost() {
        return redisHost;
    }

    public int getRedisPort() {
        return redisPort;
    }

    public String getRedisPsw() {
        return redisPsw;
    }

    public int getRedisTimeOut() {
        return redisTimeOut;
    }

    public int getRedisMaxIdle() {
        return redisMaxIdle;
    }

    public int getRedisMaxTotal() {
        return redisMaxTotal;
    }

    public Long getRedisMaxWait() {
        return redisMaxWait;
    }

    public Boolean getRedisTestOnBorrow() {
        return redisTestOnBorrow;
    }

    public PropertiesConfig(){
        System.out.println("实例化PropertiesConfig类.........");
    }
}
