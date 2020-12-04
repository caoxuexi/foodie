package com.caostudy.service;

import com.caostudy.pojo.bo.SubmitOrderBO;

/**
 * @author 曹学习
 * @description OrderService
 * @date 2020/11/12 18:02
 */
public interface OrderService {
    /**
     * 创建订单相关信息
     * @param submitOrderBO
     */
    public String createOrder(SubmitOrderBO submitOrderBO);
}
