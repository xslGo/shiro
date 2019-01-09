package com.xsl.shiro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CrosConfig {

    private CorsConfiguration buildConfig(){
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");   //允许任何域名使用
        config.addAllowedHeader("*");   // 允许使用任何头
        config.addAllowedMethod("*");   // 允许使用任何方法 get,post等
        return config;
    }

    /**
     * 设置跨域请求
     * @return
     */
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}
