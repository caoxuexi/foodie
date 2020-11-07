package com.caostudy.controller;

import com.caostudy.pojo.bo.ShopcartBO;
import com.caostudy.utils.CaoJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 曹学习
 * @description HelloController
 * @date 2020/11/1 10:22
 */
@RestController
@Api(value = "购物车接口", tags = {"购物车接口相关api"})
@RequestMapping("shopcart")
public class ShopcatController {

    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车", httpMethod = "POST")
    @PostMapping("/add")
    public CaoJSONResult add(@RequestParam String userId,
                             @RequestBody ShopcartBO shopcartBO,
                             HttpServletRequest request,
                             HttpServletResponse response) {

        if (StringUtils.isBlank(userId)) {
            return CaoJSONResult.errorMsg("");
        }
        System.out.println(shopcartBO);
        //TODO 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存
        return CaoJSONResult.ok();
    }


}
