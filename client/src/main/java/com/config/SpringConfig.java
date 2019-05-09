package com.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Spring的配置类 在SSM框架中对应spring-mybatis.xml
 **/

@Configuration  //表明此类是配置类，启动Spring容器
@ComponentScan //扫描自定义的组件,默认会扫描该类所在的包下所有的配置类
               //使用ApplicationContext的getBeanDefinitionNames()方法获取已经注册到容器中的 bean 的名称。
@PropertySource("classpath:application.properties")  //读取属性文件
@MapperScan(basePackages = {"com.mapper"})    //扫描Mybatis的Mapper接口
@EnableTransactionManagement  //开启事务管理
public class SpringConfig {

    public SpringConfig() {
        System.out.println("SpringConfig容器启动。。。。");
    }

    /**
     * 配置阿里druid数据源
     **/
    @Bean  //默认是单例创建 默认bean ID与方法名相同
    public DataSource dataSource(PropertiesConfig propertiesConfig){
        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setUsername(propertiesConfig.getUsername());
        druidDataSource.setPassword(propertiesConfig.getPassword());
        druidDataSource.setUrl(propertiesConfig.getUrl());
        druidDataSource.setDriverClassName(propertiesConfig.getDriver());
        return druidDataSource;
    }

    /**
     *  配置mybatis的SqlSessionFactoryBean
     **/
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource,PropertiesConfig propertiesConfig){
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        //分页插件pagehelper
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{new PageInterceptor()});
        //多模块扫描
        try{
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(propertiesConfig.getMapperLocations());
            sqlSessionFactoryBean.setMapperLocations(resources);
        }catch (IOException e){
            e.printStackTrace();
        }
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(propertiesConfig.getMybatisTypeAliasPackage());
        return sqlSessionFactoryBean;
    }

    /**
     *  配置spring的声明式事务
     **/
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
        return dataSourceTransactionManager;
    }

    /**
     * 引入配置文件（application.properties）时所要用到的类
     * 作用：将占位符指向的配置信息（如数据库）放在bean中定义的工具。
     **/
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }
}
