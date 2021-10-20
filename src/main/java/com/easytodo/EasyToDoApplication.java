package com.easytodo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = EasyToDoApplication.class)
@SpringBootApplication
@MapperScan("com.easytodo.mapper")
public class EasyToDoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyToDoApplication.class, args);
    }

}
