package com.zoudong.fileserver.config.filter.cors;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置处理
 * 解决前后端分离调用时跨域问题.注意安全风险,更细粒度的控制,可在方法上 @CrossOrigin(origins = "url")
 */
@Configuration
public class CorsConfigFilter {
    //@Autowired
    //private VhscProperties vhscProperties;
    @Bean
    public FilterRegistrationBean regCorsConfigFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        //config.addAllowedOrigin(vhscProperties.getAllowedOrigins());
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", corsConfiguration);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

}