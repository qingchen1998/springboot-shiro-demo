package com.qingchen.shiro.studyshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qingchen.shiro.studyshiro.mapper")
public class StudyShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyShiroApplication.class, args);
    }

}
