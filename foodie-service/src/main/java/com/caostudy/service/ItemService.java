package com.caostudy.service;

import com.caostudy.pojo.Items;
import com.caostudy.pojo.ItemsImg;
import com.caostudy.pojo.ItemsParam;
import com.caostudy.pojo.ItemsSpec;

import java.util.List;

/**
 * @author 曹学习
 * @description CarouselService
 * @date 2020/11/4 11:30
 */
public interface ItemService {
    /**
     * 根据商品id查询详情
     * @param itemId
     * @return
     */
    public Items queryItemById(String itemId);

    /**
     * 根据商品id查询商品图片列表
     * @param itemId
     * @return
     */
    public List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品id查询商品规格
     * @param itemId
     * @return
     */
    public List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品id查询商品参数
     * @param itemId
     * @return
     */
    public ItemsParam queryItemParam(String itemId);
}
