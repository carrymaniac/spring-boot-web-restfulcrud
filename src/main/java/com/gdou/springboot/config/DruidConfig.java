package com.gdou.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRegistration;
import javax.sql.DataSource;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    /*
    采用这种手动创建DataSource的方式设置上Druid的其他参数
     */
    @Bean(initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid(){
        return new DruidDataSource();
    }

    //配置监控
    //1,配置一个后台的servlet
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> stringStringMap = new HashMap<>();
        stringStringMap.put("loginUsername","admin");
        stringStringMap.put("loginPassword","admin");
        servletRegistrationBean.setInitParameters(stringStringMap);
            return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean DruidFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        Map<String,String> stringStringMap = new HashMap<String,String>();
        stringStringMap.put("exclusions","*.js,*.css,/druid/*,*.json,*.gif,*.jpg,*.png,*.ico");

        filterRegistrationBean.setInitParameters(stringStringMap);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));

        return filterRegistrationBean;
    }


}
