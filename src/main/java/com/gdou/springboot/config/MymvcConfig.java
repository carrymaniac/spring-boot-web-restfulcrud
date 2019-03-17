package com.gdou.springboot.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 扩展Springmvc 既保留了原本的自动配置 也可以添加自己新的配置
 * 在spring5.0之后 ,WebMvcConfigurerAdapter已经被废弃,
 * 官方推荐直接继承WebMvcConfigurer接口进行操作
 * WebMvcAutoConfiguration中也使用了该接口
 *
 */
//@EnableWebMvc
@Configuration
public class MymvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/gdou").setViewName("success");
    }
}
