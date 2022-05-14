package com.zhm.drug.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.zhm.drug.entity.User;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description
 * @Author kknever
 * @Date 2022/4/10
 **/
@Configuration
public class ShrioFilterConfiguration {
    /**
     *
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiro = new ShiroFilterFactoryBean();
        shiro.setSecurityManager(manager());
        /**
         * shiro 的内置过滤器，实现权限相关的过滤器
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/login", "anon");
        filterMap.put("/user/toRegister", "anon");//放行注册
        filterMap.put("/user/register", "anon");
        filterMap.put("/imserver/**", "anon");
        filterMap.put("/file/**", "anon");//上传下载
        filterMap.put("/**","authc");
        shiro.setFilterChainDefinitionMap(filterMap);
        //shiro.setLoginUrl("/login");
        //shiro.setSuccessUrl("/index");


        return shiro;
    }
    @Bean
    public DefaultWebSecurityManager manager(){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userRealm());
        return manager;
    }
    @Bean
    public UserRealm userRealm(){
        UserRealm realm = new UserRealm();
        return realm;
    }
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
