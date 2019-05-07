package com.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * 对应web.xml,用来配置 servlet 上下文。
 * 需要重写三个方法。还可以做web.xml中的很多事
 * https://blog.csdn.net/classicer/article/details/50753019
 * AbstractAnnotationConfigDispatcherServletInitializer
 * 产生了一个 DispatcherServlet和一个ContextLoaderListener
 * DispatcherServlet开始启动时，会产生一个 Spring 应用程序上下文，把它和配置文件中声明的 bean 或者类一起加载进来。通过getServletConfigClasses() 方法，
 * 设置 DispatcherServlet 通过 WebConfig 配置类来完成 Spring 上下文和 bean 的加载。
 * 但是在 Spring web 程序中，往往还有另外一个应用程序上下文，它是由 ContextLoaderListener 产生的。
 * 通过调用 getRootConfigClasses()方法返回的类就是用来配置 ContextLoaderListener 产生的上下文。
 * 其中，DispatcherServlet 是用来加载涉及 web 功能的 beans，例如 controllers， view resolvers， 和 handler mappings；而 ContextLoaderListener 则是用来载入程序中其余的 beans，例如一些中间层和数据层组件，完成的是程序后端功能。
 **/
public class WebClientInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Autowired
    ShiroFilterFactoryBean shiroFilter;
    /**
     *  对应spring-mybatis.xml
     *  这个文件我们用SpringConfig类配置
     **/
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    /**
     *  对应Spring-MVC.xml文件
     *  这个文件我们用WebConfig类配置
     **/
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     *  将DispathcerServlet映射到"/"里
     **/
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     *  添加编码过滤器和shiro过滤器
     *  否则shiro可能会报UnavailableSecurityManagerException异常
     **/
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter=new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        //配置shiro过滤器
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");

        return new Filter[]{ characterEncodingFilter ,proxy};
    }

//    /**
//     *  指定multpart的配置，文件上传
//     *  指定文件保存位置
//     *  文件大小不超过2MB,整个请求不超过4MB
//     **/
//    @Override
//    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
//        registration.setMultipartConfig(
//                new MultipartConfigElement("D:\\Program Files\\Git\\GitRepository\\BlogSystemSSM\\client\\src\\main\\uploads",
//                        2097152,4194304,0)
//        );
//    }
}
