package com.chenwenxing.springsecurityjwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {


    @RequestMapping("/admin")
    //hasAnyRole 只要满足其中一个就行,会自动加上Role
//    @PreAuthorize(value = "hasRole('admin11'and 'ADMIN')")
    public String admin(){
        return "admin";
    }
    //hasRole传入多个必须同时满足多个角色才能访问,会自动加上前缀Role
    @RequestMapping("/db")
//    @PreAuthorize(value = "hasRole('DB')")
    public String db(){
        return "db";
    }
    @RequestMapping("/user")
    //hasAuthority 不会加上前缀
//    @PreAuthorize(value = "hasAuthority('USER')")
    public String user(){
        return "user";
    }

}
