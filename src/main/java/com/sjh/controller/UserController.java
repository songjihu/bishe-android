package com.sjh.controller;

import com.sjh.pojo.UserInfo;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.File;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;
import org.apache.http.entity.ContentType;


//@RestController             //表示类下的所有方法返回值都是json
/**
 * 头像上传
 */
@Controller
public class UserController {

    @GetMapping(value = "/user2")
    public String file() {
        return "user2";
    }

    @PostMapping(value = "/avatarUpload")
    public String fileUpload(MultipartFile file1,
                             String userId,
                             HttpServletRequest request) {

        System.out.println(userId);
        String fileName = file1.getOriginalFilename();  // 文件名

        //String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名

        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "J://temp-rainy//user_avatar//"; // 上传后的路径
        //fileName = UUID.randomUUID() +suffixName; // 新文件名
        fileName = userId +suffixName; // 新文件名为用户id加原来的后缀
        //fileName=info.split(":")[4];
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file1.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "user2";
    }

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
