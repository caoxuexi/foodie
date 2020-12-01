package com.caostudy.controller;

import com.caostudy.enums.PayMethod;
import com.caostudy.pojo.bo.SubmitOrderBO;
import com.caostudy.service.OrderService;
import com.caostudy.utils.CaoJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 曹学习
 * @description OrdersController
 * @date 2020/11/12 17:45
 */
@Api(value = "订单相关", tags = {"订单相关的api接口"})
@RestController
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public CaoJSONResult create(
            @ApiParam(name = "submitOrderBO",value = "订单提交对象",required = true)
            @RequestBody SubmitOrderBO submitOrderBO
            ) {
        if (submitOrderBO.getPayMethod() != PayMethod.WEIXIN.type
                && submitOrderBO.getPayMethod() != PayMethod.ALIPAY.type) {
            return CaoJSONResult.errorMsg("支付方式不支持！");
        }
        //1.创建订单
        orderService.createOrder(submitOrderBO);
        //2.创建订单以后，移除购物车中已结算(已提交)的商品
        //3.向支付中心发送当前订单，用于保存支付中心的订单数据
        return CaoJSONResult.ok();
    }
}
