package com.lbt.community_stu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "compackage com.lbt.community_stu.dao")
public class CommunityStuApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityStuApplication.class, args);
    }

}
