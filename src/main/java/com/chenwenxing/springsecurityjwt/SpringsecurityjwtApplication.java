package com.chenwenxing.springsecurityjwt;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.chenwenxing.springsecurityjwt.dao"})
public class SpringsecurityjwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecurityjwtApplication.class, args);
    }

}
