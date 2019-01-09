package com.config;


import com.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public Map<String,String> filterChainDefinitionMap(){
        //配置资源的访问规则
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        //以下规则必须加，不能因为我们将静态资源放在了resources/static文件夹下就不用加
        filterChainDefinitionMap.put("/htmlTemplates/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/login.do", "anon");
        filterChainDefinitionMap.put("/getGIFCode.do", "anon");
        filterChainDefinitionMap.put("/authc/**", "authc");

        return filterChainDefinitionMap;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //配置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //资源的访问规则
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap());
        //指定认证失败后跳转的页面
        shiroFilterFactoryBean.setLoginUrl("login.html");
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        securityManager.setRememberMeManager(cookieRememberMeManager());
        return securityManager;
    }


    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm= new UserRealm();
        userRealm.setCredentialsMatcher(matcher());
        return userRealm;
    }

    @Bean
    public HashedCredentialsMatcher matcher(){
        HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");
        matcher.setHashIterations(1);
        return matcher;
    }

    @Bean
    public CookieRememberMeManager cookieRememberMeManager(){
        CookieRememberMeManager manager=new CookieRememberMeManager();
        manager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        manager.setCookie(simpleCookie());
        return manager;
    }

    @Bean
    public SimpleCookie simpleCookie(){
        SimpleCookie simpleCookie=new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(7*24*60*60);
        return simpleCookie;
    }

}
