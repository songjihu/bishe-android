package com.sjh.controller;

import com.sjh.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    @ResponseBody
    @RequestMapping("/upload")
    public Map<String, String> uploadaaa(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
        //String path = request.getSession().getServletContext().getRealPath("upload");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式  HH:mm:ss
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String path = "J:/var/uploaded_files/"+date+"/";
        UUID uuid=UUID.randomUUID();
        String originalFilename = file.getOriginalFilename();
        // String fileName = uuid.toString() + originalFilename;
        String extendName = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
        String fileName = uuid.toString() + extendName;
        File dir = new File(path, fileName);
        File filepath = new File(path);
        if(!filepath.exists()){
            filepath.mkdirs();
        }
        file.transferTo(dir);

        Map<String, String> map = new HashMap<>();
        map.put("filePath", path);
        map.put("fileName", fileName);

        return map;

    }

}
