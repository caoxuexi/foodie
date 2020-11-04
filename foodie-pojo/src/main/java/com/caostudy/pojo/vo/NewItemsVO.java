package com.caostudy.pojo.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author 曹学习
 * @description NewItemsVO 最新商品
 * @date 2020/11/4 17:28
 */
@Getter
@Setter
public class NewItemsVO {
    private Integer rootCatId;
    private String rootCatName;
    private String slogan;
    private String catImage;
    private String bgColor;

    // 六个推荐商品的List
    private List<SimpleItemVO> simpleItemVoList;
}
