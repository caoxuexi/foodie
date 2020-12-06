package com.caostudy.service.impl;

import com.caostudy.enums.OrderStatusEnum;
import com.caostudy.enums.YesOrNo;
import com.caostudy.mapper.OrderItemsMapper;
import com.caostudy.mapper.OrderStatusMapper;
import com.caostudy.mapper.OrdersMapper;
import com.caostudy.pojo.*;
import com.caostudy.pojo.bo.SubmitOrderBO;
import com.caostudy.service.AddressService;
import com.caostudy.service.ItemService;
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

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String createOrder(SubmitOrderBO submitOrderBO) {
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
        String[] itemSpecIdArr = itemSpecIds.split(",");
        Integer totalAmount=0; //商品原价累计
        Integer realPayAmount=0; //优惠后的实际支付价格累计
        for (String itemSpecId : itemSpecIdArr){
            //TODO 整合redis后，商品购买的数量重新从redis的购物车中获取
            int buyCounts=1;
            //2.1 根据规格id搜索
            ItemsSpec itemSpec = itemService.queryItemSpecById(itemSpecId);
            totalAmount+=itemSpec.getPriceNormal()*buyCounts;
            realPayAmount+=itemSpec.getPriceDiscount()*buyCounts;

            //2.2 根据商品id，获得商品信息以及商品图片
            String itemId=itemSpec.getItemId();
            Items item = itemService.queryItemById(itemId);
            String imgUrl=itemService.queryItemMainImgById(itemId);

            //2.3 循环保存子订单数据数据到数据库
            String subOrderId=sid.nextShort();
            OrderItems subOrderItem=new OrderItems();
            subOrderItem.setId(subOrderId);
            subOrderItem.setOrderId(orderId);
            subOrderItem.setItemId(itemId);
            subOrderItem.setItemName(item.getItemName());
            subOrderItem.setItemImg(imgUrl);
            subOrderItem.setBuyCounts(buyCounts);
            subOrderItem.setItemSpecId(itemSpecId);
            subOrderItem.setItemSpecName(itemSpec.getName());
            subOrderItem.setPrice(itemSpec.getPriceDiscount());
            orderItemsMapper.insert(subOrderItem);

            //2.4 在用户提交订单之后，要扣去库存
            itemService.decreaseItemSpecStock(itemSpecId,buyCounts);
        }
        newOrder.setTotalAmount(totalAmount);
        newOrder.setRealPayAmount(realPayAmount);
        ordersMapper.insert(newOrder);
        //3. 保存订单状态表
        OrderStatus waitPayOrderStatus=new OrderStatus();
        waitPayOrderStatus.setOrderId(orderId);
        waitPayOrderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        waitPayOrderStatus.setCreatedTime(new Date());
        orderStatusMapper.insert(waitPayOrderStatus);
        return orderId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateOrderStatus(String orderId, Integer orderStatus) {
        OrderStatus paidStatus=new OrderStatus();
        paidStatus.setOrderId(orderId);
        paidStatus.setOrderStatus(orderStatus);
        paidStatus.setPayTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(paidStatus);
    }
}
