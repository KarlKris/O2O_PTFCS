package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.io.IOException;

/**
 *  对应Spring-MVC-xml文件
 **/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.controller","com.service"}) //这里要扫描控制层
public class WebConfig extends WebMvcConfigurerAdapter{


    /**
     *  配置静态资源
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO Auto-generated method stub
        /**
         *  这句的意思是：
         *  将请求路径url ../static/js/**的资源
         *  映射到类路径（这里就是target里的classes）下的static/js文件内
         **/
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     *  配置multpart解析器
     **/
    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        return new StandardServletMultipartResolver();
    }

    /**
     *  配置HTML视图第1步：配置模板解析器
     **/
    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/view/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }


    /**
     *  配置HTML视图第2步：配置模板引擎
     **/
    @Bean
    public TemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    /**
     *  配置HTML视图第3步：配置视图解析器
     **/
    @Bean
    public ViewResolver viewResolver(TemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setContentType("text/html; charset=utf-8");
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }



//    /**
//     *  跨域访问支持，前后端分离开发时用得到
//     **/
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        // TODO Auto-generated method stub
//        registry.addMapping("*").allowedOrigins("*");
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:8080/Blog", "null")
//                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
//                .maxAge(3600)
//                .allowCredentials(true);
//    }

}
