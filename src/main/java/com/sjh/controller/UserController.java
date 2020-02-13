package com.sjh.controller;

import com.sjh.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController             //表示类下的所有方法返回值都是json
public class UserController {

    @Value("${jdbc.url}")
    private String jdbcUrl;//加载自定义参数
    //登录
    //@RequestMapping(value = "/login.do",method= RequestMethod.POST)
    //login.do/?username=xxx&password=xxx
    @RequestMapping(value = {"/login.do","/login"})//省略代表get和post,value为数组，多个都有效
    //@ResponseBody//表示返回json
    public UserInfo login(@RequestParam(value = "username",required = true,defaultValue = "275245802@qq.com") String username,//required默认为true
                          @RequestParam("password") String password){
        UserInfo userInfo=new UserInfo();
        userInfo.setId(1);
        userInfo.setUsername(username);
        userInfo.setPassword(password );

        return userInfo;//前缀+逻辑视图+后缀
    }

    @RequestMapping(value = {"/restful/login/{username}/{password}"})//省略代表get和post,value为数组，多个都有效
    //@ResponseBody//表示返回json
    public UserInfo loginRestluf(@PathVariable("username") String username,//路径参数
                                 @PathVariable("password") String password){
        UserInfo userInfo=new UserInfo();
        userInfo.setId(1);
        userInfo.setUsername(username);
        userInfo.setPassword(password );

        return userInfo;//前缀+逻辑视图+后缀
    }

    @RequestMapping(value = {"/test"})//省略代表get和post,value为数组，多个都有效
    //@ResponseBody//表示返回json
    public String test(){
       return jdbcUrl;
    }
}
