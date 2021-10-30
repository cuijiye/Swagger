package com.example.swagger.controller;

import com.example.swagger.pojo.User;
import com.example.swagger.service.AsyncService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @Autowired
    AsyncService asyncService;

    //只要我们的返回值中存在实体类，那么实体类也会被扫描到Swagger中
    @ApiOperation("hello测试类")
    @PostMapping(value = "/hello")
    public User demo(@ApiParam("用户信息") User user){
        user.setUsername(user.getUsername()+"，hello World!!!");
        return user;
    }
    @RequestMapping("/asyncTest")
    public String asyncTest(){
        asyncService.hello();
        return "OK";
    }
}
