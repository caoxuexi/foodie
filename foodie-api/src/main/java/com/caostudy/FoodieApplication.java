package com.caostudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 曹学习
 * @description Application
 * @date 2020/11/1 10:20
 */
@SpringBootApplication
//扫描mybatis通用mapper的包
@MapperScan(basePackages = "com.caostudy.mapper")
//扫描所有包及相关组件包
@ComponentScan(basePackages = {"com.caostudy","org.n3r.idworker"})
public class FoodieApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoodieApplication.class,args);
    }
}
