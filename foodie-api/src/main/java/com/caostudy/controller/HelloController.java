package com.caostudy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author 曹学习
 * @description HelloController
 * @date 2020/11/1 10:22
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public Object hello(){
        return "hello,World";
    }
}
