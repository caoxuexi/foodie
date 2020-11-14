package com.caostudy.service.impl;

import com.caostudy.enums.YesOrNo;
import com.caostudy.mapper.OrdersMapper;
import com.caostudy.pojo.Orders;
import com.caostudy.pojo.UserAddress;
import com.caostudy.pojo.bo.SubmitOrderBO;
import com.caostudy.service.AddressService;
import com.caostudy.service.OrderService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author 曹学习
 * @description OrderServiceImpl
 * @date 2020/11/12 18:03
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private Sid sid;

    @Autowired
    private AddressService addressService;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void createOrder(SubmitOrderBO submitOrderBO) {
        String userId = submitOrderBO.getUserId();
        String addressId = submitOrderBO.getAddressId();
        String itemSpecIds = submitOrderBO.getItemSpecIds();
        Integer payMethod = submitOrderBO.getPayMethod();
        String leftMsg = submitOrderBO.getLeftMsg();
        //包邮 订单费用为0
        Integer postAmount = 0;
        String orderId = sid.nextShort();

        UserAddress address = addressService.queryUserAddress(userId, addressId);
        //1. 新订单数据保存
        Orders newOrder = new Orders();
        newOrder.setId(orderId);
        newOrder.setUserId(userId);
        newOrder.setReceiverName(address.getReceiver());
        newOrder.setReceiverMobile(address.getMobile());
        newOrder.setReceiverAddress(address.getProvince() + " " + address.getCity() + " "
                + address.getDistrict() + " " + address.getDetail());
        newOrder.setPostAmount(postAmount);
        newOrder.setPayMethod(payMethod);
        newOrder.setLeftMsg(leftMsg);
        newOrder.setIsComment(YesOrNo.No.type);
        newOrder.setIsDelete(YesOrNo.No.type);
        newOrder.setCreatedTime(new Date());
        newOrder.setUpdatedTime(new Date());
        //2. 循环根据itemSpecIds保存订单商品信息表
        //TODO
    }
}
