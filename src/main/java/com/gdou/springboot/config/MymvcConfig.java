package com.gdou.springboot.config;


import com.gdou.springboot.component.LoginHandlerInterceptor;
import com.gdou.springboot.component.MyLocaleReolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
            registry.addViewController("/").setViewName("login");
            registry.addViewController("/index.html").setViewName("login");
            registry.addViewController("/main.html").setViewName("dashboard");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //在springboot1.0时期静态资源会被springboot注册的拦截器负责静态映射了,在2.x时代,需要自己重新注册
            registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html","/","/user/login","/webjars/**","/asserts/**");
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleReolver();
    }

}
