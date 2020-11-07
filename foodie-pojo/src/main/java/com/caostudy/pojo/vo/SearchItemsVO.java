package com.caostudy.pojo.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchItemsVO {
    private String itemId;
    private String itemName;
    private Integer sellCounts;
    private String imgUrl;
    private Integer price;
}