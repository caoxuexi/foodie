package com.caostudy.pojo.bo;

import lombok.Data;

/**
 * @author 曹学习
 * @description SubmitOrderBO 前端订单提交的BO
 * @date 2020/11/12 17:47
 */
@Data
public class SubmitOrderBO {
    private String userId;
    private String itemSpecIds;
    private String addressId;
    private Integer payMethod;
    private String leftMsg;
}
