package com.caostudy.pojo.vo;

import com.caostudy.pojo.Items;
import com.caostudy.pojo.ItemsImg;
import com.caostudy.pojo.ItemsParam;
import com.caostudy.pojo.ItemsSpec;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author 曹学习
 * @description 商品详情VO
 * @date 2020/11/4 23:41
 */
@Getter
@Setter
public class ItemInfoVO {
    private Items item;
    private List<ItemsImg> itemImgList;
    private List<ItemsSpec> itemSpecList;
    private ItemsParam itemParams;
}
