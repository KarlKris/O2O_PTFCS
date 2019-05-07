package com.realm;

import com.model.PO.User;
import com.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;
import java.util.logging.Logger;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //执行授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        // 查到权限数据，返回授权信息(要包括 上边的permissions)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
        simpleAuthorizationInfo.addRole("user");

        return simpleAuthorizationInfo;
    }

    //执行认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken arg0) throws AuthenticationException {
        System.out.println("开始校验。。。");
        //1.得到令牌
        UsernamePasswordToken token = (UsernamePasswordToken) arg0;
        //2.调用业务组件进行登录判断
        String password = new String(token.getPassword());
        System.out.println(token.getUsername()+password);
        //这里只需要查询出对应用户即可
        User user = (User) userService.findOne(token.getUsername());
        //3.判断用户是否为空，如果不为空代表登录成功
        if (user != null) {
            //存在该用户则传入该用户的正确密码，会自动校验密码
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                    //priciple，使用当前登录用户对象
                    user,
                    //credentials
                    user.getPsw(),
                    //realmName
                    user.getName());
            //加盐
             simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getName()));
             return simpleAuthenticationInfo;
        }
        //方法返回Null代表验证失败
        return null;
    }

    public static void main(String[] args) {
        Md5Hash hash=new Md5Hash("123456","mm");
        System.out.println(UUID.randomUUID().toString());
    }

}
