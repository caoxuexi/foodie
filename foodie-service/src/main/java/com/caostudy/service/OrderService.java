package com.caostudy.service;

import com.caostudy.pojo.OrderStatus;
import com.caostudy.pojo.bo.SubmitOrderBO;
import com.caostudy.pojo.vo.OrderVO;

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
    public OrderVO createOrder(SubmitOrderBO submitOrderBO);

    /**
     * 修改订单状态
     * @param orderId 订单ID
     * @param orderStatus 订单状态
     */
    public void updateOrderStatus(String orderId,Integer orderStatus);

    /**
     * 查询订单状态
     * @param orderId 订单id
     * @return 订单状态
     */
    public OrderStatus queryOrderStatusInfo(String orderId);

    /**
     * 关闭超时未支付订单
     */
    public void closeOrder();
}
