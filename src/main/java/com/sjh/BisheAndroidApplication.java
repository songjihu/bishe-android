package com.sjh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//入口类注解
@SpringBootApplication
@MapperScan("com.sjh.dao")//指定生成实现类
public class BisheAndroidApplication {

    public static void main(String[] args) {
        SpringApplication.run(BisheAndroidApplication.class, args);
    }

}
