package com.caostudy.pojo.bo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 曹学习
 * @description ShopcartBO
 * @date 2020/11/7 23:36
 */
@Data
@ApiModel(value = "购物车BO",description = "从客户端，传入的购物车数据封装在此entity")
public class ShopcartBO {
    public String itemId;
    public String itemImgUrl;
    public String itemName;
    public String specId;
    public String specName;
    public Integer buyCounts;
    public String priceDiscount;
    public String priceNormal;
}
