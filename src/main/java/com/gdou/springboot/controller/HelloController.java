package com.gdou.springboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Array;
import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @RequestMapping("/success")
    public String success(Map<String,Object> map){
            map.put("hello","<h1>hello</h1>");
            map.put("users", Arrays.asList("zhansan","lisi","wangwu"));
            return "success";
    }

}
