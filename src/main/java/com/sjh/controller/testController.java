package com.sjh.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 文件上传
 */
@Controller
public class testController {

    /**
     * 登录页面
     * @return
     */
    @GetMapping("file1")
    public String file1() {
        // 这里返回的login指的是src/main/resources/templates目录下的login.html
        // 因此，我们需要在src/main/resources/templates目录下新建一个login.html
        // 当我们通过浏览器访问localhost:8080/login时即可访问到我们编写的login.html
        return "file1";
    }

}
