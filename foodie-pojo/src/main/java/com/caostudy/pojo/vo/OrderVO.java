package com.caostudy.pojo.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderVO {
    private String orderId;
    private MerchantOrdersVO merchantOrdersVO;
}