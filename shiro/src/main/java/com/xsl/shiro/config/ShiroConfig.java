package com.xsl.shiro.config;

import com.xsl.shiro.shiro.CustomRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // 注入自定义realm,告诉shiro如何获取用户信息来做登录，或者权限控制
    @Bean
    public Realm realm(){
        return new CustomRealm();
    }

    /**
     * 这里统一做鉴权，即判断哪些请求路径需要用户登录，哪些请求不要需用户登录
     * 这里只要鉴权，不做权限控制，权限控制用注解来做
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition chain(){
        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();

        Map<String, String> map = new HashMap<>();
        map.put("/user/login", "anon");
        map.put("/page/401", "anon");
        map.put("/page/403", "anon");
        map.put("/t5/hello", "anon");
        map.put("/t5/guest", "anon");

        // 哪些请求可以匿名访问
        chain.addPathDefinitions(map);
        // 除了以上map定义的请求外，其余请求都需要登录
        chain.addPathDefinition("/**", "authc");
        return chain;
    }


    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug
         */
        creator.setUsePrefix(true);
        return creator;
    }
}
