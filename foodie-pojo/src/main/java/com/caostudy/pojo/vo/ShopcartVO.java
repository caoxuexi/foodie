package com.caostudy.pojo.vo;

import lombok.Data;

/**
 * @author 曹学习
 * @description ShopcartBO
 * @date 2020/11/7 23:36
 */
@Data
public class ShopcartVO {
    public String itemId;
    public String itemImgUrl;
    public String itemName;
    public String specId;
    public String specName;
    public String priceDiscount;
    public String priceNormal;
}
