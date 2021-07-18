package com.caostudy.pojo.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Cao Study
 * @description 用户中心，我的订单列表VO
 * @date 2021/7/18 16:29
 */
@Data
public class MyOrdersVO {

    private String orderId;
    private Date createdTime;
    private Integer payMethod;
    private Integer realPayAmount;
    private Integer postAmount;
    private Integer isComment;
    private Integer orderStatus;

    private List<MySubOrderItemVO> subOrderItemList;

}